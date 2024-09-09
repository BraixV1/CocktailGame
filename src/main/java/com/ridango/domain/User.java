package com.ridango.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name= "name")
    public String Name;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Game> Games;



}
