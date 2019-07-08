package ru.ftc.android.shifttemple.features.recipes.presentation;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/*import com.example.recipe.model.Ingredient;
import com.example.recipe.model.IngredientAdapter;
import com.example.recipe.model.Recipe;*/

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesApi;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesDataSource;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesDataSourceImpl;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepository;
import ru.ftc.android.shifttemple.features.recipes.data.RecipesRepositoryImpl;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractor;
import ru.ftc.android.shifttemple.features.recipes.domain.RecipesInteractorImpl;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;
import ru.ftc.android.shifttemple.network.Carry;

public class CreateRecipeActivity extends AppCompatActivity {
    //TODO check for empty name, ingredients count
    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private List<Ingredient> ingredients;
    private Recipe recipe;
    private RecipesInteractor interactor;



    //DEB
    final private Gson gson = new Gson();
    final public static int GET_RECIPE_CODE = 1;
    final public static String RESOURCE_NAME = "recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        recipe = new Recipe();
        ingredients = new ArrayList<>();
        initView();
    }




    public void onClick(View view) {
        EditText editName = findViewById(R.id.name);
        EditText editDescription = findViewById(R.id.description);
        EditText editIngredientsName = findViewById(R.id.ingredient_name);
        EditText editIngredientsCount = findViewById(R.id.ingredient_count);
        recipe.setTitle(editName.getText().toString());
        recipe.setDescription(editDescription.getText().toString());
        ingredients = adapter.getIngredients();


        for (int i = 0; i < ingredients.size(); ++i) {
            ingredients.get(i).setName(editIngredientsName.getText().toString());
            ingredients.get(i).setCount(Integer.parseInt(editIngredientsCount.getText().toString()));
            //recipe.setIngredients(ingredients.get(i));
        }
        recipe.setIngredients(ingredients);


        //TODO когда появиться сервер
        //
        Log.println(Log.DEBUG, "Test", "send created Recipe");
        interactor.createRecipe(recipe, new Carry<Recipe>(){
            @Override
            public void onSuccess(Recipe result) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        //DEB
        Intent intent = new Intent();
        intent.putExtra(RESOURCE_NAME,gson.toJson((recipe)));
        setResult(RESULT_OK, intent);
        finish();


    }

    private void initView() {
        adapter = new IngredientAdapter(this);
        recyclerView = findViewById(R.id.ingredients);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecipesApi api = App.getRetrofitProvider(this)
                .getRetrofit()
                .create(RecipesApi.class);//создаем api а основе возможных запросов и url


        //api.getRecipe("fg").execute();execute immediately
        //запросы с RestApi
        final RecipesDataSource dataSource = new RecipesDataSourceImpl(api);
        final RecipesRepository repository = new RecipesRepositoryImpl(dataSource);
        interactor = new RecipesInteractorImpl(repository);
    }

    public void loadIngredients() {
        adapter.setItems(ingredients);
    }

    /*private Collection<Ingredient> getMembers(String s) {
        return Arrays.asList(
                new Ingredient(s)
        );
    }*/

    private final int ID_CHECK_PRODUCTS = 0;

    AlertDialog.Builder builder;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case ID_CHECK_PRODUCTS:
                final int count = 19;
                final boolean[] mCheckedItems = new boolean[count];
                for (int i = 0; i < count; ++i){
                    mCheckedItems[i] = false;
                }
                final String[] checkProductsName = { "Картофель", "Гречка", "Рис",
                        "Лук", "Чеснок", "Морковь", "Перец", "Соль", "Укроп", "Свинина",
                        "Хлеб", "Молоко", "Сыр", "Творог", "Помидоры", "Огурцы", "Яюлоки", "Сахар"};
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Выберите продукты")
                        .setCancelable(false)
                        .setMultiChoiceItems(checkProductsName, mCheckedItems,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {
                                        mCheckedItems[which] = isChecked;
                                    }
                                })
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        ingredients.clear();
                                        for (int i = 0; i < checkProductsName.length; i++){
                                            if (mCheckedItems[i]){
                                                ingredients.add(new Ingredient(checkProductsName[i]));
                                            }
                                        }
                                        loadIngredients();
                                    }})
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }});
        }
        return builder.create();
    }

    public void onAddIngredient(View view){
        Dialog dialog = onCreateDialog(ID_CHECK_PRODUCTS);
        dialog.show();
    }
    public void onClickDelete(View view){
        adapter.clearItems();
    }
}