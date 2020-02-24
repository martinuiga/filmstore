package com.rental.uigastore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Rental {

    @Id
    @GeneratedValue
    private Long id;
    private Long filmId;
    private Long customerId;
    private Integer initialRentalPeriod;
    private LocalDate rentalEnd;
}
