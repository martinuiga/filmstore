package com.rental.uigastore.util;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.dto.RentalDTO;
import com.rental.uigastore.model.Customer;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.model.Rental;

public class DtoToEntityUtil {

    public static Film convertFilmDtoToEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setType(filmDTO.getType());
        film.setAvailable(filmDTO.getAvailable());
        return film;
    }

    public static Customer convertCustomerDtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setBonusPoints(customerDTO.getBonusPoints());
        customer.setName(customerDTO.getName());
        return customer;
    }

    public static Rental convertRentalDtoToEntity(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setCustomerId(rentalDTO.getCustomerId());
        rental.setFilmId(rentalDTO.getFilmId());
        rental.setRentalEnd(rentalDTO.getRentalEnd());
        return rental;
    }
}
