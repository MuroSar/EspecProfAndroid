package com.curso.especprofandroid.data.database;

import com.curso.especprofandroid.data.database.entity.UserEntity;
import com.curso.especprofandroid.data.element.User;
import com.curso.especprofandroid.util.StringUtils;

import io.realm.Realm;

import static com.curso.especprofandroid.data.database.FieldNames.USER_EMAIL;
import static com.curso.especprofandroid.data.database.FieldNames.USER_ID;
import static com.curso.especprofandroid.data.database.FieldNames.USER_PASSWORD;

public class RealmDB implements RealmContract{

    public boolean login(User user) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(UserEntity.class)
                .equalTo(USER_EMAIL, user.getEmail())
                .and()
                .equalTo(USER_PASSWORD, user.getPassword())
                .findFirst() != null;
    }

    public boolean existEmail(String email) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(UserEntity.class)
                .equalTo(USER_EMAIL, StringUtils.trimEnd(email))
                .findFirst() != null;
    }

    public long createAccount(User user) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Number maxValue = realm.where(UserEntity.class).max(USER_ID);
        long pk = (maxValue != null) ? maxValue.longValue() + 1 : 0;

        UserEntity entity = realm.createObject(UserEntity.class, pk++);
        entity.setEmail(StringUtils.trimEnd(user.getEmail()));
        entity.setPassword(user.getPassword());
        entity.setName(StringUtils.trimEnd(user.getName()));
        entity.setLastname(StringUtils.trimEnd(user.getLastname()));
        entity.setDateOfBirth(user.getDateOfBirth());

        realm.insertOrUpdate(entity);
        realm.commitTransaction();

        return pk;
    }
}
