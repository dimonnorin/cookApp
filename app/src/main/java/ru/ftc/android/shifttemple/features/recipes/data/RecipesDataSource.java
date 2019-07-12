package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Query;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public interface RecipesDataSource {

    void getRecipes(Carry<List<ShortRecipe>> carry);

    void getRecipe(String id, Carry<Recipe> carry);

    void createRecipe(Recipe recipe, Carry<Recipe> carry);

    void deleteRecipe(String id, String userId, Carry<Success> carry);


    void getSearchedRecipes(String search, Carry<List<ShortRecipe>> carry);
    //Call<List<MemberIngredients>> updateRecipe(@Path("id") String id);

    void updateRecipe(String id, MemberIngredients ingredients,  Carry<List<MemberIngredients>> carry);


}
