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

    // Long customerId, Integer rentalLength, Boolean useBonusPoints
    @PostMapping("/rent_movies")
    public ResponseEntity<Integer> rentMovies(@RequestBody List<RentalPeriodRequest> rentals,
                                              @RequestParam(name = "customerId") Long customerId,
                                              @RequestParam(name = "useBonusPoints") Boolean useBonusPoints) {
        Integer totalPrice = rentalService.rentMovies(rentals, customerId, useBonusPoints);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    // upon rental says for how many days, when returning then request how many days were late or not
}
