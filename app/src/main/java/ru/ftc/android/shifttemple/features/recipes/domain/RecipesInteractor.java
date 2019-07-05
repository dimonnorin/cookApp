package ru.ftc.android.shifttemple.features.recipes.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.network.Carry;

public interface RecipesInteractor {
     void loadRecipes(Carry<List<Recipe>> carry);

     void loadRecipe(String id, Carry<Recipe> carry);

     void createRecipe(Recipe recipe, Carry<Recipe> carry);

     void deleteRecipe(String id, Carry<Success> carry);
}
