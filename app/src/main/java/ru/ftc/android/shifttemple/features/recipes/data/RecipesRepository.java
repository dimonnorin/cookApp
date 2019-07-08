package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface RecipesRepository {

    void loadRecipes(Carry<List<ShortRecipe>> carry);

    void loadRecipe(String id, Carry<Recipe> carry);

    void createRecipe(Recipe recipe, Carry<Recipe> carry);

    void deleteRecipe(String id, Carry<Success> carry);
}