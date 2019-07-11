package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import retrofit2.Call;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
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
    public void createRecipe(Recipe recipe, Carry<Recipe> carry) {
        recipesApi.createRecipe(recipe).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getSearchedRecipes(String search, Carry<List<ShortRecipe>> carry){
        recipesApi.getSearchedRecipes(search).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteRecipe(String id, Carry<Success> carry) {
        recipesApi.deleteRecipe(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void updateRecipe(String id, MemberIngredients ingredients, Carry<List<MemberIngredients>> carry) {
        recipesApi.updateRecipe(id, ingredients).enqueue(new DefaultCallback(carry));
    }
}