package ru.ftc.android.shifttemple.features.recipes.domain.model;

public class Ingredient {
    private String name;
    private int need;
    private int inStock;

    public Ingredient(String name){
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getName(){
        return name;
    }
    public int getCount(){
        return need;
    }



    public void setName(String name){this.name = name;}
    public void setCount(int count){
        need = count;}
}