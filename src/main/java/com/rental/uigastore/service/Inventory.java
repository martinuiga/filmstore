package com.rental.uigastore.service;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.util.FilmType;

import java.util.List;

public interface Inventory {

    FilmDTO addFilm(FilmDTO film);

    void removeFilm(Long filmId);

    FilmDTO changeFilmType(Long filmId, FilmType toType);

    List<FilmDTO> getAllFilms();

    List<FilmDTO> getAllAvailableFilms();
}
