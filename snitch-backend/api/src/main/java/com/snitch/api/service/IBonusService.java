package com.snitch.api.service;


import com.snitch.entities.model.Bonus;
import javassist.NotFoundException;

import java.util.List;

public interface IBonusService {
    void saveBonus(Bonus bonus);
    List<Bonus> getBonusList();
    void updateBonus(Bonus bonus);
    Bonus getBonus(Long id) throws NotFoundException;
}
