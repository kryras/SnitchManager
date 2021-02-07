package com.snitch.api.service.impl;

import com.snitch.api.repository.BonusRepository;
import com.snitch.api.repository.SnitchTypeRepository;
import com.snitch.api.service.ITypeService;
import com.snitch.entities.model.Bonus;
import com.snitch.entities.model.SnitchType;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    SnitchTypeRepository typeRepository;

    @Override
    public void saveType(SnitchType type) {
        typeRepository.save(type);
    }

    @Override
    public List<SnitchType> getTypeList() {
        List<SnitchType> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public void updateType(SnitchType type) {
        typeRepository.save(type);
    }

    @Override
    public SnitchType getSnitchType(long id) throws NotFoundException {
        return typeRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie ma! "));
    }
}
