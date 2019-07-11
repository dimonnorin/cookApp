package ru.ftc.android.shifttemple.features.recipes.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepository;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class RecipesInteractorImpl implements RecipesInteractor {
    //TODO imlemantation
    private final RecipesRepository repository;

    public RecipesInteractorImpl(RecipesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadRecipes(Carry<List<ShortRecipe>> carry) {
        repository.loadRecipes(carry);
    }

    @Override
    public void loadRecipe(String id, Carry<Recipe> carry) {
        repository.loadRecipe(id, carry);
    }

    @Override
    public void createRecipe(Recipe recipe, Carry<Recipe> carry) {
        repository.createRecipe(recipe, carry);
    }

    @Override
    public void deleteRecipe(String id, Carry<Success> carry) {
        repository.deleteRecipe(id, carry);
    }

    @Override
    public void updateRecipe(String id, MemberIngredients ingredients, Carry<List<MemberIngredients>> carry) {
        repository.updateRecipe(id, ingredients, carry);
    }

    @Override
    public void getSearchedRecipes(String search, Carry<List<ShortRecipe>> carry) {
        repository.getSearchedRecipes(search, carry);
    }


}