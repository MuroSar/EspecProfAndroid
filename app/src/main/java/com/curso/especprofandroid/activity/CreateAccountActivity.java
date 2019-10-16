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
import com.curso.especprofandroid.logic.CreateAccountLogic;
import com.curso.especprofandroid.util.CreateAccountOption;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.curso.especprofandroid.activity.MainActivity.USER_ID;
import static com.curso.especprofandroid.util.StringUtils.EMPTY;

public class CreateAccountActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_create_account_name)
    EditText name;
    @BindView(R.id.text_create_account_name_error)
    TextView nameError;
    @BindView(R.id.edit_text_create_account_lastname)
    EditText lastname;
    @BindView(R.id.text_create_account_lastname_error)
    TextView lastnameError;
    @BindView(R.id.edit_text_create_account_email)
    EditText email;
    @BindView(R.id.text_create_account_email_error)
    TextView emailError;
    @BindView(R.id.edit_text_create_account_password)
    EditText password;
    @BindView(R.id.text_create_account_password_error)
    TextView passwordError;
    @BindView(R.id.edit_text_create_account_dob)
    EditText dob;
    @BindView(R.id.text_create_account_dob_error)
    TextView dobError;
    @BindView(R.id.text_create_account_general_error)
    TextView generalError;

    private CreateAccountLogic logic;
    private RealmContract realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        realm = new RealmDB();

        logic = new CreateAccountLogic(realm);
    }

    @Override
    protected void onResume() {
        super.onResume();

        name.setText(EMPTY);
        lastname.setText(EMPTY);
        email.setText(EMPTY);
        password.setText(EMPTY);
        dob.setText(EMPTY);

        nameError.setVisibility(View.GONE);
        lastnameError.setVisibility(View.GONE);
        emailError.setVisibility(View.GONE);
        passwordError.setVisibility(View.GONE);
        dobError.setVisibility(View.GONE);
        generalError.setVisibility(View.GONE);
    }

    private void updateUI(CreateAccountOption createAccountOption) {
        switch (createAccountOption) {
            case NAME_OK: {
                hideError(nameError);
                hideError(generalError);
                break;
            }
            case NAME_ERROR: {
                showError(nameError);
                break;
            }
            case LASTNAME_OK: {
                hideError(lastnameError);
                hideError(generalError);
                break;
            }
            case LASTNAME_ERROR: {
                showError(lastnameError);
                break;
            }
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
            case DOB_OK: {
                hideError(dobError);
                hideError(generalError);
                break;
            }
            case DOB_ERROR: {
                showError(dobError);
                break;
            }
            case ALL_FIELDS_OK: {
                hideError(nameError);
                hideError(lastnameError);
                hideError(emailError);
                hideError(passwordError);
                hideError(dobError);
                hideError(generalError);
                updateUI(logic.createAccount(name.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString(), dob.getText().toString()));
                break;
            }
            case ALL_FIELDS_ERROR: {
                showError(nameError);
                showError(lastnameError);
                showError(emailError);
                showError(passwordError);
                showError(dobError);
                break;
            }
            case CREATE_ACCOUNT_OK: {
                hideError(nameError);
                hideError(lastnameError);
                hideError(emailError);
                hideError(passwordError);
                hideError(dobError);
                hideError(generalError);
                startActivity(new Intent(this, MainActivity.class).putExtra(USER_ID, logic.getJustCreatedUserId()));
                break;
            }
            case CREATE_ACCOUNT_ERROR: {
                generalError.setVisibility(View.VISIBLE);
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

    @OnTextChanged(R.id.edit_text_create_account_name)
    protected void onNameTextChange(CharSequence text) {
        updateUI(logic.onNameTextChange(text.toString()));
    }

    @OnTextChanged(R.id.edit_text_create_account_lastname)
    protected void onLastnameTextChange(CharSequence text) {
        updateUI(logic.onLastnameTextChange(text.toString()));
    }

    @OnTextChanged(R.id.edit_text_create_account_email)
    protected void onEmailTextChange(CharSequence text) {
        updateUI(logic.onEmailTextChange(text.toString()));
    }

    @OnTextChanged(R.id.edit_text_create_account_password)
    protected void onPasswordTextChange(CharSequence text) {
        updateUI(logic.onPasswordTextChange(text.toString()));
    }

    @OnTextChanged(R.id.edit_text_create_account_dob)
    protected void onDobTextChange(CharSequence text) {
        updateUI(logic.onDobTextChange(text.toString()));
    }

    @OnClick(R.id.button_create_account_create_account)
    public void onCreateAccountButtonPressed() {
        updateUI(logic.onCreateAccountButtonPressed());
    }
}
