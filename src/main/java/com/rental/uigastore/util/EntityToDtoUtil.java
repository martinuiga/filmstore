package com.rental.uigastore.util;

import com.rental.uigastore.dto.CustomerDTO;
import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Customer;
import com.rental.uigastore.model.Film;

public class EntityToDtoUtil {

    public static FilmDTO convertFilmToDto(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setName(film.getName());
        filmDTO.setAvailable(film.getAvailable());
        filmDTO.setType(film.getType());
        return filmDTO;
    }

    public static CustomerDTO convertCustomerToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setBonusPoints(customer.getBonusPoints());
        return customerDTO;
    }
}
