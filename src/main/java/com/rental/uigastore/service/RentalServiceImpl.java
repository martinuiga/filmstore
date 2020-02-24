package com.rental.uigastore.service;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.dto.RentalDTO;
import com.rental.uigastore.model.Rental;
import com.rental.uigastore.repository.RentalRepository;
import com.rental.uigastore.request.RentalPeriodRequest;
import com.rental.uigastore.util.CalculationUtil;
import com.rental.uigastore.util.DtoToEntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
    private final Logger LOGGER = LoggerFactory.getLogger(RentalServiceImpl.class);

    private final InventoryService inventoryService;
    private final RentalRepository rentalRepository;
    private final CustomerService customerService;

    @Autowired
    public RentalServiceImpl(InventoryService inventoryService, RentalRepository rentalRepository, CustomerService customerService) {
        this.inventoryService = inventoryService;
        this.rentalRepository = rentalRepository;
        this.customerService = customerService;
    }

    @Override
    public Integer rentMovies(List<RentalPeriodRequest> rentals, Long customerId, Boolean useBonusPoints) {
        LOGGER.info("rentMovies(): rentalRequests={}, customerId={}, useBonusPoints={}", rentals, customerId, useBonusPoints);
        int totalPrice = 0;

        for (RentalPeriodRequest rental : rentals) {
            RentalDTO rentalDTO = new RentalDTO();
            rentalDTO.setFilmId(rental.getFilmId());
            rentalDTO.setCustomerId(customerId);
            rentalDTO.setInitialRentalPeriod(rental.getRentalLength());
            rentalDTO.setRentalEnd(LocalDate.now().plusDays(rental.getRentalLength()));

            if (rental.getRentalLength() <= 0) {
                continue;
            }

            FilmDTO film = inventoryService.getFilm(rental.getFilmId());
            if (useBonusPoints && film.getAvailable()) {
                CustomerDTO customerDTO = customerService.showCustomer(customerId);

                int bonusDays = 0;
                if (customerDTO.getBonusPoints() >= 25) {
                    bonusDays = customerDTO.getBonusPoints() / 25;

                    if (rental.getRentalLength() < bonusDays) {
                        bonusDays = rental.getRentalLength();
                    }
                }

                int filmPrice = CalculationUtil.calculateRentalPrice(film.getType(), rental.getRentalLength() - bonusDays);
                totalPrice += filmPrice;

                customerService.removeCustomerBonuspoints(customerId, bonusDays);
                rentalRepository.save(DtoToEntityUtil.convertRentalDtoToEntity(rentalDTO));
                inventoryService.setFilmAvailability(rental.getFilmId(), false);

                LOGGER.info("{} ({}) {} days {} EUR (Paid with {} bonus points)", film.getName(), film.getType(),
                        rental.getRentalLength(), filmPrice, bonusDays * 25);
            } else if (film.getAvailable()) {
                int filmPrice = CalculationUtil.calculateRentalPrice(film.getType(), rental.getRentalLength());
                totalPrice += filmPrice;
                rentalRepository.save(DtoToEntityUtil.convertRentalDtoToEntity(rentalDTO));
                inventoryService.setFilmAvailability(rental.getFilmId(), false);

                LOGGER.info("{} ({}) {} days {} EUR", film.getName(), film.getType(), rental.getRentalLength(), filmPrice);
            }
        }
        LOGGER.info("Total price: {} EUR", totalPrice);
        return totalPrice;
    }

    @Override
    public Integer returnMovies(List<Long> rentalIds, Long customerId) {
        LOGGER.info("returnMovies(): rentalIds={}, customerId={}", rentalIds, customerId);
        int totalPrice = 0;

        for (Long rentalId : rentalIds) {
            Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);

            if (rentalOptional.isEmpty()) {
                continue;
            }

            Rental rental = rentalOptional.get();
            FilmDTO film = inventoryService.setFilmAvailability(rental.getFilmId(), true);

            LocalDate returnDate = LocalDate.now();
            customerService.addCustomerBonuspoints(rental.getCustomerId(), 2);
            long extraDays = ChronoUnit.DAYS.between(rental.getRentalEnd(), returnDate);

            if (extraDays > 0) {
                int filmPrice = CalculationUtil.calculateRentalPrice(film.getType(), (int) extraDays);
                LOGGER.info("{} ({}) {} extra days {} EUR",
                        film.getName(), film.getType(), extraDays, filmPrice);
                totalPrice += filmPrice;
            }
        }

        LOGGER.info("Total late charge: {} EUR", totalPrice);
        return totalPrice;
    }
}
