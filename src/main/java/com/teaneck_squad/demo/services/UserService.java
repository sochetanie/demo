package com.teaneck_squad.demo.services;

import com.teaneck_squad.demo.models.User;
import com.teaneck_squad.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createUser(User user) {
        final User save = userRepository.save(user);
        if(save != null) {

            return true;
        }
        else return false;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }


}
