package com.snitch.entities.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "snitch")
@Data
@NoArgsConstructor
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
}
