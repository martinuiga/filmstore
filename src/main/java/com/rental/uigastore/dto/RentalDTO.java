package com.rental.uigastore.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RentalDTO {

    private Long filmId;
    private Long customerId;
    private Integer initialRentalPeriod;
    private Timestamp rentalStart;
    private Timestamp rentalEnd;
}
