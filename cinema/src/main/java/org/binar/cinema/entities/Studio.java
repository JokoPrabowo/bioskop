package org.binar.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Studios")
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Integer studioId;

    @Column(name = "studio_name")
    private String studioName;

    @JoinColumn(name = "studio-id", insertable = false, updatable = false)
    private Set<SeatId> seatIds;
}
