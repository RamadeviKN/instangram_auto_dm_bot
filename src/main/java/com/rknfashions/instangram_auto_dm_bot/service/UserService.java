package com.rknfashions.instangram_auto_dm_bot.service;

import com.rknfashions.instangram_auto_dm_bot.model.User;
import com.rknfashions.instangram_auto_dm_bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
