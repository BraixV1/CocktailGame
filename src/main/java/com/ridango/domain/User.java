package com.ridango.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends BaseEntity {

    public String Name;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Game> Games;






    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Iterable<Game> getGames() {
        return Games;
    }
    public void setGames(List<Game> games) {
        this.Games = games;
    }



}
