package com.proyect.tinder.services;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Pet;

public interface PetService {

    Pet addPet(Integer idUser, Pet pet)throws SpringException;

    Pet updatePet(Integer idUser,Pet pet)throws SpringException;

    void deletePet(Integer idUser,Integer idPet)throws SpringException;
}
