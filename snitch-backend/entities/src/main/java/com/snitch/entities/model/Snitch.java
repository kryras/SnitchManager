package com.snitch.entities.model;

import javax.persistence.*;

@Entity
@Table(name = "snitch")
public class Snitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional =false)
    private User snitchId;

    @ManyToOne(fetch = FetchType.LAZY, optional =false)
    private User victimId;

    @ManyToOne(fetch = FetchType.LAZY, optional =false)
    private SnitchType snitchType;
    //TODO many to many to bonus
    public int getSnitchPoints(){
        return snitchType.getPoints(); //TODO add bonus points
    }
    public Snitch(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSnitchId() {
        return snitchId;
    }

    public void setSnitchId(User snitchId) {
        this.snitchId = snitchId;
    }

    public User getVictimId() {
        return victimId;
    }

    public void setVictimId(User victimId) {
        this.victimId = victimId;
    }

    public SnitchType getSnitchType() {
        return snitchType;
    }

    public void setSnitchType(SnitchType snitchType) {
        this.snitchType = snitchType;
    }
}
