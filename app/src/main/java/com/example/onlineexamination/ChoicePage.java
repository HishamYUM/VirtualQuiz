/* This class is used after an admin logged in,  it provides two options:
-Add questions
-Log out
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

public class ChoicePage extends AppCompatActivity {
Button adqst;
Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_page);

         adqst = findViewById(R.id.addqst);

         log = findViewById(R.id.log);
        //if add questions is selected, go to SelectCourse Class
        adqst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoicePage.this, SelectCourse.class);
                startActivity(intent);

            }
        });


                //if Log out is selected

                log.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(ChoicePage.this).create();
                        alertDialog.setTitle("logout");
                        alertDialog.setMessage(" Are you sure you want to logout ? ");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        SharedPreferences.Editor editor = getSharedPreferences("myPre", MODE_PRIVATE).edit();
                                        editor.putBoolean("loginTrue",false);
                                        editor.apply();
                                        Intent cold = new Intent(ChoicePage.this, MainActivity.class);
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
            }

    }


