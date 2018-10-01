package kozak.labs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ValidationActivity extends AppCompatActivity {

    private static final String PHONE_NUMBER_REGEX = "^[+]?[0-9]{10,13}$";
    private static final String NAME_REGEX = "[A-Z][a-z]{1,24}";
    Button submitButton, viewListButton;
    EditText firstName, lastName, email, phone, password, confirmPassword;
    TextView errorList;

    private int maxLength = 25;
    private SharedPreferences preferences;

    private StringBuilder listOfErrors = new StringBuilder();

    private Boolean firstNameValidation, lastNameValidation, emailValidation, phoneValidation, passValidation, passError;

    private ArrayList<String> recordsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);

        errorList =  findViewById(R.id.error_list);
        submitButton = findViewById(R.id.submitButton);
        viewListButton = findViewById(R.id.viewListButton);

        preferences = getSharedPreferences(Constants.preference, Context.MODE_PRIVATE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValidationActivity.this, StorageActivity.class);
                startActivity(intent);
            }
        });
    }

    void validate()
    {
        listOfErrors.delete(0, listOfErrors.length());
        clearValidation();
        validateFirstName(firstName);
        validateLastName(lastName);
        validateEmail(email);
        validatePhone(phone);
        validatePassword(password, confirmPassword);
        errorList.setText(listOfErrors.toString());
        if(getValidationStatus()) {
            savePreferences();
        }
    }

    void validateFirstName(EditText firstName) {
        if (firstName.getText().length() > maxLength) {
            firstName.setError(getString(R.string.error_name_length));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_name_length)));
        } else if (!firstName.getText().toString().matches(NAME_REGEX)) {
            firstName.setError(getString(R.string.error_non_valid_name));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_lowercase_name)));
        } else {
            firstNameValidation = true;
        }
    }

    void validateLastName(EditText lastName) {
        if (lastName.getText().length() > maxLength) {
            lastName.setError(getString(R.string.error_last_name_length));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_last_name_length)));
        } else if (!lastName.getText().toString().matches(NAME_REGEX)) {
            lastName.setError(getString(R.string.error_non_valid_last_name));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_lowercase_last_name)));
        } else {
            lastNameValidation = true;
        }
    }

    void validateEmail(EditText email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError(getString(R.string.error_non_valid_email));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_invalid_email)));
        }
        else {
            emailValidation = true;
        }
    }

    void validatePhone(EditText phone){
        if (!phone.getText().toString().matches(PHONE_NUMBER_REGEX)) {
            phone.setError(getString(R.string.error_non_valid_phone));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_invalid_phone)));
        }
        else {
            phoneValidation = true;
        }
    }

    void validatePassword(EditText password, EditText confirmPassword){
        if (password.getText().toString().isEmpty()) {
            password.setError(getString(R.string.error_enter_pass));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_empty_pass)));
            passError = true;
        } else if (password.getText().length() < 8) {
            password.setError(getString(R.string.error_pass_length));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_pass_length)));
            passError = true;
        }

        if(confirmPassword.getText().toString().isEmpty()) {
            confirmPassword.setError(getString(R.string.error_empty_pass));
            listOfErrors.append(String.format("%s\n", getString(R.string.error_empty_conf_pass)));
            passError = true;
        }

        if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError(getString(R.string.error_pass_dont_match));
            listOfErrors.append(String.format("%s\n", R.string.error_pass_dont_match));
            passError = true;
        }

        if(!passError) {
            passValidation = true;
        }

    }

    void clearValidation(){
        firstNameValidation = false;
        lastNameValidation = false;
        emailValidation = false;
        phoneValidation = false;
        passValidation = false;
        passError = false;
    }

    boolean getValidationStatus() {
        return firstNameValidation & lastNameValidation & emailValidation & phoneValidation & passValidation;
    }

    void savePreferences(){
        recordsList.add(firstName.getText().toString() + "\n" + lastName.getText().toString() + "\n" + phone.getText().toString());
        Set<String> recordsSet = new HashSet<>(recordsList);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(Constants.preferenceRecord, recordsSet);
        editor.apply();
    }

}
