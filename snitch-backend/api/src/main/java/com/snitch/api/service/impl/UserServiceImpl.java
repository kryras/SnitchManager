package com.snitch.api.service.impl;


import com.snitch.api.repository.UserRepository;
import com.snitch.api.service.IUserService;
import com.snitch.api.viewmodels.UserVM;
import com.snitch.entities.model.User;

import enums.ERole;
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
    public List<UserVM> getEmployeeList() {
        List<UserVM> result = new ArrayList<>();
        for(User u: userRepository.findAll()){
            if(u.getRoles().stream().anyMatch(x -> x.getName() == ERole.ROLE_USER))
                result.add(new UserVM(u));
        }
        return result;
    }

    @Override
    public List<User> getUserList() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }
}
