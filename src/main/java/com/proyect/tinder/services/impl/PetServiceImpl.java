package com.proyect.tinder.services.impl;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Pet;
import com.proyect.tinder.model.User;
import com.proyect.tinder.repository.PetRepository;
import com.proyect.tinder.repository.UserRepository;
import com.proyect.tinder.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(UserRepository userRepository,PetRepository petRepository){
        this.userRepository =  userRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Pet addPet(Integer idUser, Pet pet) throws SpringException {

        Optional<User> user = userRepository.findById(idUser);
        if(!user.isPresent()){
            throw new SpringException("User not exists");
        }
        pet.setUser(user.get());
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Integer idUser, Pet pet) throws SpringException {
        Optional<Pet> response = petRepository.findById(pet.getIdPet());
        if(response.isPresent()){
            Pet pet1 = response.get();
            if(pet1.getUser().getIdUser().equals(idUser)){
                pet1.setName(pet.getName());
                pet1.setSex(pet.getSex());
                return petRepository.save(pet1);
            }else {
                throw new SpringException("You do not have permissions to perform the operation");
            }
        }else {
            throw new SpringException("There is no pet with the requested id");
        }

    }

    @Override
    public void deletePet(Integer idUser, Integer idPet) throws SpringException {
        Optional<Pet> response = petRepository.findById(idPet);
        if(response.isPresent()){
            Pet pet = response.get();
            if(pet.getUser().getIdUser().equals(idUser)){
                pet.setDeleted(true);
            }
        }else {
            throw new SpringException("There is no pet with the requested id");
        }
    }

}
