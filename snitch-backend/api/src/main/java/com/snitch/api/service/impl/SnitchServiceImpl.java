package com.snitch.api.service.impl;

import com.snitch.api.repository.SnitchRepository;
import com.snitch.api.service.ISnitchService;
import com.snitch.api.viewmodels.SnitchVM;
import com.snitch.entities.model.Snitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SnitchServiceImpl implements ISnitchService {

    @Autowired
    SnitchRepository snitchRepository;

    @Override
    public List<SnitchVM> getSnitchList() {
        List<SnitchVM> snitchList = new ArrayList<>();
        for(Snitch snitch: snitchRepository.findAll()){
            snitchList.add(new SnitchVM(snitch));
        }
        snitchList.sort(Comparator.comparing(SnitchVM::getWhen));
        return snitchList;
    }
}
