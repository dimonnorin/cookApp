package ru.ftc.android.shifttemple.features.recipes.domain.model;

public class Ingredient {
    private String name;
    private int required;
    private int collected;
    public Ingredient(String name){
        this.name = name;
    }

    public int getCollected() {
        return collected;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public String getName(){
        return name;
    }
    public int getCount(){
        return required;
    }



    public void setName(String name){this.name = name;}
    public void setCount(int count){
        required = count;}
}
