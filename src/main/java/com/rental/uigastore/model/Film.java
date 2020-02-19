package com.rental.uigastore.model;

import com.rental.uigastore.util.FilmType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Film {

    @Id
    @GeneratedValue
    private Long id;
    private Integer price;
    private String name;
    private FilmType type;
    private Boolean available;
}
