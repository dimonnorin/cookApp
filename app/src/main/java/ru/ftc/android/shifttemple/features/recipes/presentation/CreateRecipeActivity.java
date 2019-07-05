package ru.ftc.android.shifttemple.features.recipes.presentation;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

/*import com.example.recipe.model.Ingredient;
import com.example.recipe.model.IngredientAdapter;
import com.example.recipe.model.Recipe;*/

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Recipe;

public class CreateRecipeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private List<Ingredient> list;
    private Recipe recipe = new Recipe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        initView();

    }

    public void onClick(View view) {
        EditText editName = findViewById(R.id.name);
        EditText editDescription = findViewById(R.id.description);
        EditText editIngredientsName = findViewById(R.id.ingredient_name);
        EditText editIngredientsCount = findViewById(R.id.ingredient_count);
        recipe.setTitle(editName.getText().toString());
        recipe.setDescription(editDescription.getText().toString());
        list = adapter.getIngredients();

        Intent ini = new Intent();
        
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).setName(editIngredientsName.getText().toString());
            list.get(i).setCount(Integer.parseInt(editIngredientsCount.getText().toString()));
            //recipe.setIngredients(list.get(i));
        }
        recipe.setIngredients(list);
    }

    private void initView() {
        adapter = new IngredientAdapter();
        recyclerView = findViewById(R.id.ingredient);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void loadIngredients(String s) {
        Collection<Ingredient> ingredients = getIngredients(s);
        adapter.setItems(ingredients);
    }

    private Collection<Ingredient> getIngredients(String s) {
        return Arrays.asList(
                new Ingredient(s)
        );
    }

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
                                        for (int i = 0; i < checkProductsName.length; i++){
                                            if (mCheckedItems[i])
                                                loadIngredients(checkProductsName[i]);
                                        }
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

    public void onButtonClick(View view){
        Dialog dialog = onCreateDialog(ID_CHECK_PRODUCTS);
        dialog.show();
    }
    public void onClickDelete(View view){
        adapter.clearItems();
    }
}