package com.rental.uigastore.dto;

import com.rental.uigastore.util.FilmType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO {

    private String name;
    private FilmType type;
    private Boolean available;
}
