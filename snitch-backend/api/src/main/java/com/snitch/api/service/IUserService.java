package com.snitch.api.service;

import com.snitch.api.viewmodels.UserAdminListVM;
import com.snitch.api.viewmodels.UserRankingVM;
import com.snitch.entities.model.Role;
import com.snitch.entities.model.User;

import java.util.List;

import javassist.NotFoundException;

public interface IUserService {
    User getUser(Long id) throws NotFoundException;
    List<UserRankingVM> getEmployeeList();
    List<UserAdminListVM> getUserList();
    List<Role> getRoles();
    void updateRole(Long userId, Long roleId);
}
