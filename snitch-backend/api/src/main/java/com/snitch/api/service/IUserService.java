package com.snitch.api.service;

import com.snitch.api.viewmodels.UserVM;
import com.snitch.entities.model.User;

import java.util.List;

import javassist.NotFoundException;

public interface IUserService {
    User getUser(Long id) throws NotFoundException;
    List<UserVM> getUserList();
}
