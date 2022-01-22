package com.example.onlineexamination.SQLQuestions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_QUESTIONS = "Question";
    private static final String KEYQUESTIONS = "questions";
    private static final String KEYOPTIONA = "optionA";
    private static final String KEYOPTIONB = "optionB";
    private static final String KEYOPTIONC = "optionC";
    private static final String KEYOPTIOND = "optionD";
    private static final String KEYCORRECTOPTION = "correctoption";
    private static final String KEYVALUE = "coursekey";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static final String DATABASE_NAME = "AllQuestions";
    public final static String DATABASE_PATH = "/data/data/com.example.onlineexamination/databases/";
    public DatabaseHandler2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;

    }




    //Create a empty database on the system
    public void createDatabase() throws IOException
    {

        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();
        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }

    }
    //Check database already exist or not
    private boolean checkDataBase()
    {
        boolean checkDB = false;
        try
        {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }


    //Copies your database from your local assets-folder to the just created empty database in the system folder
    private void copyDataBase() throws IOException
    {

        InputStream mInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[2024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    //delete database
    public void db_delete()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        if(file.exists())


            System.out.println("delete database file.");
    }

    //Open database
    public void openDatabase() throws SQLException
    {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }

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
    public List<Questions> getAllData() throws IOException {
        copyDataBase();
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