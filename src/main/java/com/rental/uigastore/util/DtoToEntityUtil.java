package com.rental.uigastore.util;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;

public class DtoToEntityUtil {

    public static Film convertFilmDtoToEntity(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setPrice(filmDTO.getPrice());
        film.setType(filmDTO.getType());
        film.setAvailable(filmDTO.getAvailable());
        return film;
    }
}
