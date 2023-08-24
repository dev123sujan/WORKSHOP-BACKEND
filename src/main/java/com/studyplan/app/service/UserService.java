package com.studyplan.app.service;

import com.studyplan.app.entities.User;

import java.util.List;

public interface UserService {

    public User saveOneUser(User user);

    public User getOneUser(Long userId);

    public List<User> getAllUser();

    public Boolean deleteOneUser(Long userId);

    public User updateOneUSer(User user, Long userId);

}
