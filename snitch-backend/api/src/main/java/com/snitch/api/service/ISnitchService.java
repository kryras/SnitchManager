package com.snitch.api.service;

import com.snitch.api.viewmodels.NamedListVM;
import com.snitch.api.viewmodels.SnitchCreateVM;
import com.snitch.api.viewmodels.SnitchVM;
import javassist.NotFoundException;

import java.util.List;

public interface ISnitchService {
    List<SnitchVM> getSnitchList();
    List<NamedListVM> getUserListName();
    List<NamedListVM> getBonusListName();
    List<NamedListVM> getTypeListName();
    void saveSnitch(SnitchCreateVM snitch);
    SnitchCreateVM getSnitch(Long id) throws NotFoundException;
}
