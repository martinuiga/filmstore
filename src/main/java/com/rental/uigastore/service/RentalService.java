package com.rental.uigastore.service;

public interface RentalService {

    void rentMovie(Long filmId, Long customerId);

    void returnMovie(Long filmId, Long customerId);
}
