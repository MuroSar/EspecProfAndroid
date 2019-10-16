package com.curso.especprofandroid.logic;

import com.curso.especprofandroid.data.database.RealmContract;
import com.curso.especprofandroid.data.element.User;
import com.curso.especprofandroid.util.LoginOption;

public class LoginLogic {

    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;

    private RealmContract realm;

    public LoginLogic(RealmContract realm) {
        this.realm = realm;
    }

    public LoginOption onEmailTextChange(String email) {
        if (email.isEmpty()) {
            isEmailValid = false;
            return LoginOption.EMAIL_ERROR;
        }
        isEmailValid = true;
        return LoginOption.EMAIL_OK;
    }

    public LoginOption onPasswordTextChange(String password) {
        if (password.isEmpty()) {
            isPasswordValid = false;
            return LoginOption.PASSWORD_ERROR;
        }
        isPasswordValid = true;
        return LoginOption.PASSWORD_OK;
    }

    public LoginOption onLoginButtonPressed() {
        if (!isEmailValid && !isPasswordValid) {
            return LoginOption.BOTH_FIELDS_ERROR;
        }
        if (!isEmailValid) {
            return LoginOption.EMAIL_ERROR;
        }
        if (!isPasswordValid) {
            return LoginOption.PASSWORD_ERROR;
        }

        return LoginOption.BOTH_FIELDS_OK;
    }

    public LoginOption login(String email, String password) {
        if (realm.login(new User(email, password))) {
            return LoginOption.LOGGED_OK;
        } else {
            return LoginOption.LOGGED_ERROR;
        }
    }
}

