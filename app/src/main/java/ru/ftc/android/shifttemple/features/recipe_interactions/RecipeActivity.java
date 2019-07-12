package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.AddedIngredient;
import ru.ftc.android.shifttemple.features.recipe_interactions.model.MemberIngredients;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.features.recipes.presentation.RecipesListActivity;

public class RecipeActivity extends AppCompatActivity implements RecipeView {


    public static final String RES_RECIPE_ID = "id";

    private String recipeId;

    private Recipe recipe;

    private IngredientAdapter ingredientAdapter;

    private RecipePresenter presenter;

    private MemberAdapter memberAdapter;

    private ProgressBar progressBar;

    private TextView recipeName;

    private TextView creatorName;

    private Button updateButton;

    private MemberIngredients addedIngredients;

    private HashMap<String, AddedIngredient> ingredientsOnLoad;


    private TextView description;

    private TextView creatorPhone;
    private ImageButton callCreator;

    private TextView recipeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        //check for null
        ingredientsOnLoad = new HashMap<>();
        //Прилетело от RecipeListActivity
        recipeId = intent.getStringExtra(RES_RECIPE_ID);
        initView();
    }




    private void onCall(String number) {
        Log.println(Log.DEBUG, "Test", "onCall");
        String num = "tel:" + number.trim();
        //Action call
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(num)));
    }

    private void initView() {
        presenter = PresenterFactory.createPresenter(this);


        addedIngredients = new MemberIngredients();
        addedIngredients.setIngredients(new ArrayList<AddedIngredient>());

        //TODO static Pety
        addedIngredients.setUser(new User("1", "Петя"));


        recipeName = findViewById(R.id.recipe_name);
        creatorName = findViewById(R.id.creator_info);
        description = findViewById(R.id.recipe_description);
        progressBar = findViewById(R.id.progress_bar);
        updateButton = findViewById(R.id.update_recipe_btn);
        recipeStatus = findViewById(R.id.recipe_status);

        creatorPhone = findViewById(R.id.creator_phone_text);
        callCreator = findViewById(R.id.call_creator);

        callCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall(creatorPhone.getText().toString());
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecipeActivity.this, RecipesListActivity.class));
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ingredientsOnLoad.isEmpty()){
                    List<AddedIngredient> ing =
                            new ArrayList<>(ingredientsOnLoad.values());
                    addedIngredients.setIngredients(ing);

                    presenter.updateRecipe(addedIngredients, recipeId);
                }
                //addedIngredients.getIngredients()
            }
        });

        RecyclerView ingredients = findViewById(R.id.ingredients_view);
        ingredientAdapter = new IngredientAdapter(this, new IngredientAdapter.IngredientListener() {
            @Override
            public String onAddIngredient(Ingredient ingredient, int count) {
                Log.println(Log.DEBUG, "Test", "add" + count);
                if (ingredient.getCountHave().equals(ingredient.getCountNeed())) {
                    return "0";
                }


                if (checkForIngredients(ingredient, count)) {
                    int inStock = Integer.valueOf(ingredient.getCountHave());

                    int need = Integer.valueOf(ingredient.getCountNeed());

                    Log.println(Log.DEBUG, "Test", "Need: " + need + " Have: " + inStock);

                    int total = 0, added = 0;
                    if ((need - count - inStock) <= 0) {
                        total = need;
                        added = need - inStock;
                    } else {
                        total = inStock + count;
                        added = count;
                    }

                    //ingredient.setCountHave(String.valueOf(added));
                    Log.println(Log.DEBUG, "Test", "Fly count " + added);
                    AddedIngredient ing =  new AddedIngredient(String.valueOf(added), ingredient.getName());
                    ingredientsOnLoad.put(ingredient.getName(), ing);

                    //addedIngredients.getIngredients().add(new AddedIngredient(String.valueOf(added), ingredient.getName()));

                    removeIngredientFromStock(ingredient, added);

                    return String.valueOf(added);



                   /*List<ExceptionLifeCycleDataBean> beans =
                            new ArrayList<ExceptionLifeCycleDataBean>(transitionHash.values());*/


                    //updateRecipe(recipe);


                    //DEB
                    //we do not have any session id
                    /*String id = "0";
                    boolean has = false;
                    for (User user: recipe.getMembers()){
                        if(user.getUserId().equals(id)){
                            has = true;
                            break;
                        }
                    }

                    if(!has){
                        //again, we do not have session id yet
                        recipe.getMembers().total(new User("0", "Name"));
                    }*/

                    //presenter.updateRecipe(recipe);
                    //presenter.loadMembersList();

                }
                //TODO Сюда прилетает число ингридиентов которое ты хочешь добавить
                //в рецепт,
                return "0";
            }

            @Override
            public void onError(String message) {
                Toast.makeText(RecipeActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });


        ingredients.setAdapter(ingredientAdapter);
        ingredients.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView members = findViewById(R.id.members_view);
        memberAdapter = new MemberAdapter(this, new MemberAdapter.MemberListener() {
            @Override
            public void onCall(String number) {
                RecipeActivity.this.onCall(number);
            }
        });


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
    public void onLoadMembersIngredients(List<MemberIngredients> membersIngredients) {
        //TODO добавить в member item информацию о добавленных ингредиентах
        List<User> members = new ArrayList<>();

        for(MemberIngredients memberIngredients : membersIngredients){
            members.add(memberIngredients.getUser());
            Log.println(Log.DEBUG, "Test", "Member found:" + memberIngredients.getUser().getName());

            for(AddedIngredient added : memberIngredients.getIngredients()){
                for (Ingredient ingredient : recipe.getIngredients()){
                    if(0 == added.getName().compareTo(ingredient.getName())){
                        //???????????????? crazy
                         //int value = Integer.valueOf(ingredient.getCountHave())   +   Integer.valueOf(added.getCount());
                         ingredient.setCountHave(added.getCount());
                    }

                }
            }
        }
        memberAdapter.setItems(members);
        onLoadRecipe(recipe);


        //ingredientAdapter.setItems(recipe.getIngredients());
        //memberAdapter.setItems(recipe.getMembers());
    }

    @Override
    public void onLoadRecipe(Recipe recipe) {
        this.recipe = recipe;
        creatorName.setText(recipe.getCreator().getName());
        recipeName.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        ingredientAdapter.setItems(recipe.getIngredients());
        recipeStatus.setText(recipe.getStatus());

        //TODO dsfdsfdsfdsfds
        //TODO выпилить при серваке
        //memberAdapter.setItems(recipe.getMembers());
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
