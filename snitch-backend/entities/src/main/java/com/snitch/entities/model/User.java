package com.snitch.entities.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String firstName;

    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Size(max = 20)
    private String position;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "snitchId")
    private List<Snitch> snitchList;

    @OneToMany(mappedBy = "victimId")
    private List<Snitch> victimList;

    public User() {
    }

    public User(
            @NotBlank @Size(max = 20) String firstName,
            @NotBlank @Size(max = 20) String lastName,
            @NotBlank @Size(max = 20) String position,
            @NotBlank @Size(max = 20) String username,
            @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
