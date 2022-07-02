package com.proyect.tinder.services;

import com.proyect.tinder.exception.SpringException;

public interface VoteService {

    void vote(Integer idUser,Integer idPet1, Integer idPet2) throws SpringException;

}
