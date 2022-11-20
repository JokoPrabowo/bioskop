package org.binar.cinema.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.binar.cinema.dto.StudioRequest;
import org.binar.cinema.entities.Studio;
import org.binar.cinema.repositories.StudioRepo;
import org.binar.cinema.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class StudioServiceImpl implements StudioService {
    @Autowired
    StudioRepo studioRepo;

    public Studio create(StudioRequest studio){
        log.info("Creating new studio");
        try{
            Studio data = new Studio(null, studio.getStudioName());
            log.info("Studio has been created");
            return studioRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating studio data {}", e.getMessage());
            return null;
        }
    }
    public Studio update(Integer id, StudioRequest studio){
        log.info("Updating studio");
        try{
            Studio data = findOne(id);
            data.setStudioName(studio.getStudioName());
            log.info("Studio has been updated");
            return studioRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating studio data {}", e.getMessage());
            return null;
        }
    }
    public Studio findOne(Integer id){
        log.info("Retrieving the studio");
        try{
            Optional<Studio> studio = studioRepo.findById(id);
            if (!studio.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("Studio has been retrieved");
            return studio.get();
        }catch(Exception e){
            log.error("Error detected when retrieving studio data {}", e.getMessage());
            return null;
        }
    }
    public Iterable<Studio> findAll(){
        log.info("Retrieving the studios data");
        return studioRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting studio data");
        studioRepo.deleteById(id);
    }
}

