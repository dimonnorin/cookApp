package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;

public class RecipeActivity extends AppCompatActivity implements RecipeView {



    public static final String RES_RECIPE_ID = "id";

    private String recipeId;

    private Recipe recipe;

    private IngredientAdapter ingredientAdapter;

    private RecipePresenter presenter;

    private MemberAdapter memberAdapter;

    private ProgressBar progressBar;

    private TextView name;

    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        //check for null
        recipeId = intent.getStringExtra(RES_RECIPE_ID);
        initView();
    }


    private void initView() {
        presenter = PresenterFactory.createPresenter(this);



        name = findViewById(R.id.recipe_name);
        description = findViewById(R.id.recipe_description);
        progressBar = findViewById(R.id.progress_bar);

        RecyclerView ingredients = findViewById(R.id.ingredients_view);
        ingredientAdapter = new IngredientAdapter(this, new IngredientAdapter.IngredientListener() {
            @Override
            public void onAddIngredient(Ingredient ingredient, int count) {
                Log.println(Log.DEBUG, "Test", "add" + count);

                if(checkForIngredients(ingredient, count)){
                    int inStock = ingredient.getCollected();
                    int c = ingredient.getCount();
                    int add = 0;
                    if((c - count - inStock) < 0) {
                        add = c;
                    }else{
                        add = inStock + count;
                    }
                    ingredient.setCollected(add);
                    //TODO attention! sorry for this code
                    //we do not have any session id
                    String id = "0";
                    boolean has = false;
                    for (User user: recipe.getMembers()){
                        if(user.getSessionId().equals(id)){
                            has = true;
                            break;
                        }
                    }

                    if(!has){
                        //again, we do not have session id yet
                        recipe.getMembers().add(new User("0", "Name"));
                    }



                    presenter.updateRecipe(recipe);
                    presenter.loadMembersList();

                    removeIngredientFromStock(ingredient, add);
                }
                //TODO Сюда прилетает число ингридиентов которое ты хочешь добавить
                //в рецепт,
            }

            @Override
            public void onError(String message) {
                Toast.makeText(RecipeActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });



        ingredients.setAdapter(ingredientAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView members = findViewById(R.id.members_view);
        memberAdapter = new MemberAdapter(this);
        members.setAdapter(memberAdapter);
        members.setLayoutManager(new LinearLayoutManager(this));

        presenter.attachView(this);
        presenter.loadRecipe(recipeId);
    }


    private boolean checkForIngredients(Ingredient ingredient, int count){
        //проверка наличия ингредиентов в холодильнике
        return true;
    }
    private void removeIngredientFromStock(Ingredient ingredient, int count){
        //когда будет холодос
    }



    private void onMemberJoin(){

    }

    @Override
    public void onLoadMembers(List<User> members) {

    }

    @Override
    public void onLoadRecipe(Recipe recipe) {
        this.recipe = recipe;
        name.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        ingredientAdapter.setItems(recipe.getIngredients());
        memberAdapter.setItems(recipe.getMembers());
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        onLoadRecipe(recipe);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
