package com.proyect.tinder.services;

import com.proyect.tinder.models.User;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user ,Long id);

    void enableUser(Long id);

    void deleteUser(Long id) throws Exception;

}
