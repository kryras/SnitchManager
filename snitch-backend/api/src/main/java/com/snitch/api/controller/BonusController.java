package com.snitch.api.controller;

import com.snitch.api.service.IBonusService;
import com.snitch.entities.model.Bonus;
import javassist.NotFoundException;
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
    public List<Bonus> getBonusList() {
        return bonusService.getBonusList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public Bonus getBonus(@PathVariable("id") long id) throws NotFoundException {
        return bonusService.getBonus(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public void saveBonus(@RequestBody Bonus newBonus) {
        bonusService.saveBonus(newBonus);
    }

    @PutMapping
    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    public void updateBonus(@RequestBody Bonus newBonus) {
        bonusService.updateBonus(newBonus);
    }
}
