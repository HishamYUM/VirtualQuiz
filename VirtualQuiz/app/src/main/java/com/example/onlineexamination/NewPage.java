/*
This class is used when an admin adds the first question, it provides three options:
-Add New Question
-Leave
-Log Out
*/
package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewPage extends AppCompatActivity {
    String intentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        intentValue = getIntent().getStringExtra("course");

        Button logout = findViewById(R.id.logout);
        Button newques = findViewById(R.id.newques);
        Button leave = findViewById(R.id.leave);

        // if Logout is clicked,
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            //Make sure to logout
            public void onClick(View view) {
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(NewPage.this).create();
                alertDialog.setTitle("logout");
                alertDialog.setMessage(" Are you sure you want to logout ? ");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SharedPreferences.Editor editor = getSharedPreferences("myPre", MODE_PRIVATE).edit();
                                editor.putBoolean("loginTrue",false);
                                editor.apply();
                                Intent cold = new Intent(NewPage.this,MainActivity.class);
                                startActivity(cold);
                                finish();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }
        });
        //if Add New Question is clicked then,
        //Go to AddQuestions class and use the current course to add new questions
        newques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewPage.this,AddQuestions.class);
                intent.putExtra("course",intentValue);
                startActivity(intent);

            }
        });

        //if Leave is clicked, then go back to MainActivity
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NewPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
