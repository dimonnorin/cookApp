package ru.ftc.android.shifttemple.features.recipes.domain.model;

import java.util.List;

public class ShortRecipe {
    private String description;
    private int status;
    private String title;
    private String id;

    public ShortRecipe(String title){
        this.title = title;
    }

    /*public List<String> getIngredients() {
        return ingredients;
    }*/

    public ShortRecipe()
    {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
