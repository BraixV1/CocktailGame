package com.ridango.game.dal.services;

import com.ridango.game.dal.repositories.ICocktailRepository;
import com.ridango.game.domain.Cocktail;
import com.ridango.game.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CocktailService {

    @Autowired
    private ICocktailRepository cocktailRepository;

    @Transactional(readOnly = true)
    public List<Cocktail> getAll() {
        return cocktailRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cocktail get(Long id) {
        return cocktailRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cocktail create(Cocktail cocktail) {
        // Ensure currentCocktail is saved
        return cocktailRepository.save(cocktail);
    }

}