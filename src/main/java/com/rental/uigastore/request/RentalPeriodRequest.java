package com.rental.uigastore.request;

import lombok.Data;

@Data
public class RentalPeriodRequest {

    private Long filmId;
    private Integer rentalLength;
}
