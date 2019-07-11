package ru.ftc.android.shifttemple.features.recipe_interactions.model;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;

public class MemberIngredients {

    List<AddedIngredient>  ingredients;

    User user;

    public List<AddedIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<AddedIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
