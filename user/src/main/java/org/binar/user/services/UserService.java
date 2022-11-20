package org.binar.user.services;

import org.binar.user.dto.UserRequest;
import org.binar.user.entities.User;

import java.util.Optional;

public interface UserService {
    public User create(UserRequest user);
    public User update(String username, UserRequest user);
    public User findOne(String username);
    public Iterable<User> findAll();
}
