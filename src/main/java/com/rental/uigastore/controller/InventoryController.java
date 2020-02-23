package com.rental.uigastore.controller;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.service.InventoryService;
import com.rental.uigastore.util.FilmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/add_film")
    public ResponseEntity<Film> addNewFilm(@RequestBody FilmDTO filmDTO) {
        Film savedFilm = inventoryService.addFilm(filmDTO);
        return new ResponseEntity<>(savedFilm, HttpStatus.OK);
    }

    @DeleteMapping("/remove_film/{id}")
    public ResponseEntity<Void> removeFilm(@PathVariable Long id) {
        inventoryService.removeFilm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change_film_type/{id}")
    public ResponseEntity<Film> changeFilmType(@PathVariable Long id, @RequestParam(name = "newType") FilmType filmType) {
        Film film = inventoryService.changeFilmType(id, filmType);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @GetMapping("/all_films")
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return new ResponseEntity<>(inventoryService.getAllFilms(), HttpStatus.OK);
    }

    @GetMapping("/all_available_films")
    public ResponseEntity<List<FilmDTO>> getAllAvailableFilms() {
        return new ResponseEntity<>(inventoryService.getAllAvailableFilms(), HttpStatus.OK);
    }

}
