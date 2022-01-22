package com.example.onlineexamination.SQL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASEINFORMATION = "InfoName";
    private static final String TABLE_INFORMATION = "infoTable";
    private static final String KEYID = "id";
    private static final String KEYNAME = "name";
    private static final String KEYEMAIL = "email";
    private static final String KEYPASSWORD = "password";
    private static final String KEYVALUE = "value";

    public DatabaseHandler(Context context) {
        super(context, DATABASEINFORMATION, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMAIL_TABLE = "CREATE TABLE " + TABLE_INFORMATION + "("
                + KEYID + " INTEGER PRIMARY KEY,"
                + KEYNAME + " TEXT,"
                + KEYEMAIL + " TEXT,"
                + KEYPASSWORD + " TEXT,"
                + KEYVALUE + " TEXT" + ")";
        db.execSQL(CREATE_EMAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);

        onCreate(db);
    }

    public void addinformation(Information information) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEYNAME, information.getName());
        values.put(KEYEMAIL, information.getEmail());
        values.put(KEYPASSWORD, information.getPassword());
        values.put(KEYVALUE, information.getValue());

        db.insert(TABLE_INFORMATION, null, values);
        db.close();
    }

    public List<Information> getAllDETAILS() {
        List<Information> informationList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_INFORMATION;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Information Information = new Information();
                Information.setId(Integer.parseInt(cursor.getString(0)));
                Information.setName(cursor.getString(1));
                Information.setEmail(cursor.getString(2));
                Information.setPassword(cursor.getString(3));
                Information.setValue(cursor.getString(4));

                informationList.add(Information);
            } while (cursor.moveToNext());
        }


        return informationList;
    }


}
