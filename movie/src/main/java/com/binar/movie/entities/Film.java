package com.binar.movie.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Film {

    @Id
    @Column(name = "film_code")
    private String filmCode;

    @Column(name = "film_name")
    private String filmName;

    private String category;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "on_show", columnDefinition = "DATE")
    private LocalDate onShow;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
