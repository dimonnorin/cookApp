package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.recipe_interactions.RecipeActivity;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;


public class RecipesListActivity extends BaseActivity implements RecipesView {


    private RecyclerView recyclerView;

    private RecipeAdapter adapter;

    private RecipesListPresenter presenter;

    private ImageButton createButton;

    private ImageButton updateButton;

    private Gson gson = new Gson();


    //TODO зачем это
    public static void start(final Context context) {
        Intent intent = new Intent(context, RecipesListActivity.class);
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
        setContentView(R.layout.activity_recipes_list);
        initView();
    }

    //DEB
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.println(Log.DEBUG,"Test", "On result");
        if (data == null) {
            return;
        }
        Recipe recipe = new Recipe();
        if (getIntent() != null) {
            String response = data.getStringExtra(CreateRecipeActivity.RESOURCE_NAME);
            if (response != null) {
                recipe = gson.fromJson(response, Recipe.class);
            }
        }
        //PROD
        //presenter.loadRecipes();
        Log.println(Log.DEBUG,"Test", "On create recipe");
        presenter.onRecipeCreate(recipe);
    }

    private void initView(){

        presenter = PresenterFactory.createPresenter(this);

        recyclerView = findViewById(R.id.recipes_recycle_view);

        createButton = findViewById(R.id.create_product_button);

        updateButton  = findViewById(R.id.update_button);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.DEBUG, "Test", "createRecipeClicked");
                //TODO Вика
                //DEB
                Intent intent = new Intent(RecipesListActivity.this, CreateRecipeActivity.class);
                startActivityForResult(intent, CreateRecipeActivity.GET_RECIPE_CODE);
                //presenter.onRecipeCreate(recipe);
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.DEBUG, "Test", "updateButtonClicked");
                presenter.loadRecipes();
            }
        });



        adapter = new RecipeAdapter(this, new RecipeAdapter.RecipeListener() {
            @Override
            public void onRecipeClick(ShortRecipe shortRecipe) {
                Log.println(Log.DEBUG, "Test", "RecipeClicked");

                Intent intent = new Intent(RecipesListActivity.this, RecipeActivity.class);
                intent.putExtra(RecipeActivity.RES_RECIPE_ID, shortRecipe.getId());
                startActivity(intent);


                //вызов будет по id в RecipeActivity
                //presenter.loadRecipeData(shortRecipe);
            }
        });


        //adapter.setRecipes(recipes);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.attachView(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void startRecipeActivity(ShortRecipe shortRecipe) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showRecipesList(List<ShortRecipe> list) {
        Log.println(Log.DEBUG, "Test", "show list");
        adapter.setRecipes(list);
    }

    @Override
    public void showError(String message) {

    }
}