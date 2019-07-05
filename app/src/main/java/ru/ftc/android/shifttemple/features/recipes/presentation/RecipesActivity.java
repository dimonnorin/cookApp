package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;


public class RecipesActivity extends BaseActivity implements RecipesView {


    private RecyclerView recyclerView;

    private RecipeAdapter adapter;

    private RecipesListPresenter presenter;

    private ImageButton createButton;


    public static void start(final Context context) {
        Intent intent = new Intent(context, RecipesActivity.class);
        context.startActivity(intent);
    }



    @Override
    protected <T extends MvpView> MvpPresenter<T> getPresenter() {
        return null;//TODO no presenter is here in Recipes yet. ps I
    }

    @Override
    protected RecipesView getMvpView() {
        return this;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        initView();
    }

    private void initView(){

        presenter = PresenterFactory.createPresenter(this);

        recyclerView = findViewById(R.id.recipes_recycle_view);

        createButton = findViewById(R.id.create_product_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Вика
                //presenter.onRecipeCreate(recipe);
            }
        });



        adapter = new RecipeAdapter(this, new RecipeAdapter.RecipeListener() {
            @Override
            public void onRecipeClick(Recipe recipe) {
                //TODO  Аня
                //presenter.
            }

            @Override
            public void onDeleteRecipe(Recipe recipe) {
                //presenter.onRecipeDelete(recipe);
            }
        });


        //adapter.setProducts(recipes);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.attachView(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void startRecipeActivity(Recipe recipe) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showRecipesList(List<Recipe> list) {
        adapter.setProducts(list);
    }

    @Override
    public void showError(String message) {

    }
}
