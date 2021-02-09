package com.snitch.api.service;

import com.snitch.api.viewmodels.UserAdminListVM;
import com.snitch.api.viewmodels.UserRankingVM;
import com.snitch.entities.model.Role;
import com.snitch.entities.model.User;

import java.util.List;

import enums.ERole;
import javassist.NotFoundException;

public interface IUserService {
    UserRankingVM getUser(Long id) throws NotFoundException;
    List<UserRankingVM> getEmployeeList();
    List<UserAdminListVM> getUserList();
    List<Role> getRoles();
    void updateRole(Long userId, Integer roleId);
    List<User> getUsersWithRole(ERole ERole) throws NotFoundException;
}
