package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public interface RecipesDataSource {

    void getRecipes(Carry<List<Recipe>> carry);

    void getRecipe(String id, Carry<Recipe> carry);

    void createRecipe(Recipe recipe, Carry<Recipe> carry);

    void deleteRecipe(String id, Carry<Success> carry);
}
