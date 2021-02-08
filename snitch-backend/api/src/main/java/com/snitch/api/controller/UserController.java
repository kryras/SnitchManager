package com.snitch.api.controller;

import com.snitch.api.service.IUserService;
import com.snitch.api.viewmodels.UserAdminListVM;
import com.snitch.api.viewmodels.UserRankingVM;
import com.snitch.entities.model.Role;
import com.snitch.entities.model.SnitchType;
import com.snitch.entities.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javassist.NotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/ranking")
    @PreAuthorize("hasRole('USER') || hasRole('MANAGER') || hasRole('ADMIN')")
    public List<UserRankingVM> getEmployeeList() {
        return userService.getEmployeeList();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserAdminListVM> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> getRoleList() {
        return userService.getRoles();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@PathVariable("id") long id) throws NotFoundException {
        return userService.getUser(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void getUser(@PathVariable("id") Long userId, @RequestBody Long roleId) {
        userService.updateRole(userId, roleId);
    }
}
