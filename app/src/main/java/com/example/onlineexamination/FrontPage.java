/*
This class allows an user to connect as User
*/
package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineexamination.SQL.DatabaseHandler;
import com.example.onlineexamination.SQL.Information;

import java.util.List;

public class FrontPage extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    CheckBox box;
    TextView signup;
    TextView terms;
    String intentValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        intentValue = getIntent().getStringExtra("user"); //Get user type from MainActivity (User)


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        box = findViewById(R.id.box);
        signup = findViewById(R.id.signup);
        terms = findViewById(R.id.terms);


        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Click on login
        // Check authentication for User
        //if Login is successful, go to WelcomPage
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler databaseHandler = new DatabaseHandler(FrontPage.this);

                List<Information> listInfo = databaseHandler.getAllDETAILS();


                if (username.getText().toString().trim().equalsIgnoreCase("")) {
                    username.setError("enter a valid username");
                } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError("Incorrect password");
                } else if (!box.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please agree to the terms and conditions.", Toast.LENGTH_SHORT).show();
                } else {

                    for (Information information : listInfo) {

                        if (information.getEmail() != null && information.getValue() != null) {
                            if (information.getEmail().equals(username.getText().toString()) && information.getValue().equals("User")) {
                                if (information.getPassword().equals(password.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "Login succesful ", Toast.LENGTH_SHORT).show();

                                    SharedPreferences.Editor editor = getSharedPreferences("myPre2", MODE_PRIVATE).edit();
                                    editor.putBoolean("login1True",true);
                                    editor.apply();

                                    Intent intent = new Intent(FrontPage.this, WelcomePage.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                } else {
                                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                                }

                            }

                        }
                    }
                }
                box.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

                        terms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toast.makeText(getApplicationContext(),"Please enter a valid user name",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FrontPage.this, TermsAndConditions.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    //Go to Sign up class
    public void signUpClick(View view) {

        Intent intent = new Intent(FrontPage.this, SignupPage.class);
        intent.putExtra("user","User" );
        startActivity(intent);
    }
}

