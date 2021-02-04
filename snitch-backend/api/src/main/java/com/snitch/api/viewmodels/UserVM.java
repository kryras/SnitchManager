package com.snitch.api.viewmodels;

import com.snitch.entities.model.Snitch;
import com.snitch.entities.model.User;

import lombok.Data;

@Data
public class UserVM {

    private String firstName;
    private String lastName;
    private String position;
    private int points;
    private int snitchCount;
    private int victimCount;

    public UserVM(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        position = user.getPosition();
        points = user.getSnitchList().stream().mapToInt(Snitch::getSnitchPoints).sum();
        snitchCount = user.getSnitchList().size();
        victimCount = user.getVictimList().size();
    }
}
