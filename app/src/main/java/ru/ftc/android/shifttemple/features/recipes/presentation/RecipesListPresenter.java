package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.products.domain.model.Product;
import ru.ftc.android.shifttemple.features.products.domain.model.Success;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractor;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:43
 */
//TODO тут работа с запросами , можно тут добавлять реакции, вызовы через presenter
//TODO Реакции на пользовательские действия
final class RecipesListPresenter extends MvpPresenter<RecipesView> {

    private final RecipesInteractor interactor;

    //for test without network
    //DEB
    private List<ShortRecipe> recipesData = new ArrayList<>();

    RecipesListPresenter(RecipesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {


        //TODO Статически заданные начальные поля продуктов, вызывается при переходе на активити

        //DEB
        //Log.println(Log.DEBUG, "Test", "onViewReady")
        // ;
        /*ShortRecipe r = new ShortRecipe("Блины");
        r.setDescription("1. Налить в подходящую емкость молоко комнатной температуры, вбить туда яйца, добавить соль и сахар.\n");
        r.setStatus("ожидание");
        recipesData.add(r);*/

        loadRecipes(false);
    }


    void onSearchRecipes(String search){
        Log.println(Log.DEBUG, "Test", "search");
        view.showProgress();
        interactor.getSearchedRecipes(search, new Carry<List<ShortRecipe>>() {
            @Override
            public void onSuccess(List<ShortRecipe> result) {
                view.showRecipesList(result);
                view.hideProgress();
            }
            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }


    //TODO реакция на взаимодействие с продуктом
    /*void onRecipeDelete(ShortRecipe shortRecipe) {
        Log.println(Log.DEBUG, "Test", "onRecipeDelete");
        interactor.deleteRecipe(shortRecipe.getId(), new Carry<Success>(){
            @Override
            public void onSuccess(Success result) {
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }*/

    /*void loadRecipeData(ShortRecipe shortRecipe){
        Log.println(Log.DEBUG, "Test", "loadRecipeData");
        interactor.loadRecipe(shortRecipe.getId(),new Carry<Recipe>(){
            @Override
            public void onSuccess(Recipe result) {
                //view.startRecipeActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }*/

    //DEB
    void onRecipeCreate(Recipe recipe) {
        //DEB
        recipesData.add(recipe);
        Log.println(Log.DEBUG, "Test", "loadRecipeData");
        loadRecipes(false);
    }



    void loadRecipes(boolean swipe) {
        Log.println(Log.DEBUG, "Test", "loadRecipes");
        if(!swipe)
            view.showProgress();
        //DEB
        view.showRecipesList(recipesData);
        //view.hideProgress();

        interactor.loadRecipes(new Carry<List<ShortRecipe>>(){
            @Override
            public void onSuccess(List<ShortRecipe> result) {
                Log.println(Log.DEBUG, "Test", "load recipes success.");
                view.hideProgress();
                view.showRecipesList(result);
                view.hideSwipeProgress();
            }
            @Override
            public void onFailure(Throwable throwable) {
                Log.println(Log.DEBUG, "Test", "load recipes failed.");
                view.hideProgress();
                view.showError("Check your internet connection.");
                view.hideSwipeProgress();
            }
        });
    }




    //все ниже оставлено для примера для примера
    void onBookSelected(Product product) {
        //view.showProgress();
        /*interactor.loadRecipe(product.getId(), new Carry<Product>() {

            @Override
            public void onSuccess(Product result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });*/
    }

    void onBookLongClicked(Product product) {
        //view.showProgress();


        /*interactor.deleteRecipe(product.getId(), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadProducts();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });*/
    }

    /*private final AtomicInteger atomicInteger = new AtomicInteger();

    public void onCreateBookClicked() {
        int id = atomicInteger.incrementAndGet();
        String name = "Name_" + id;
        String author = "Author_" + id;
        int pages = 7 * id;

        Product product = new Product(name, author, String.valueOf(pages));
        interactor.createBook(product, new Carry<Product>() {
            @Override
            public void onSuccess(Product result) {
                loadProducts();
            }

            @Override
            public void onFailure(Throwable throwable) {
                //view.showError(throwable.getMessage());
            }
        });
    }*/

}