package com.proyect.tinder.services;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User registerUser(MultipartFile file,User user) throws SpringException;

    User updateUser(MultipartFile file,Integer idUser,User user) throws SpringException;

}
