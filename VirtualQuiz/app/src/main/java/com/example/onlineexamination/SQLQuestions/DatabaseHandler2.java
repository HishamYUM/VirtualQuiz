package com.example.onlineexamination.SQLQuestions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;


import com.example.onlineexamination.SQL.Information;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASEQUESTIONS = "QUESTIONSManager";
    private static final String TABLE_QUESTIONS = "questionsTable";
    private static final String KEYID = "id";
    private static final String KEYQUESTIONS = "questions";
    private static final String KEYOPTIONA = "optionA";
    private static final String KEYOPTIONB = "optionB";
    private static final String KEYOPTIONC = "optionC";
    private static final String KEYOPTIOND = "optionD";
    private static final String KEYCORRECTOPTION = "correctoption";
    private static final String KEYVALUE = "coursekey";

    public DatabaseHandler2(Context context) {
        super(context, DATABASEQUESTIONS, null, DATABASE_VERSION);

    }


    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + KEYID + " INTEGER PRIMARY KEY,"
                + KEYQUESTIONS + " TEXT,"
                + KEYOPTIONA + " TEXT,"
                + KEYOPTIONB + " TEXT,"
                + KEYOPTIONC + " TEXT,"
                + KEYOPTIOND + " TEXT,"
                + KEYCORRECTOPTION + " TEXT,"
                + KEYVALUE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    public void addQuestions(Questions questions) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEYQUESTIONS, questions.getQuestion());
        values.put(KEYOPTIONA, questions.getOptionA());
        values.put(KEYOPTIONB, questions.getOptionB());
        values.put(KEYOPTIONC, questions.getOptionC());
        values.put(KEYOPTIOND, questions.getOptionD());
        values.put(KEYCORRECTOPTION, questions.getCorrectOption());
        values.put(KEYVALUE, questions.getCoursekey());

        db.insert(TABLE_QUESTIONS, null, values);

        db.close();
    }

    public List<Questions> getAllData() {
        List<Questions> QuestionList = new ArrayList<Questions>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Questions questions = new Questions();
                questions.setId(Integer.parseInt(cursor.getString(0)));
                questions.setQuestion(cursor.getString(1));
                questions.setOptionA(cursor.getString(2));
                questions.setOptionB(cursor.getString(3));
                questions.setOptionC(cursor.getString(4));
                questions.setOptionD(cursor.getString(5));
                questions.setCorrectOption(cursor.getString(6));
                questions.setCoursekey(cursor.getString(7));

                QuestionList.add(questions);
            } while (cursor.moveToNext());
        }
        return QuestionList;
    }
}