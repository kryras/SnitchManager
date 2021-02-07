package com.snitch.api.service;

import com.snitch.entities.model.SnitchType;
import javassist.NotFoundException;

import java.util.List;

public interface ITypeService {
    void saveType(SnitchType type);
    List<SnitchType> getTypeList();
    void updateType(SnitchType type);
    SnitchType getSnitchType(long id) throws NotFoundException;
}
