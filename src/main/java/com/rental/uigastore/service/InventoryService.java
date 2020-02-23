package com.rental.uigastore.service;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.util.FilmType;

import java.util.List;

public interface InventoryService {

    Film addFilm(FilmDTO film);

    void removeFilm(Long filmId);

    Film changeFilmType(Long filmId, FilmType toType);

    List<FilmDTO> getAllFilms();

    List<FilmDTO> getAllAvailableFilms();

    FilmDTO setFilmAvailability(Long filmId, boolean available);

    FilmDTO getFilm(Long filmId);
}
