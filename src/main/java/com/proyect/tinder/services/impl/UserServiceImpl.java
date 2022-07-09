package com.proyect.tinder.services.impl;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Pet;
import com.proyect.tinder.model.Photo;
import com.proyect.tinder.model.User;
import com.proyect.tinder.repository.PetRepository;
import com.proyect.tinder.repository.UserRepository;
import com.proyect.tinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        String passwordEncoder = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoder);
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

            String passwordEncoder = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(passwordEncoder);
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
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(mail);

        if(user != null){
            List<GrantedAuthority> permissions = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULE_PHOTO");
            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULE_PET");
            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULE_VOTE");

            permissions.add(p1);
            permissions.add(p2);
            permissions.add(p3);

            org.springframework.security.core.userdetails.User user1 =
                    new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),permissions);
            return user1;
        }
        return null;
    }
}
