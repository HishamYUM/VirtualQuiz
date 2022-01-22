package com.example.onlineexamination;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineexamination.SQL.DatabaseHandler;
import com.example.onlineexamination.SQL.Information;
import com.example.onlineexamination.SQLQuestions.DatabaseHandler2;

public class UserCourse extends AppCompatActivity implements

        AdapterView.OnItemSelectedListener {
    String[] country = { "Select", "C", "C++", "Java", "Python", "Android", "Machine Learning", "Networking", "Other"};
    String intentValue=" ",selectCourse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        intentValue = getIntent().getStringExtra("course");
        Button save;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_course);

        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(),"Your answer was saved.",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UserCourse.this,DummyPage.class);
                intent.putExtra("course",selectCourse);
                startActivity(intent);
                finish();
             }
        });
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

   }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        selectCourse = country[position];


    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}