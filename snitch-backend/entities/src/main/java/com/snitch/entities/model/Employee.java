package com.snitch.entities.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String position;

    @OneToMany(mappedBy = "snitchId")
    private List<Snitch> snitchList;

    @OneToMany(mappedBy = "victimId")
    private List<Snitch> victimList;

    public Employee(){
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Snitch> getSnitchList() {
        return snitchList;
    }

    public void setSnitchList(List<Snitch> snitchList) {
        this.snitchList = snitchList;
    }

    public List<Snitch> getVictimList() {
        return victimList;
    }

    public void setVictimList(List<Snitch> victimList) {
        this.victimList = victimList;
    }
}
