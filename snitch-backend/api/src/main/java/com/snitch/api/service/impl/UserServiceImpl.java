package com.snitch.api.service.impl;


import com.snitch.api.repository.RoleRepository;
import com.snitch.api.repository.UserRepository;
import com.snitch.api.service.IUserService;
import com.snitch.entities.model.Role;
import com.snitch.api.viewmodels.UserAdminListVM;
import com.snitch.api.viewmodels.UserRankingVM;
import com.snitch.entities.model.User;

import enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserRankingVM getUser(Long id) throws NotFoundException {
        User temp = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie ma! "));
        return new UserRankingVM(temp);
    }

    @Override
    public List<UserRankingVM> getEmployeeList() {
        List<UserRankingVM> result = new ArrayList<>();
        for(User u: userRepository.findAll()){
            if(u.getRoles().stream().anyMatch(x -> x.getName() == ERole.ROLE_USER))
                result.add(new UserRankingVM(u));
        }
        result.sort(Comparator.comparing(UserRankingVM::getPoints).reversed());
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

    @Override
    public List<User> getUsersWithRole(ERole ERole) throws NotFoundException {
        Role role = roleRepository.getByName(ERole)
                .orElseThrow(() -> new NotFoundException("Role not found!"));
        return userRepository.getAllByRolesIn(Collections.singletonList(role));
    }
}
