package org.binar.movie.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRes {
    private Integer scheduleId;
    private String filmCode;
    private String studioName;
    private Integer price;
    private LocalDate showDate;
    private LocalTime startAt;
    private LocalTime endAt;
}
