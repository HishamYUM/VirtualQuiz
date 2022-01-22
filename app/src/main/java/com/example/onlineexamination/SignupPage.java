package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineexamination.SQL.DatabaseHandler;
import com.example.onlineexamination.SQL.Information;

import java.util.List;

public class SignupPage extends AppCompatActivity {
    EditText name, email, password, password2;
    RadioButton male, female;
    Button submit;
    CheckBox box;
    String intentValue = "";
    TextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        intentValue = getIntent().getStringExtra("user");

        final DatabaseHandler databaseHandler = new DatabaseHandler(this);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        box = findViewById(R.id.box);
        email = findViewById(R.id.email);
        password2 = findViewById(R.id.password2);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        terms = findViewById(R.id.terms);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean emailValue = false;

                if (name.getText().toString().trim().equals("")) {
                    name.setError("enter a valid username");
                } else if (email.getText().toString().trim().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError("Enter a valid email id");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("Enter password");
                } else if (password2.getText().toString().trim().equals("")) {
                    password2.setError("Enter re-password");
                } else if (!password.getText().toString().equals(password2.getText().toString())) {
                    password2.setError("Enter Valid password");
                } else if (!box.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please agree to the terms and conditions.", Toast.LENGTH_SHORT).show();
                }

                 else  {
                   List<Information> listInfo = databaseHandler.getAllDETAILS();

                    for (Information information : listInfo) {
                        if (information.getEmail().equals(email.getText().toString().trim())) {
                            emailValue = true;
                        }

                    }
                    if (emailValue) {
                        Toast.makeText(getApplicationContext(), "Email Already Exists!", Toast.LENGTH_SHORT).show();
                    } else {

                        databaseHandler.addinformation(new Information(name.getText().toString(), email.getText().toString(),
                                password.getText().toString(), intentValue));

                        if (intentValue.equals("User")) {
                            Intent i = new Intent(SignupPage.this, FrontPage.class);
                            startActivity(i);

                        } else {
                            Intent i = new Intent(SignupPage.this, AdminLogin.class);
                            startActivity(i);

                        }
                        finish();

                    }
                }
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        }
                });
                male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        }
                });
                female.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        }
                });
                terms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SignupPage.this, TermsAndConditions.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}


