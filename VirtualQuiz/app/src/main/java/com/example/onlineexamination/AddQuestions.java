// this class is used to add questions to database
package com.example.onlineexamination;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.onlineexamination.SQLQuestions.DatabaseHandler2;
import com.example.onlineexamination.SQLQuestions.Questions;
public class AddQuestions extends AppCompatActivity {
    EditText add, btn1, btn2, btn3, btn4;
    Button save;
    CheckBox cb1, cb2, cb3, cb4;
    String intentValue = "";
    DatabaseHandler2 databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        intentValue = getIntent().getStringExtra("course"); // the name of selected course

        databaseHandler = new DatabaseHandler2 (AddQuestions.this);

        add = findViewById(R.id.add);
        save = findViewById(R.id.save);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


       //            List<Questions> listQues = DatabaseHandler2.getAllQuestions();

                System.out.println(databaseHandler.getAllData());
                System.out.println(databaseHandler.getAllData());


                if (!btn1.getText().toString().trim().equals("") && !btn2.getText().toString().trim().equals("")
                        && !btn3.getText().toString().trim().equals("") && !btn4.getText().toString().trim().equals("")
                        && !add.getText().toString().trim().equals("")) {

                String correctOption; // Store the correct option
                    //if one or more option is selected, take it's value and store it as correct option

                    if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked()) {
                      if (cb1.isChecked()){
                         correctOption= btn1.getText().toString();

                      } else if (cb2.isChecked()) {
                          correctOption= btn2.getText().toString();

                      } else if (cb3.isChecked()) {
                          correctOption= btn3.getText().toString();

                      } else   {
                          correctOption= btn4.getText().toString();
                      }
                      //add question to database
                       databaseHandler.addQuestions(new Questions(add.getText().toString(),btn1.getText().toString(),
                              btn2.getText().toString(), btn3.getText().toString(), btn4.getText().toString(),correctOption,intentValue ));

                        Intent intent = new Intent(AddQuestions.this, NewPage.class);
                        intent.putExtra("course",intentValue); // send this value to NewPage class to add more questions
                        startActivity(intent);
                        finish();
                    }
                    //check if at least one option is selected as correct answer
                    else if(!cb1.isChecked() || !cb2.isChecked() || !cb3.isChecked() || !cb4.isChecked()){
                    Toast.makeText(getApplicationContext(), " select the correct answer", Toast.LENGTH_SHORT).show();
                }
            }else {
                    // check if all fields are filled
                    android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AddQuestions.this).create();
                    alertDialog.setTitle("ALERT");
                    alertDialog.setMessage(" Kindly fill all the Information? ");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                    alertDialog.show();
                }
           }
        });
    }
}

