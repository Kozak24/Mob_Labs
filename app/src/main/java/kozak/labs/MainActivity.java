package kozak.labs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String phoneNumberRegex = "^[+]?[0-9]{10,13}$";
    public static final String nameRegex = "[A-Z][a-z]{1,24}";
    private Button submitButton;
    private EditText firstName, lastName, email, phone, password, confirmPassword;
    private TextView errorList;

    private String listOfErrors = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);

        errorList = (TextView) findViewById(R.id.error_list);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOfErrors = "";
                validateName(firstName);
                validateName(lastName);
                validateEmail(email);
                validatePhone(phone);
                validatePassword(password, confirmPassword);
                errorList.setText(listOfErrors);
            }
        });
    }

    void validateName(EditText name) {
        if (name == firstName || name == lastName) {
            if (name.getText().length() > 25) {
                if (name == firstName) {
                    name.setError("First name must be less than 25 chars");
                    listOfErrors += "First name must be less than 25 characters\n";
                } else if (name == lastName) {
                    name.setError("Last name must be less than 25 chars");
                    listOfErrors += "Last name must be less than 25 characters\n";
                }
            } else if (!name.getText().toString().matches(nameRegex)) {
                if (name == firstName) {
                    name.setError("Please input a valid first name");
                    listOfErrors += "First name must start with an uppercase letter\n";
                } else {
                    name.setError("Please input a valid last name");
                    listOfErrors += "Last name must start with an uppercase letter\n";
                }
            }
        }
    }

    void validateEmail(EditText email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Please input a valid email");
            listOfErrors += "Invalid email\n";
        }
    }

    void validatePhone(EditText phone){
        if (!phone.getText().toString().matches(phoneNumberRegex)) {
            phone.setError("Please input a valid phone number");
            listOfErrors += "Invalid phone number\n";
        }
    }

    void validatePassword(EditText password, EditText confirmPassword){
        if (password.getText().toString().isEmpty()) {
            password.setError("Please enter a password");
            listOfErrors += "Password must be not empty\n";
        } else if (password.getText().length() < 8) {
            password.setError("Password must be greater than 8 characters");
            listOfErrors += "Password must have at least 8 characters\n";
        }

        if(confirmPassword.getText().toString().isEmpty()) {
            confirmPassword.setError("Please enter a password");
            listOfErrors += "Password confirmation must be not empty\n";
        }

        if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Password must match");
            listOfErrors += "Password don't match\n";
        }

    }

}
