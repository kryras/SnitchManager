package com.snitch.api.service;


import com.snitch.api.repository.UserRepository;
import com.snitch.api.viewmodels.UserVM;
import com.snitch.entities.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie ma! "));
    }

    @Override
    public List<UserVM> getUserList() {
        List<UserVM> result = new ArrayList<>();
        for(User user: userRepository.findAll()){
            result.add(new UserVM(user));
        }
        return result;
    }
}
