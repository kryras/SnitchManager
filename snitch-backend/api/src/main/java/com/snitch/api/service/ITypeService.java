package com.snitch.api.service;

import com.snitch.entities.model.SnitchType;

import java.util.List;

public interface ITypeService {
    void saveType(SnitchType type);
    List<SnitchType> getTypeList();
}
