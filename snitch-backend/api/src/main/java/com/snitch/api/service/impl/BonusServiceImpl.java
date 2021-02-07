package com.snitch.api.service.impl;

import com.snitch.api.repository.BonusRepository;
import com.snitch.api.service.IBonusService;
import com.snitch.entities.model.Bonus;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BonusServiceImpl implements IBonusService {

    @Autowired
    BonusRepository bonusRepository;

    @Override
    public void saveBonus(Bonus bonus) {
        bonusRepository.save(bonus);
    }

    @Override
    public List<Bonus> getBonusList() {
        List<Bonus> bonuses = new ArrayList<>();
        bonusRepository.findAll().forEach(bonuses::add);
        return bonuses;
    }

    @Override
    public void updateBonus(Bonus bonus) {
        bonusRepository.save(bonus);
    }

    @Override
    public Bonus getBonus(Long id) throws NotFoundException {
        return bonusRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie ma! "));
    }
}
