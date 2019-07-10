package ru.ftc.android.shifttemple.features.recipes.domain.model;

import java.util.List;

public class ShortRecipe {
    private String description;
    private String status;
    private String title;
    private String id;

    public ShortRecipe(String title){
        this.title = title;
    }

    /*public List<String> getMembers() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
