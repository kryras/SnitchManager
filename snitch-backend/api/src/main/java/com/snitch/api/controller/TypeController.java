package com.snitch.api.controller;

import com.snitch.api.service.IBonusService;
import com.snitch.api.service.ITypeService;
import com.snitch.entities.model.Bonus;
import com.snitch.entities.model.SnitchType;
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
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public List<SnitchType> getTypeList() {
        return typeService.getTypeList();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void saveType(@RequestBody SnitchType newType) {
        typeService.saveType(newType);
    }
}
