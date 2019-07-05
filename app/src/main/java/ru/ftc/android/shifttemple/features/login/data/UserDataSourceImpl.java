package ru.ftc.android.shifttemple.features.login.data;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;

public final class UserDataSourceImpl implements UserDataSource {

    @Override
    public List<User> getUserList() {
        //TODO add users
        final List<User> userList = new ArrayList<>();

        userList.add(new User("UserA", " Петя"));
        userList.add(new User("UserB", " Вася"));
        userList.add(new User("UserC", " Катя"));
        userList.add(new User("UserO", "Cat"));
        return userList;
    }
}