package ru.ftc.android.shifttemple.features.login.domain.model;

public final class User {

    private String userId;
    private String name;

    public void setUserId(String sessionId) {
        this.userId = sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User(){

    }

    public User(String sessionId, String name) {
        this.userId = sessionId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}