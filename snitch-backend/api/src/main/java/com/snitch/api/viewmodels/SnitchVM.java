package com.snitch.api.viewmodels;

import com.snitch.entities.model.Snitch;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SnitchVM {

    private Long id;
    private String who;
    private Date when;
    private String type;
    private List<String> bonus;
    private int points;

    public SnitchVM(Snitch snitch){
        this.id = snitch.getId();
        this.who = snitch.getSnitchId().getFirstName() + " " + snitch.getSnitchId().getLastName();
        this.when = snitch.getDate();
        this.type = snitch.getSnitchType().getName();
        this.points = snitch.getSnitchPoints();
        this.bonus = new ArrayList<>();
        snitch.getBonuses().forEach(x -> this.bonus.add(x.getName()));
    }
}
