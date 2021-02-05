package com.snitch.api.controller;

import com.snitch.api.service.IBonusService;
import com.snitch.entities.model.Bonus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bonus")
public class BonusController {

    @Autowired
    private IBonusService bonusService;

    @GetMapping
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public List<Bonus> getBonusList() {
        return bonusService.getBonusList();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void saveBonus(@RequestBody Bonus newBonus) {
        bonusService.saveBonus(newBonus);
    }
}
