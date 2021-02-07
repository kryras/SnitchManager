package com.snitch.api.controller;

import com.snitch.api.service.IUserService;
import com.snitch.api.viewmodels.UserVM;
import com.snitch.entities.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javassist.NotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/ranking")
    @PreAuthorize("hasRole('USER')")
    public List<UserVM> getEmployeeList() {
        return userService.getEmployeeList();
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable("id") long id) throws NotFoundException {
        return userService.getUser(id);
    }
}
