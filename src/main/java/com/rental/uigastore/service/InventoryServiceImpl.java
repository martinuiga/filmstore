package com.rental.uigastore.service;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.repository.FilmRepository;
import com.rental.uigastore.util.DtoToEntityUtil;
import com.rental.uigastore.util.EntityToDtoUtil;
import com.rental.uigastore.util.FilmType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final FilmRepository filmRepository;

    @Autowired
    public InventoryServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film addFilm(FilmDTO filmDTO) {
        LOGGER.info("addFilm(): film to be added={}", filmDTO);
        Film savedFilm = filmRepository.save(DtoToEntityUtil.convertFilmDtoToEntity(filmDTO));
        LOGGER.info("addFilm(): film created={}", savedFilm);
        return savedFilm;
    }

    @Override
    public void removeFilm(Long filmId) {
        LOGGER.info("removeFilm(): filmId={}", filmId);
        Optional<Film> film = filmRepository.getById(filmId);

        if (film.isEmpty()) {
            LOGGER.error("removeFilm(): filmId={} not found", filmId);
            throw new NoSuchElementException();
        }
        LOGGER.info("removeFilm(): film removed={}", film.get());
        filmRepository.delete(film.get());
    }

    @Override
    public Film changeFilmType(Long filmId, FilmType toType) {
        LOGGER.info("changeFilmType(): filmId={}, filmType={}", filmId, toType);
        Optional<Film> optionalFilm = filmRepository.getById(filmId);

        if (optionalFilm.isEmpty()) {
            LOGGER.error("changeFilmType(): filmId={} not found", filmId);
            throw new NoSuchElementException();
        }

        Film exisitingFilm = optionalFilm.get();
        exisitingFilm.setType(toType);
        LOGGER.info("changeFilmType(): filmId={} type changed to={}", filmId, toType);
        return filmRepository.save(exisitingFilm);
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        List<Film> allFilms = filmRepository.findAll();
        return allFilms.stream().map(EntityToDtoUtil::convertFilmToDto).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> getAllAvailableFilms() {
        List<Film> allAvailableFilms = filmRepository.getAllByAvailable(true);
        return allAvailableFilms.stream().map(EntityToDtoUtil::convertFilmToDto).collect(Collectors.toList());
    }

    @Override
    public FilmDTO setFilmAvailability(Long filmId, boolean available) {
        LOGGER.info("setFilmAvailability(): filmId={} availability={}", filmId, available);
        Optional<Film> filmOptional = filmRepository.findById(filmId);

        if (filmOptional.isEmpty()) {
            LOGGER.error("setFilmAvailability(): filmId={} not found", filmId);
            throw new NoSuchElementException();
        }

        Film film = filmOptional.get();
        film.setAvailable(available);
        LOGGER.info("setFilmAvailability(): filmId={} availability={} set", filmId, available);
        return EntityToDtoUtil.convertFilmToDto(filmRepository.save(film));
    }

    @Override
    public FilmDTO getFilm(Long filmId) {
        Optional<Film> filmOptional = filmRepository.findById(filmId);

        if (filmOptional.isEmpty()) {
            throw new NoSuchElementException();
        }

        return EntityToDtoUtil.convertFilmToDto(filmOptional.get());
    }
}
