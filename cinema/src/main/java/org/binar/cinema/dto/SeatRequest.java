package org.binar.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequest {
    @NotNull
    private Character seatRow;
    @NotNull
    private Integer seatNumber;
    @NotNull
    private Integer studioId;
    @Null
    private boolean seatStatus;
}
