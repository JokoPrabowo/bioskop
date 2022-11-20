package com.binar.movie.services.impl;

import com.binar.movie.dto.ScheduleModel;
import com.binar.movie.entities.Schedule;
import com.binar.movie.repositories.ScheduleRepo;
import com.binar.movie.services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepo scheduleRepo;

    public Schedule create(ScheduleModel schedule){
        log.info("Creating new schedule");
        try{
            String created = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Schedule data = new Schedule(null, schedule.getFilmCode(), null, schedule.getStudioName(), schedule.getPrice(), schedule.getShowDate(), schedule.getStartAt(), schedule.getEndAt(), LocalDateTime.parse(created, format), null);
            log.info("Schedule has been created");
            return scheduleRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating schedule data {}", e.getMessage());
            return null;
        }
    }
    public Schedule update(Integer id, ScheduleModel schedule){
        log.info("Updating schedule");
        try{
            Schedule data = findOne(id);
            String updated = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            data.setPrice(schedule.getPrice());
            data.setShowDate(schedule.getShowDate());
            data.setStartAt(schedule.getStartAt());
            data.setEndAt(schedule.getEndAt());
            data.setUpdateAt(LocalDateTime.parse(updated, format));
            return scheduleRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating schedule data {}", e.getMessage());
            return null;
        }
    }
    public Schedule findOne(Integer id){
        log.info("Retrieving schedule data");
        try{
            Optional<Schedule> schedule = scheduleRepo.findById(id);
            if(!schedule.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("Schedule has been retrieved");
            return schedule.get();
        }catch(Exception e){
            log.error("Error detected when retrieving schedule data {}", e.getMessage());
            return null;
        }
    }
    public List<Schedule> findByCode(String code){
        log.info("Retrieving the schedules data by film code");
        return scheduleRepo.findByFilmCode(code);
    }
    public Iterable<Schedule> findAll(){
        log.info("Retrieving the schedules data");
        return scheduleRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting the schedule");
        scheduleRepo.deleteById(id);
    }
}
