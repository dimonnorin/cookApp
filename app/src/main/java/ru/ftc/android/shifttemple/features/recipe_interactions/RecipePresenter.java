package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
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



    void loadRecipe(String id){
        view.showProgress();

        //DEB
        Log.println(Log.DEBUG, "Test", "loadRecipe");
        /*Recipe r = new Recipe();
        List<User> users = new ArrayList<>();
        users.add(new User("1", "Bob"));
        users.add(new User("1", "Cat"));
        users.add(new User("1", "Bob"));
        users.add(new User("1", "Cat"));
        users.add(new User("1", "Bob"));
        users.add(new User("1", "Cat"));


        r.setMembers(users);
        r.setCreator(new User("id", "Петя"));
        r.setId(id);
        r.setTitle("Блины");
        r.setDescription("" +
                "1. Налить в подходящую емкость молоко комнатной температуры, вбить туда яйца, добавить соль и сахар.\n" +
                "2. Постепенно подсыпать муку, при этом помешивая, чтобы не получалось комочков.\n" +
                "3. Все размешать, оставить на 15–20 минут и потом добавить растительное масло.\n" +
                "4. На сильно раскаленную сковороду налить немного масла и жарить блины."
        );
        r.setCreator(new User("id", "Петя"));
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient d = new Ingredient("Мука");
        d.setCountNeed("1");
        d.setCountHave("0");
        ingredients.add(d);

        Ingredient h = new Ingredient("Дрожжи");
        h.setCountNeed("1");
        h.setCountHave("0");
        ingredients.add(h);

        Ingredient m = new Ingredient("Дрожжи");
        m.setCountNeed("1");
        m.setCountHave("0");
        ingredients.add(m);

        Ingredient s = new Ingredient("chocolate");
        s.setCountHave("2");
        s.setCountNeed("3");
        ingredients.add(s);
        r.setIngredients(ingredients);
        view.onLoadRecipe(r);*/


        //DEB
        view.hideProgress();



        interactor.loadRecipe(id, new Carry<Recipe>(){
            @Override
            public void onSuccess(Recipe result) {
                view.onLoadRecipe(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError("Check your internet connection.");
            }
        });
    }


    void updateRecipe(final MemberIngredients ingredients, final String id){
        Log.println(Log.DEBUG, "Test", "updateRecipe");

        Log.println(Log.DEBUG, "Test", ingredients.getUser().getName());


        //DEB
        //view.updateRecipe(recipe);


        interactor.updateRecipe(id, ingredients, new Carry<List<MemberIngredients>>() {
            @Override
            public void onSuccess(List<MemberIngredients> result) {
                view.onLoadMembersIngredients(result);
            }
            @Override
            public void onFailure(Throwable throwable) {
                view.showError("Check your connection.");
            }
        });

        /*interactor.createRecipe(recipe, new Carry<Recipe>() {
            @Override
            public void onSuccess(Recipe result) {

                view.updateRecipe(result);
            }

            @Override
            public void onFailure(Throwable throwable) {

                view.showError("Check.");
            }
        });*/
    }

}
