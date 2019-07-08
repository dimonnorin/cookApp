package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    IngredientAdapter(Context context) {
        inflater = LayoutInflater.from(context);
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
        //clear?
        ingredients.addAll(tweets);
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
        //private final Button button;


        IngredientHolder(View view) {
            super(view);
            ingredientNameView = view.findViewById(R.id.ingredient_name);
            ingredientCountView = view.findViewById(R.id.ingredient_count);
            //button = view.findViewById(R.id.delete_ingredient_button);
        }
        void bind (final Ingredient ingredient) {
            ingredientNameView.setText(ingredient.getName());
            ingredientCountView.setText(Integer.toString(ingredient.getCount()));
        }

    }
}