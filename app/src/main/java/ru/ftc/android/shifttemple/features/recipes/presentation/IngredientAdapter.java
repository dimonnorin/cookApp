package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;

public final class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder>
{
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final LayoutInflater inflater;
    private final IngredientListener ingredientListener;

    IngredientAdapter(Context context, IngredientListener ingredientListener) {
        inflater = LayoutInflater.from(context);
        this.ingredientListener = ingredientListener;
    }

    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater
                .inflate(R.layout.ingredient_item, parent, false);
        return new IngredientHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientHolder ingredientHolder, int position) {
        ingredientHolder.bind(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setItems(Collection<Ingredient> tweets) {
        ingredients.addAll(tweets);
        notifyDataSetChanged();
    }

    public void removeItem(Ingredient ingredient){
        ingredients.remove(ingredient);
        notifyDataSetChanged();
    }

    public void clearItems() {
        ingredients.clear();
        notifyDataSetChanged();
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }


    class IngredientHolder extends RecyclerView.ViewHolder {
        private final EditText ingredientNameView;
        private final EditText ingredientCountView;
        private final ImageButton deleteButton;


        IngredientHolder(View view) {
            super(view);
            ingredientNameView = view.findViewById(R.id.ingredient_name);
            ingredientCountView = view.findViewById(R.id.ingredient_count);
            deleteButton = view.findViewById(R.id.delete_ingredient_button);
        }
        void bind (final Ingredient ingredient) {
            ingredientNameView.setText(ingredient.getName());
            ingredientCountView.setText("1");

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingredientListener.onDeleteIngredient(ingredient);
                }
            });
        }
    }


    interface IngredientListener{
        void onDeleteIngredient(Ingredient ingredient);
    }
}