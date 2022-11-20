package org.binar.movie.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleModel {

    @NotNull
    private String filmCode;
    @NotNull
    private String studioName;
    @NotNull
    private Integer price;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate showDate;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startAt;
    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endAt;

}