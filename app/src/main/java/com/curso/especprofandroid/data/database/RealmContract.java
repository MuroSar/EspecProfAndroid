package com.curso.especprofandroid.data.database;

import com.curso.especprofandroid.data.element.User;

public interface RealmContract {

    boolean login(User user);

    boolean existEmail(String email);

    long createAccount(User user);
}
