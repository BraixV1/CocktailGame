package com.ridango.game.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "APP_USER")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Game> games;
}
