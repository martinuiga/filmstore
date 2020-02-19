package com.rental.uigastore.service;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.repository.FilmRepository;
import com.rental.uigastore.util.DtoToEntityUtil;
import com.rental.uigastore.util.EntityToDtoUtil;
import com.rental.uigastore.util.FilmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryImpl implements Inventory {

    private final FilmRepository filmRepository;

    @Autowired
    public InventoryImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film addFilm(FilmDTO filmDTO) {
        Film savedFilm = filmRepository.save(DtoToEntityUtil.convertFilmDtoToEntity(filmDTO));
        return savedFilm;
    }

    @Override
    public void removeFilm(Long filmId) {
        Optional<Film> film = filmRepository.getById(filmId);

        if (film.isEmpty()) {
            throw new NoSuchElementException();
        }
        filmRepository.delete(film.get());
    }

    @Override
    public Film changeFilmType(Long filmId, FilmType toType) {
        Optional<Film> optionalFilm = filmRepository.getById(filmId);

        if (optionalFilm.isEmpty()) {
            throw new NoSuchElementException();
        }

        Film exisitingFilm = optionalFilm.get();
        exisitingFilm.setType(toType);

        Film updatedFilm = filmRepository.save(exisitingFilm);
        return updatedFilm;
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        List<Film> allFilms = filmRepository.findAll();
        return allFilms.stream().map(EntityToDtoUtil::convertFilmToDto).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> getAllAvailableFilms() {
        List<Film> allAvailableFilms = filmRepository.findAll();
        return allAvailableFilms.stream().map(EntityToDtoUtil::convertFilmToDto).collect(Collectors.toList());
    }
}
