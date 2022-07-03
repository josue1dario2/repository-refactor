package com.proyect.tinder.services;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    Photo savePhoto(MultipartFile file) throws SpringException;

    Photo updatePhoto(Integer idPhoto,MultipartFile file) throws SpringException;
}
