//This class manage admin log in
package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
public class AdminLogin extends AppCompatActivity {

    public static android.content.SharedPreferences SharedPreferences = null;
    SharedPreferences sharedpreferences;

    TextView terms;
    String intentValue = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        //get selected user, Admin or User, from MainActivity
        intentValue = getIntent().getStringExtra("user");


        final EditText name = findViewById(R.id.name);
        Button log = findViewById(R.id.login);
        final EditText pw = findViewById(R.id.pw);
        final CheckBox box = findViewById(R.id.box);
        terms = findViewById(R.id.terms);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(),"password is incorrect",Toast.LENGTH_SHORT).show();
            }
        });

       //Check admin's authentication

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler databaseHandler = new DatabaseHandler(AdminLogin.this);

                List<Information> listInfo = databaseHandler.getAllDETAILS();

                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("enter a valid username");
                } else if (pw.getText().toString().trim().equalsIgnoreCase("")) {
                    pw.setError("Incorrect password");
                }
                    //Agree to terms and conditions
                   else if (!box.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please agree to the terms and conditions.", Toast.LENGTH_SHORT).show();
                } else {

                    for (Information information : listInfo) {

                        if (information.getEmail()!= null && information.getValue() != null){

                                SharedPreferences.Editor editor = getSharedPreferences("myPre", MODE_PRIVATE).edit();
                                editor.putBoolean("loginTrue",true);
                                editor.apply();
                                if (information.getEmail().equals(name.getText().toString()) && information.getValue().equals("Admin")) {
                                    if (information.getPassword().equals(pw.getText().toString())) {
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                                // if login is successful, go to ChoicePage
                                Intent intent = new Intent(AdminLogin.this, ChoicePage.class);
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
                        //read Terms and Conditions
                        Intent intent = new Intent(AdminLogin.this, TermsAndConditions.class);
                        startActivity(intent);


                    }
                });
            }


        });
    }

    public void signUpClick(View view) {
        //if admin doesn't have account, go to signup
        Intent intent = new Intent(AdminLogin.this, SignupPage.class);
        intent.putExtra("user","Admin" );
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}














