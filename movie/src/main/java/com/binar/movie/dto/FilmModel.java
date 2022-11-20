package com.binar.movie.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmModel {

    @NotNull
    private String filmCode;
    @NotNull
    private String filmName;
    @NotNull
    private String category;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate onShow;
}
