package com.wodongso.wodongso.service;

import com.wodongso.wodongso.entity.User;
import com.wodongso.wodongso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void userRegister(User user) {
        userRepository.save(user);
    }
}
