package com.snitch.api.service;


import com.snitch.entities.model.Bonus;
import java.util.List;

public interface IBonusService {
    void saveBonus(Bonus bonus);
    List<Bonus> getBonusList();
}
