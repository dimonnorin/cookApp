package ru.ftc.android.shifttemple.features.recipe_interactions.model;

public class AddedIngredient {
    private String count;
    private String name;

    public AddedIngredient(){

    }

    public AddedIngredient(String count, String name) {
        this.count = count;
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
