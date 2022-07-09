package com.proyect.tinder.services.impl;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Pet;
import com.proyect.tinder.model.Photo;
import com.proyect.tinder.model.User;
import com.proyect.tinder.repository.PetRepository;
import com.proyect.tinder.repository.UserRepository;
import com.proyect.tinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PhotoServiceImpl photoService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,PhotoServiceImpl photoService){
        this.userRepository = userRepository;
        this.photoService = photoService;
    }

    @Override
    public User registerUser(MultipartFile file, User userEntity) throws SpringException {

        User user = new User();

        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setCreatedAt(LocalDateTime.now());

        Photo photo = photoService.savePhoto(file);
        user.setPhoto(photo);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(MultipartFile file, Integer idUser, User userEntity) throws SpringException {

        Optional<User> response = userRepository.findById(idUser);

        if(response.isPresent()){
            User user = response.get();
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setEmail(userEntity.getEmail());
            user.setPassword(userEntity.getPassword());

            Integer idPhoto = null;
            if(user.getPhoto() != null){
                idPhoto = user.getPhoto().getIdPhoto();
            }
            Photo photo = photoService.updatePhoto(idPhoto,file);
            user.setPhoto(photo);

            return userRepository.save(user);

        }else{
            throw new SpringException("The requested user was not found");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
