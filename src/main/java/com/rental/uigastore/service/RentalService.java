package com.rental.uigastore.service;

import com.rental.uigastore.request.RentalPeriodRequest;

import java.util.List;

public interface RentalService {

    Integer rentMovies(List<RentalPeriodRequest> rentals, Long customerId, Boolean useBonusPoints);

    Integer returnMovies(List<Long> filmIds, Long customerId);
}
