//This class manage the home page, two options to choose, Admin or User
package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button user;
    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        user = findViewById(R.id.user);
        admin = findViewById(R.id.admin);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if User is already logged in, go to WelcomePage
                //else, go to FrontPage to log in
                SharedPreferences prefs = getSharedPreferences("myPre2", MODE_PRIVATE);
                boolean restoredText = prefs.getBoolean("login1True", false);

                Intent intent;
                if (restoredText) {
                    intent = new Intent(MainActivity.this, WelcomePage.class);

                } else {
                    intent = new Intent(MainActivity.this, FrontPage.class);
                    intent.putExtra("user", "User");
                }
                startActivity(intent);
            }
        });


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences("myPre", MODE_PRIVATE);
                boolean restoredText = prefs.getBoolean("loginTrue", false);

                Intent intent;
                if (restoredText) {
                    //if admin is already logged in, go to ChoicePage
                    intent = new Intent(MainActivity.this, ChoicePage.class);

                } else {
                    //else go to AdminLogin
                    intent = new Intent(MainActivity.this, AdminLogin.class);
                    intent.putExtra("user", "Admin");
                }
                startActivity(intent);

            }
        });

    }

}



