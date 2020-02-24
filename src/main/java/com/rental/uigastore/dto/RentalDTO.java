package com.rental.uigastore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalDTO {

    private Long filmId;
    private Long customerId;
    private Integer initialRentalPeriod;
    private LocalDate rentalEnd;
}
