package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public final class RecipesDataSourceImpl implements RecipesDataSource {

    private final RecipesApi recipesApi;

    public RecipesDataSourceImpl(RecipesApi recipesApi) {
        this.recipesApi = recipesApi;
    }

    @Override
    public void getRecipes(final Carry<List<ShortRecipe>> carry) {
        //TODO создаем запрос на основе carry, в carry будет success or failure
        recipesApi.getRecipesList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getRecipe(String id, Carry<Recipe> carry) {
        recipesApi.getRecipe(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createRecipe(ShortRecipe shortRecipe, Carry<ShortRecipe> carry) {
        recipesApi.createRecipe(shortRecipe).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteRecipe(String id, Carry<Success> carry) {
        recipesApi.deleteRecipe(id).enqueue(new DefaultCallback(carry));
    }
}