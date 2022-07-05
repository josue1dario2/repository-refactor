package com.proyect.tinder.services;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Pet;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Pet addPet(Integer idUser, MultipartFile file, Pet pet)throws SpringException;

    Pet updatePet(Integer idUser,MultipartFile file,Pet pet)throws SpringException;

    void deletePet(Integer idUser,Integer idPet)throws SpringException;

}
