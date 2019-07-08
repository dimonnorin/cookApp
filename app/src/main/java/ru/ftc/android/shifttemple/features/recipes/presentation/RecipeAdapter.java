package ru.ftc.android.shifttemple.features.recipes.presentation;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.recipes.domain.model.ShortRecipe;

final class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private final List<ShortRecipe> recipes = new ArrayList<>();
    private final LayoutInflater inflater;
    private final RecipeListener productListener;
    private final Animation clickAnimation;//TODO click anim

    RecipeAdapter(Context context, RecipeListener productListener) {
        inflater = LayoutInflater.from(context);
        this.productListener = productListener;
        clickAnimation = AnimationUtils.loadAnimation(context, R.anim.click_anim);
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO устанавливаем шаблон для каждого holder

        final View itemView = inflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeHolder(itemView, productListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<ShortRecipe> shortRecipeList) {
        recipes.clear();
        recipes.addAll(shortRecipeList);
        notifyDataSetChanged();
    }

    class RecipeHolder extends RecyclerView.ViewHolder {

        private final TextView recipeName;
        private final RecipeListener recipeListener;


        RecipeHolder(View view, RecipeListener recipeListener) {
            super(view);
            this.recipeListener = recipeListener;
            recipeName = view.findViewById(R.id.recipe_item_name);


            //bookAuthorView = view.findViewById(R.id.book_item_author);
        }
        void bind(final ShortRecipe shortRecipe) {

            recipeName.setText(shortRecipe.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recipeListener.onRecipeClick(shortRecipe);
                }
            });
            /*recipeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recipeListener.onRecipeClick(shortRecipe);
                }
            });*/


            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //recipeListener.onBookLongClick(product);
                    return true;
                }
            });*/

        }

    }
    //выпилить
    interface RecipeListener {


        void onRecipeClick(ShortRecipe shortRecipe);

        //void onDeleteRecipe(ShortRecipe shortRecipe);

        /*void onProductTypeDelete(Product product);

        void onProductAdd(Product product);

        void onProductReduce(Product product);*/

    }

}
