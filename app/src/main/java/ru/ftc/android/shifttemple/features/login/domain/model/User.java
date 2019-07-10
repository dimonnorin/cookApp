package ru.ftc.android.shifttemple.features.login.domain.model;

public final class User {

    private String sessionId;
    private String name;
    private String phone;


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(){

    }

    public User(String sessionId, String name) {
        this.sessionId = sessionId;
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getName() {
        return name;
    }
}