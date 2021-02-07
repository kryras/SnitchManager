package com.snitch.api.service.impl;

import com.snitch.api.repository.BonusRepository;
import com.snitch.api.repository.SnitchRepository;
import com.snitch.api.repository.SnitchTypeRepository;
import com.snitch.api.repository.UserRepository;
import com.snitch.api.service.ISnitchService;
import com.snitch.api.viewmodels.NamedListVM;
import com.snitch.api.viewmodels.SnitchVM;
import com.snitch.entities.model.Bonus;
import com.snitch.entities.model.Snitch;
import com.snitch.entities.model.SnitchType;
import com.snitch.entities.model.User;
import enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SnitchServiceImpl implements ISnitchService {

    @Autowired
    SnitchRepository snitchRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BonusRepository bonusRepository;

    @Autowired
    SnitchTypeRepository snitchTypeRepository;

    @Override
    public List<SnitchVM> getSnitchList() {
        List<SnitchVM> snitchList = new ArrayList<>();
        for(Snitch snitch: snitchRepository.findAll()){
            snitchList.add(new SnitchVM(snitch));
        }
        snitchList.sort(Comparator.comparing(SnitchVM::getWhen));
        return snitchList;
    }

    @Override
    public List<NamedListVM> getUserListName() {
        List<NamedListVM> userList = new ArrayList<>();
        for(User u: userRepository.findAll()){
            if(u.getRoles().stream().anyMatch(x -> x.getName() == ERole.ROLE_USER)){
                userList.add(new NamedListVM(u));
            }
        }
        return  userList;
    }

    @Override
    public List<NamedListVM> getBonusListName() {
        List<NamedListVM> bonusList = new ArrayList<>();
        for(Bonus b: bonusRepository.findAll()) {
            bonusList.add(new NamedListVM(b));
        }
        return bonusList;
    }

    @Override
    public List<NamedListVM> getTypeListName() {
        List<NamedListVM> typeList = new ArrayList<>();
        for(SnitchType t: snitchTypeRepository.findAll()) {
            typeList.add(new NamedListVM(t));
        }
        return typeList;
    }
}
