package ru.ftc.android.shifttemple.features.recipes.presentation;

import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.recipes.domain.model.Ingredient;

public final class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.Holder>
{
    private final List<Ingredient> ingredients = new ArrayList<>();
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setItems(Collection<Ingredient> tweets) {
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
    class Holder extends RecyclerView.ViewHolder {
        private final TextView ingredientNameView;
        private final TextView ingredientCountView;
        private final Button button;


        public Holder(View view) {
            super(view);
            ingredientNameView = view.findViewById(R.id.ingredient_name);
            ingredientCountView = view.findViewById(R.id.ingredient_count);
            button = view.findViewById(R.id.delete_ingredient_button);
        }
        void bind (final Ingredient ingredient) {
            ingredientNameView.setText(ingredient.getName());
            ingredientCountView.setText(Integer.toString(ingredient.getCount()));
        }

    }
}