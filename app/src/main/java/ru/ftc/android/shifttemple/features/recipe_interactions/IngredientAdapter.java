package ru.ftc.android.shifttemple.features.recipe_interactions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


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
                .inflate(R.layout.ingredient_present_item, parent, false);
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
        ingredients.clear();
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
        private final TextView ingredientName;
        private final TextView ingredientRelation;
        private final ImageButton buttonAdd;
        private final EditText addCount;


        IngredientHolder(View view) {
            super(view);
            ingredientName = view.findViewById(R.id.ingredient_name);
            ingredientRelation = view.findViewById(R.id.count_relation);
            buttonAdd = view.findViewById(R.id.add_ingredient_button);
            addCount = view.findViewById(R.id.add_count);

            //button = view.findViewById(R.id.delete_ingredient_button);
        }
        void bind (final Ingredient ingredient) {
            ingredientName.setText(ingredient.getName());
            addCount.setText("1");
            String relation = ingredient.getInStock() + "/" + ingredient.getCount();
            ingredientRelation.setText(relation);

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*if(addCount.getText()== null){
                        Log.println(Log.WARN, "Test", "null");
                        addCount.setText("0");
                        return;
                    }*/
                    String value = addCount.getText().toString();

                    try {
                        ingredientListener.onAddIngredient(ingredient, Integer.parseInt(value));
                    }catch (NumberFormatException exc){
                        ingredientListener.onError("Incorrect count number.");
                        addCount.setText("1");
                    }
                }
            });

        }

    }


    interface IngredientListener{


        void onAddIngredient(Ingredient ingredient, int count);

        void onError(String message);

    }



}