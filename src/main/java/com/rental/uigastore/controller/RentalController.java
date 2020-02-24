package com.rental.uigastore.controller;

import com.rental.uigastore.request.RentalPeriodRequest;
import com.rental.uigastore.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rent_movies")
    public ResponseEntity<Integer> rentMovies(@RequestBody List<RentalPeriodRequest> rentals,
                                              @RequestParam(name = "customerId") Long customerId,
                                              @RequestParam(name = "useBonusPoints") Boolean useBonusPoints) {
        Integer totalPrice = rentalService.rentMovies(rentals, customerId, useBonusPoints);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @PostMapping("/return_movies")
    public ResponseEntity<Integer> returnMovies(@RequestBody List<Long> rentalIds,
                                                @RequestParam(name = "customerId") Long customerId) {
        Integer totalPrice = rentalService.returnMovies(rentalIds, customerId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
