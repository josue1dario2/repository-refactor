package com.proyect.tinder.services.impl;

import com.proyect.tinder.models.User;
import com.proyect.tinder.repositories.UserRepository;
import com.proyect.tinder.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Long id) {
        User u = userRepository.findById(id).get();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());

        return userRepository.save(u);
    }

    @Override
    public Void enableUser(Long id) {
        return null;
    }

    @Override
    public Void disableUser(Long id) {
        return null;
    }
}
