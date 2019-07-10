package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractor;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

//TODO Сервер , взаимодействие

final class RecipePresenter extends MvpPresenter<RecipeView> {

    private final RecipesInteractor interactor;

    //for test without network
    private List<ShortRecipe> recipesData = new ArrayList<>();

    RecipePresenter(RecipesInteractor interactor) {
        this.interactor = interactor;
    }


    void loadMembersList(){
        Log.println(Log.DEBUG, "Test", "loadMembers");
        //view.onLoadMembers();
    }



    void loadRecipe(String id){
        view.showProgress();

        //DEB
        Log.println(Log.DEBUG, "Test", "loadRecipe");
        Recipe r = new Recipe();
        List<User> users = new ArrayList<>();
        users.add(new User("1", "Bob"));
        r.setMembers(users);
        r.setCreator(new User("id", "Bob"));
        r.setId(id);
        r.setTitle("Cookie");
        r.setCreator(new User("id", "Norman"));
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient d = new Ingredient("dough");
        d.setCount(2);
        d.setCollected(1);
        ingredients.add(d);
        Ingredient s = new Ingredient("chocolate");
        s.setCollected(2);
        s.setCount(3);
        ingredients.add(s);
        r.setIngredients(ingredients);
        view.onLoadRecipe(r);




        interactor.loadRecipe(id, new Carry<Recipe>(){
            @Override
            public void onSuccess(Recipe result) {
                view.onLoadRecipe(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("Check.");
            }
        });
    }


    void updateRecipe(final Recipe recipe){
        Log.println(Log.DEBUG, "Test", "updateRecipe");
        //DEB
        view.updateRecipe(recipe);

        interactor.createRecipe(recipe, new Carry<Recipe>() {
            @Override
            public void onSuccess(Recipe result) {
                view.updateRecipe(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("Check.");
            }
        });
    }

}
