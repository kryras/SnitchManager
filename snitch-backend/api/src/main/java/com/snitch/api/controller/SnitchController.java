package com.snitch.api.controller;

import com.snitch.api.service.ISnitchService;
import com.snitch.api.viewmodels.NamedListVM;
import com.snitch.api.viewmodels.SnitchCreateVM;
import com.snitch.api.viewmodels.SnitchVM;
import com.snitch.entities.model.Snitch;
import com.snitch.entities.model.SnitchType;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/snitch")
public class SnitchController {

    @Autowired
    ISnitchService snitchService;

    @GetMapping
    @PreAuthorize("hasRole('USER') || hasRole('MANAGER') || hasRole('ADMIN')")
    public List<SnitchVM> getSnitchList(){
        return snitchService.getSnitchList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
    public Snitch getSnitch(@PathVariable("id") long id) throws NotFoundException {
        return snitchService.getSnitch(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public void saveSnitchList(@RequestBody SnitchCreateVM snitchVM){
        snitchService.saveSnitch(snitchVM);
    }

    @GetMapping("/bonus")
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public List<NamedListVM> getBonusList(){
        return snitchService.getBonusListName();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public List<NamedListVM> getUserList(){
        return snitchService.getUserListName();
    }

    @GetMapping("/type")
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public List<NamedListVM> getTypeList(){
        return snitchService.getTypeListName();
    }
}
