package org.example.practice.service;

import org.example.practice.entity.User;

import java.util.List;

public interface IUserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> findAll();
    void register(User user);
}
