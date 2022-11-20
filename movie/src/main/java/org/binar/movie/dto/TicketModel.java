package org.binar.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketModel {

    private String username;
    private Integer scheduleId;
    private String studioName;
    private Character seatRow;
    private Integer seatNumber;
}

