package org.binar.movie.services;

import org.binar.movie.dto.ScheduleModel;
import org.binar.movie.entities.Schedule;

import java.util.List;

public interface ScheduleService {
    public Schedule create(ScheduleModel schedule);
    public Schedule update(Integer id,ScheduleModel schedule);
    public Schedule findOne(Integer id);
    public List<Schedule> findByCode(String code);
    public Iterable<Schedule> findAll();
    public void delete(Integer id);
}
