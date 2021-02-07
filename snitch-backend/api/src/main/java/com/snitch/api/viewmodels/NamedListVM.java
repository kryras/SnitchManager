package com.snitch.api.viewmodels;

import com.snitch.entities.model.Bonus;
import com.snitch.entities.model.SnitchType;
import com.snitch.entities.model.User;
import lombok.Data;

@Data
public class NamedListVM {

    private Long id;
    private String name;

    public NamedListVM(User u)
    {
        this.id = u.getId();
        this.name = u.getFirstName() + " " + u.getLastName();
    }
    public NamedListVM(SnitchType t)
    {
        this.id = t.getId();
        this.name = t.getName();
    }
    public NamedListVM(Bonus b)
    {
        this.id = b.getId();
        this.name = b.getName();
    }
}
