package com.curso.especprofandroid.logic;

import com.curso.especprofandroid.data.database.RealmContract;
import com.curso.especprofandroid.data.element.User;
import com.curso.especprofandroid.util.CreateAccountOption;

public class CreateAccountLogic {

    private static final long INITIAL_ID = -1;

    private boolean isNameValid = false;
    private boolean isLastnameValid = false;
    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;
    private boolean isDateOfBirthValid = false;

    private long justCreatedUserId = INITIAL_ID;

    private RealmContract realm;

    public CreateAccountLogic(RealmContract realm) {
        this.realm = realm;
    }

    public CreateAccountOption onNameTextChange(String name) {
        if (name.isEmpty()) {
            isNameValid = false;
            return CreateAccountOption.NAME_ERROR;
        }
        isNameValid = true;
        return CreateAccountOption.NAME_OK;
    }

    public CreateAccountOption onLastnameTextChange(String lastname) {
        if (lastname.isEmpty()) {
            isLastnameValid = false;
            return CreateAccountOption.LASTNAME_ERROR;
        }
        isLastnameValid = true;
        return CreateAccountOption.LASTNAME_OK;
    }

    public CreateAccountOption onEmailTextChange(String email) {
        if (email.isEmpty()) {
            isEmailValid = false;
            return CreateAccountOption.EMAIL_ERROR;
        }
        isEmailValid = true;
        return CreateAccountOption.EMAIL_OK;
    }

    public CreateAccountOption onPasswordTextChange(String password) {
        if (password.isEmpty()) {
            isPasswordValid = false;
            return CreateAccountOption.PASSWORD_ERROR;
        }
        isPasswordValid = true;
        return CreateAccountOption.PASSWORD_OK;
    }

    public CreateAccountOption onDobTextChange(String dateOfBirth) {
        if (dateOfBirth.isEmpty()) {
            isDateOfBirthValid = false;
            return CreateAccountOption.DOB_ERROR;
        }
        isDateOfBirthValid = true;
        return CreateAccountOption.DOB_OK;
    }

    public CreateAccountOption onCreateAccountButtonPressed() {
        if (!isNameValid && !isLastnameValid && !isEmailValid && !isPasswordValid && !isDateOfBirthValid) {
            return CreateAccountOption.ALL_FIELDS_ERROR;
        }
        if (!isNameValid) {
            return CreateAccountOption.NAME_ERROR;
        }
        if (!isLastnameValid) {
            return CreateAccountOption.LASTNAME_ERROR;
        }
        if (!isEmailValid) {
            return CreateAccountOption.EMAIL_ERROR;
        }
        if (!isPasswordValid) {
            return CreateAccountOption.PASSWORD_ERROR;
        }
        if (!isDateOfBirthValid) {
            return CreateAccountOption.DOB_ERROR;
        }

        return CreateAccountOption.ALL_FIELDS_OK;
    }

    public CreateAccountOption createAccount(String name, String lastname, String email, String password, String dateOfBirth) {
        if (realm.existEmail(email)) {
            return CreateAccountOption.CREATE_ACCOUNT_ERROR;
        } else {
            justCreatedUserId = realm.createAccount(new User(name, lastname, email, password, dateOfBirth));
            return CreateAccountOption.CREATE_ACCOUNT_OK;
        }
    }

    public long getJustCreatedUserId() {
        return justCreatedUserId;
    }
}
