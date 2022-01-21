//This class calculates the total result of the MCQ
package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class result extends AppCompatActivity {
    Button exit;
    EditText attemptEdit,correctEdit,wrongEdit,marksEdit, totQue;

    int correctAns = 0,atteQue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        exit = findViewById(R.id.exit);
        attemptEdit = findViewById(R.id.attemptEdit); // nummber of questions in the MCQ
        correctEdit = findViewById(R.id.correctEdit);// number of correct answers
        wrongEdit = findViewById(R.id.wrongEdit); // number of wrong answers
        marksEdit = findViewById(R.id.marksEdit); // total marks
        totQue = findViewById(R.id.totQue); // Number of questions in the course

        int wrongQue = 0;
        int correctTotal = 0;
        double wrongTotal = 0;
        double totalMarks = 0;

            atteQue = getIntent().getIntExtra("atteQue",0);
            correctAns = getIntent().getIntExtra("correctAns",0);

            wrongQue = atteQue - correctAns;
            correctTotal = correctAns * 2;
            wrongTotal = wrongQue * 0.5;
            totalMarks = correctTotal - wrongTotal;

        Toast.makeText(getApplicationContext(),""+totalMarks,Toast.LENGTH_SHORT).show();

            String atteQueS = String.valueOf(atteQue);
            String correctAnsS = String.valueOf(correctAns);
            String wrongQues = String.valueOf(wrongQue);
            String totalMark = String.valueOf(totalMarks);
            attemptEdit.setText(atteQueS);
            correctEdit.setText(correctAnsS);
            wrongEdit.setText(wrongQues);
            marksEdit.setText(totalMark);

        //exit and go back to MainActivity
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(result.this).create();
                alertDialog.setTitle("EXIT");
                alertDialog.setMessage(" Do you want to exit? ");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                SharedPreferences.Editor editor = getSharedPreferences("myPre2", MODE_PRIVATE).edit();
                                editor.putBoolean("login1True",false);
                                editor.apply();
                                Intent cold = new Intent(result.this, MainActivity.class);
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
