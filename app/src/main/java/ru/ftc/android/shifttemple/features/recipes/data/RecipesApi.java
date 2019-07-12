package ru.ftc.android.shifttemple.features.recipes.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.AddedIngredient;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface RecipesApi {

    /*Параметры запроса добавляются с помощью аннотации
    @Query к параметру метода. Они автоматически добавляются в конце URL-адреса.*/

    @GET("recipes")
    Call<List<ShortRecipe>> getRecipesList();

    @GET("recipe/{id}")// String id замещает {id}
    Call<Recipe> getRecipe(@Path("id") String id);

    @GET("recipe/find")
    Call<List<ShortRecipe>> getSearchedRecipes(@Query("search") String search);

    //
    @PUT("recipe/{id}")
    Call<List<MemberIngredients>> updateRecipe(@Path("id") String id , @Body MemberIngredients ingredients);

    @POST("recipe")//Body использует объект product в качестве тела запроса,отправляем его
    Call<Recipe> createRecipe(@Body Recipe recipe);//TODO возврат id?
    //TODO или использовать в том числе для обновления информации о рецепте


    @DELETE("recipe/{id}")
    Call<Success> deleteRecipe(@Path("id") String id, @Header("userId") String userId);

}
