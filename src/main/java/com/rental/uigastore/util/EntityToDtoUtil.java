package com.rental.uigastore.util;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;

public class EntityToDtoUtil {

    public static FilmDTO convertFilmToDto(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setName(film.getName());
        filmDTO.setPrice(film.getPrice());
        filmDTO.setAvailable(film.getAvailable());
        filmDTO.setType(film.getType());
        return filmDTO;
    }
}
