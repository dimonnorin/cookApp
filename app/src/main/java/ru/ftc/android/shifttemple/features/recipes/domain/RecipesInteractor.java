package ru.ftc.android.shifttemple.features.recipes.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

public interface RecipesInteractor {
     void loadRecipes(Carry<List<ShortRecipe>> carry);

     void loadRecipe(String id, Carry<Recipe> carry);

     void createRecipe(Recipe recipe, Carry<Recipe> carry);

     void deleteRecipe(String id, String userId, Carry<Success> carry);

     void updateRecipe(String id, MemberIngredients ingredients, Carry<List<MemberIngredients>> carry);

     void getSearchedRecipes(String search, Carry<List<ShortRecipe>> carry);

}
