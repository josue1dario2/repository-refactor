package com.proyect.tinder.services;

import com.proyect.tinder.models.User;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user ,Long id);

    Void enableUser(Long id);

    Void disableUser(Long id);

}
