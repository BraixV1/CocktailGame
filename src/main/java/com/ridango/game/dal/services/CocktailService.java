package com.ridango.game.dal.services;

import com.ridango.game.dal.repositories.ICocktailRepository;
import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CocktailService {

    @Autowired
    private ICocktailRepository cocktailRepository;

    @Transactional(readOnly = true)
    public Cocktail get(Long id) {
        return cocktailRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cocktail create(Cocktail cocktail) {

        return cocktailRepository.save(cocktail);
    }

}