package com.curso.especprofandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.curso.especprofandroid.R;
import com.curso.especprofandroid.logic.LoginLogic;
import com.curso.especprofandroid.util.LoginOption;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_login_email)
    EditText email;
    @BindView(R.id.edit_text_login_email_error)
    TextView emailError;
    @BindView(R.id.edit_text_login_password)
    EditText password;
    @BindView(R.id.edit_text_login_password_error)
    TextView passwordError;
    @BindView(R.id.edit_text_login_general_error)
    TextView generalError;

    private LoginLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        logic = new LoginLogic();
    }

    private void updateUI(LoginOption loginOption) {
        switch (loginOption) {
            case EMAIL_OK: {
                emailError.setVisibility(View.GONE);
                generalError.setVisibility(View.GONE);
                break;
            }
            case EMAIL_ERROR: {
                emailError.setVisibility(View.VISIBLE);
                break;
            }
            case PASSWORD_OK: {
                passwordError.setVisibility(View.GONE);
                generalError.setVisibility(View.GONE);
                break;
            }
            case PASSWORD_ERROR: {
                passwordError.setVisibility(View.VISIBLE);
                break;
            }
            case BOTH_FIELDS_OK: {
                emailError.setVisibility(View.GONE);
                passwordError.setVisibility(View.GONE);
                generalError.setVisibility(View.GONE);
                updateUI(logic.login(email.getText().toString(), password.getText().toString()));
                break;
            }
            case BOTH_FIELDS_ERROR: {
                emailError.setVisibility(View.VISIBLE);
                passwordError.setVisibility(View.VISIBLE);
                break;
            }
            case LOGGED_OK: {
                emailError.setVisibility(View.GONE);
                passwordError.setVisibility(View.GONE);
                generalError.setVisibility(View.GONE);
                startActivity(new Intent(this, MainActivity.class));
                break;
            }
            case LOGGED_ERROR: {
                generalError.setVisibility(View.VISIBLE);
                break;
            }
            default: {
                break;
            }
        }
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
