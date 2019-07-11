package ru.ftc.android.shifttemple.features.recipes.domain.model;

public class Ingredient {
    private String name;
    private String countNeed;
    private String countHave;
    public Ingredient(String name){
        this.name = name;
    }

    public String getCountHave() {
        return countHave;
    }

    public void setCountHave(String countHave) {
        this.countHave = countHave;
    }

    public String getName(){
        return name;
    }
    public String getCountNeed(){
        return countNeed;
    }



    public void setName(String name){
        this.name = name;

    }
    public void setCountNeed(String count)

    {
        countNeed = count;}
}
