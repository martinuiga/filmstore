package com.rental.uigastore.service;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.dto.RentalDTO;
import com.rental.uigastore.repository.RentalRepository;
import com.rental.uigastore.request.RentalPeriodRequest;
import com.rental.uigastore.util.CalculationUtil;
import com.rental.uigastore.util.DtoToEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

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
        Integer totalPrice = 0;

        for (RentalPeriodRequest rental : rentals) {
            RentalDTO rentalDTO = new RentalDTO();
            rentalDTO.setFilmId(rental.getFilmId());
            rentalDTO.setCustomerId(customerId);
            rentalDTO.setInitialRentalPeriod(rental.getRentalLength());
            rentalDTO.setRentalStart(new Timestamp(System.currentTimeMillis()));

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

                totalPrice += CalculationUtil.calculateRentalPrice(film.getType(), rental.getRentalLength() - bonusDays);
                System.out.println(totalPrice);

                inventoryService.setFilmAvailability(rental.getFilmId(), false);
                customerService.removeCustomerBonuspoints(customerId, bonusDays);
                rentalRepository.save(DtoToEntityUtil.convertRentalDtoToEntity(rentalDTO));
            } else if (film.getAvailable()) {
                totalPrice += CalculationUtil.calculateRentalPrice(film.getType(), rental.getRentalLength());
                System.out.println(totalPrice);
                rentalRepository.save(DtoToEntityUtil.convertRentalDtoToEntity(rentalDTO));
            }
        }
        return totalPrice;
    }

    @Override
    public Integer returnMovie(List<Long> filmIds, Long customerId) {

        // Add bonus points
        return null;
    }
}
