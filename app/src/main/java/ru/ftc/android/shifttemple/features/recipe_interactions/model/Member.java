package ru.ftc.android.shifttemple.features.recipe_interactions.model;

public class Member {

    private String name;

    public Member(String name) {
        this.name = name;
    }

    Member(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
