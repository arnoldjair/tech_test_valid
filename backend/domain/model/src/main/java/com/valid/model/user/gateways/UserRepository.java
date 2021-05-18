package com.valid.model.user.gateways;

import java.util.List;
import java.util.UUID;

import com.valid.model.user.User;

public interface UserRepository {
    public User save(User user);

    public List<User> save(List<User> users);

    public List<User> findAllById(List<UUID> ids);

    public List<User> findAll();

    public Boolean exists(User user);
}
