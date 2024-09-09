package com.ridango.game.dal.services;

import com.ridango.game.dal.repositories.IHintRepository;
import com.ridango.game.domain.Game;
import com.ridango.game.domain.Hint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HintService{

    @Autowired
    private IHintRepository hintRepository;

    @Autowired
    private GameService gameService;

    @Transactional(readOnly = true)
    public List<Hint> getAll() {
        return hintRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hint getById(Long id) {
        return hintRepository.findById(id).orElse(null);
    }

    @Transactional
    public Hint create(Hint hint) {
        // Ensure currentCocktail is saved
        return hintRepository.save(hint);
    }

    @Transactional
    public Hint update(Hint hint) {
        Hint existingHint = hintRepository.findById(hint.getId()).orElse(null);
        if (existingHint != null) {
            existingHint.setShowStrIngredient1(hint.isShowStrIngredient1());
            existingHint.setShowStrIngredient2(hint.isShowStrIngredient2());
            existingHint.setShowStrIngredient3(hint.isShowStrIngredient3());
            existingHint.setShowStrIngredient4(hint.isShowStrIngredient4());
            existingHint.setShowStrIngredient5(hint.isShowStrIngredient5());
            existingHint.setShowStrIngredient6(hint.isShowStrIngredient6());
            existingHint.setShowStrIngredient7(hint.isShowStrIngredient7());
            existingHint.setShowStrIngredient8(hint.isShowStrIngredient8());
            existingHint.setShowStrIngredient9(hint.isShowStrIngredient9());
            existingHint.setShowStrIngredient10(hint.isShowStrIngredient10());
            existingHint.setShowStrIngredient11(hint.isShowStrIngredient11());
            existingHint.setShowStrIngredient12(hint.isShowStrIngredient12());
            existingHint.setShowStrIngredient13(hint.isShowStrIngredient13());
            existingHint.setShowStrIngredient14(hint.isShowStrIngredient14());
            existingHint.setShowStrIngredient15(hint.isShowStrIngredient15());

            return hintRepository.save(hint);
        }
        return null;
    }

    @Transactional
    public Hint save(Hint hint) {
        Hint found = hintRepository.findById(hint.getId()).orElse(null);
        Hint result;
        if (found != null) {
            result = create(hint);
        }
        else {
            result = update(hint);
        }
        return result;
    }

}
