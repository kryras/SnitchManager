package com.snitch.api.controller;

import com.snitch.api.service.ISnitchService;
import com.snitch.api.viewmodels.NamedListVM;
import com.snitch.api.viewmodels.SnitchVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/snitch")
public class SnitchController {

    @Autowired
    ISnitchService snitchService;

    @GetMapping
    //@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public List<SnitchVM> getSnitchList(){
        return snitchService.getSnitchList();
    }

    @GetMapping("/bonus")
    @PreAuthorize("hasRole('ADMIN')")
    public List<NamedListVM> getBonusList(){
        return snitchService.getBonusListName();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<NamedListVM> getUserList(){
        return snitchService.getUserListName();
    }

    @GetMapping("/type")
    @PreAuthorize("hasRole('ADMIN')")
    public List<NamedListVM> getTypeList(){
        return snitchService.getTypeListName();
    }
}
