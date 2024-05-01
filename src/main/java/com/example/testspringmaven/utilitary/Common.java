package com.example.testspringmaven.utilitary;

import com.example.testspringmaven.persistant.UsersEntity;

public class Common {
    private static UsersEntity users;

    public static UsersEntity getUsers() {
        return users;
    }

    public static void setUsers(UsersEntity users) {
        Common.users = users;
    }
}
