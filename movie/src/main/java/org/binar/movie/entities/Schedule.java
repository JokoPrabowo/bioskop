package org.binar.movie.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "film_code")
    private String filmCode;

    @ManyToOne
    @JoinColumn(name = "film_code", referencedColumnName = "film_code", insertable = false, updatable = false)
    private Film film;

    @Column(name = "studio_name")
    private String studioName;

    @Column(name = "price")
    private Integer price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "show_date")
    private LocalDate showDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "start_at")
    private LocalTime startAt;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "end_at")
    private LocalTime endAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
