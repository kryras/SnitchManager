package com.snitch.api.viewmodels;

import com.snitch.entities.model.Employee;
import com.snitch.entities.model.Snitch;

public class EmployeeVM {

    private String firstName;
    private String lastName;
    private String position;
    private int points;
    private int snitchCount;
    private int victimCount;

    public EmployeeVM(Employee emp) {
        firstName = emp.getFirstName();
        lastName = emp.getLastName();
        position = emp.getPosition();
        points = emp.getSnitchList().stream().mapToInt(Snitch::getSnitchPoints).sum();
        snitchCount = emp.getSnitchList().size();
        victimCount = emp.getVictimList().size();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSnitchCount() {
        return snitchCount;
    }

    public void setSnitchCount(int snitchCount) {
        this.snitchCount = snitchCount;
    }

    public int getVictimCount() {
        return victimCount;
    }

    public void setVictimCount(int victimCount) {
        this.victimCount = victimCount;
    }
}
