package com.curso.especprofandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.curso.especprofandroid.R;
import com.curso.especprofandroid.data.database.RealmContract;
import com.curso.especprofandroid.data.database.RealmDB;
import com.curso.especprofandroid.logic.LoginLogic;
import com.curso.especprofandroid.util.LoginOption;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.curso.especprofandroid.util.StringUtils.EMPTY;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_login_email)
    EditText email;
    @BindView(R.id.text_login_email_error)
    TextView emailError;
    @BindView(R.id.edit_text_login_password)
    EditText password;
    @BindView(R.id.text_login_password_error)
    TextView passwordError;
    @BindView(R.id.text_login_general_error)
    TextView generalError;

    private LoginLogic logic;
    private RealmContract realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        realm = new RealmDB();

        logic = new LoginLogic(realm);
    }

    @Override
    protected void onResume() {
        super.onResume();

        email.setText(EMPTY);
        password.setText(EMPTY);

        hideError(emailError);
        hideError(passwordError);
        hideError(generalError);
    }

    private void updateUI(LoginOption loginOption) {
        switch (loginOption) {
            case EMAIL_OK: {
                hideError(emailError);
                hideError(generalError);
                break;
            }
            case EMAIL_ERROR: {
                showError(emailError);
                break;
            }
            case PASSWORD_OK: {
                hideError(passwordError);
                hideError(generalError);
                break;
            }
            case PASSWORD_ERROR: {
                showError(passwordError);
                break;
            }
            case BOTH_FIELDS_OK: {
                hideError(emailError);
                hideError(passwordError);
                hideError(generalError);
                updateUI(logic.login(email.getText().toString(), password.getText().toString()));
                break;
            }
            case BOTH_FIELDS_ERROR: {
                showError(emailError);
                showError(passwordError);
                break;
            }
            case LOGGED_OK: {
                hideError(emailError);
                hideError(passwordError);
                hideError(generalError);
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case LOGGED_ERROR: {
                showError(generalError);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void showError(TextView textView) {
        textView.setVisibility(View.VISIBLE);
    }

    private void hideError(TextView textView) {
        textView.setVisibility(View.GONE);
    }

    @OnTextChanged(R.id.edit_text_login_email)
    protected void onEmailTextChange(CharSequence text) {
        updateUI(logic.onEmailTextChange(text.toString()));
    }

    @OnTextChanged(R.id.edit_text_login_password)
    protected void onPasswordTextChange(CharSequence text) {
        updateUI(logic.onPasswordTextChange(text.toString()));
    }

    @OnClick(R.id.button_login_login)
    public void onLoginButtonPressed() {
        updateUI(logic.onLoginButtonPressed());
    }

    @OnClick(R.id.button_login_create_account)
    public void onCreateAccountButtonPressed() {
        startActivity(new Intent(this, CreateAccountActivity.class));
    }
}
