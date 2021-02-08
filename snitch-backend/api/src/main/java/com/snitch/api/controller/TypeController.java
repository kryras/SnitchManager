package com.snitch.api.controller;

import com.snitch.api.service.ITypeService;
import com.snitch.entities.model.SnitchType;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping
    public List<SnitchType> getTypeList() {
        return typeService.getTypeList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
    public SnitchType getSnitchType(@PathVariable("id") long id) throws NotFoundException {
        return typeService.getSnitchType(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
    public void saveType(@RequestBody SnitchType newType) {
        typeService.saveType(newType);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
    public void updateType(@RequestBody SnitchType type) {
        typeService.updateType(type);
    }
}
