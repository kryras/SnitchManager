package com.snitch.entities.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "snitch")
@Getter
@Setter
@NoArgsConstructor
public class Snitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, optional =false)
    private User snitchId;

    @ManyToOne(fetch = FetchType.EAGER, optional =false)
    private User victimId;

    @ManyToOne(fetch = FetchType.EAGER, optional =false)
    private SnitchType snitchType;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "snitch_bonus",
            joinColumns = @JoinColumn(name = "snitch_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id"))
    private Set<Bonus> bonuses;

    public int getSnitchPoints(){
        return (snitchType.getPoints() + bonuses.stream().mapToInt(Bonus::getPoints).sum());
    }
}
