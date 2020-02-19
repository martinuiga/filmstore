package com.rental.uigastore.controller;

import com.rental.uigastore.dto.FilmDTO;
import com.rental.uigastore.model.Film;
import com.rental.uigastore.service.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class InventoryController {

    private final Inventory inventoryService;

    @Autowired
    public InventoryController(Inventory inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Film> addNewFilm(@RequestBody FilmDTO filmDTO) {
        Film savedFilm = inventoryService.addFilm(filmDTO);
        return new ResponseEntity<>(savedFilm, HttpStatus.OK);
    }
}
