package org.binar.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Seats")
public class Seat {
    @EmbeddedId
    private SeatId seatId;

    @Column(name = "studio_id")
    private Integer studioId;

    @ManyToOne
    @JoinColumn(name = "studio_id", insertable = false, updatable = false)
    private Studio studio;

    @Column(name = "seat_status")
    private boolean seatStatus;
}
