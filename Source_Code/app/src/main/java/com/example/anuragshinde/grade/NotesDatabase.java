package com.example.anuragshinde.grade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabase extends SQLiteOpenHelper {

    public static final String DBName = "appDBian";
    public static final int DBversion=1;

    public NotesDatabase(Context context){
        super(context,DBName,null,DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String notesTable = "Create Table Notes(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, description VARCHAR);";
        db.execSQL(notesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
