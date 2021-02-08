package com.snitch.api.service.impl;


import com.snitch.api.repository.RoleRepository;
import com.snitch.api.repository.UserRepository;
import com.snitch.api.service.IUserService;
import com.snitch.api.viewmodels.UserAdminListVM;
import com.snitch.api.viewmodels.UserRankingVM;
import com.snitch.entities.model.Role;
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

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User getUser(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie ma! "));
    }

    @Override
    public List<UserRankingVM> getEmployeeList() {
        List<UserRankingVM> result = new ArrayList<>();
        for(User u: userRepository.findAll()){
            if(u.getRoles().stream().anyMatch(x -> x.getName() == ERole.ROLE_USER))
                result.add(new UserRankingVM(u));
        }
        return result;
    }

    @Override
    public List<UserAdminListVM> getUserList() {
        List<UserAdminListVM> result = new ArrayList<>();
        userRepository.findAll().forEach(x -> result.add(new UserAdminListVM(x)));
        return result;
    }

    @Override
    public List<Role> getRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public void updateRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow();
        Role role = roleRepository.findById(roleId).orElseThrow();

        user.getRoles().clear();
        user.getRoles().add(role);
    }
}
