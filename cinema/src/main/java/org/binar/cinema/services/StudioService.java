package org.binar.cinema.services;

import org.binar.cinema.dto.StudioRequest;
import org.binar.cinema.entities.Studio;

public interface StudioService {
    public Studio create(StudioRequest studio);
    public Studio update(Integer id, StudioRequest studio);
    public Studio findOne(Integer id);
    public Iterable<Studio> findAll();
    public void delete(Integer id);
}
