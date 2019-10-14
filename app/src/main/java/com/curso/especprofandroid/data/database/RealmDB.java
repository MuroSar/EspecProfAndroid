package com.curso.especprofandroid.data.database;

import com.curso.especprofandroid.data.database.entity.UserEntity;
import com.curso.especprofandroid.data.element.User;

import io.realm.Realm;

import static com.curso.especprofandroid.data.database.FieldNames.USER_EMAIL;
import static com.curso.especprofandroid.data.database.FieldNames.USER_PASSWORD;

public class RealmDB {

    public static boolean login(User user) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(UserEntity.class)
                .equalTo(USER_EMAIL, user.getEmail())
                .and()
                .equalTo(USER_PASSWORD, user.getPassword())
                .findFirst() != null;
    }
}
