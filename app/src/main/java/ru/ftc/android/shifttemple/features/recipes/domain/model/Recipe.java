package ru.ftc.android.shifttemple.features.recipes.domain.model;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;

public class Recipe extends ShortRecipe {


    private User creator;

    private List<Ingredient> ingredients;

    private List<User> members;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
