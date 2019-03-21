package com.example.anuragshinde.grade;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public static final String DBName = "appDB";
    public static final int DBversion=1;

    public Database(Context context){
        super(context,DBName,null,DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "Create Table User(username VARCHAR, password VARCHAR, email VARCHAR);";
        String notesTable = "Create Table Note(title VARCHAR, description VARCHAR);";
        db.execSQL(userTable);
        db.execSQL(notesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
