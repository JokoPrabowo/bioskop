package org.binar.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private Character seatRow;
    private Integer seatNumber;
    private Integer studioId;
    private boolean seatStatus;
}
