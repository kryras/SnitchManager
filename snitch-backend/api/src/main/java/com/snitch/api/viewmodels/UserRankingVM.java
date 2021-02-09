package com.snitch.api.viewmodels;

import com.snitch.entities.model.Snitch;
import com.snitch.entities.model.User;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class UserRankingVM {

    private String firstName;
    private String lastName;
    private int points;
    private int snitchCount;
    private int victimCount;

    public UserRankingVM(User user) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date());

        firstName = user.getFirstName();
        lastName = user.getLastName();
        points = 0;
        snitchCount = 0;
        for (Snitch s : user.getSnitchList()) {
            cal1.setTime(s.getDate());
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                    points += s.getSnitchPoints();
                    snitchCount++;
                }
            }
        }
        victimCount = 0;
        for (Snitch ss : user.getVictimList()) {
            cal1.setTime(ss.getDate());
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                    victimCount++;
                }
            }
        }

    }
}
