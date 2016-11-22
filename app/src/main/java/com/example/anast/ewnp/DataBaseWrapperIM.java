package com.example.anast.ewnp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapperIM extends SQLiteOpenHelper {

    public static final String WORDS = "Words";
    public static final String WORD_ID = "_id";
    public static final String WORD_NAME = "rus";
    public static final String WORD_NAME2 = "eng";

    private static final String DATABASE_NAME = "Im.db";
    private static final int DATABASE_VERSION = 1;

    // creation SQLite statement
    private static final String DATABASE_CREATE = "create table " + WORDS
            + "(" + WORD_ID + " integer primary key autoincrement, "
            + WORD_NAME + " text not null, " + WORD_NAME2 + " text not null);";

    public DataBaseWrapperIM(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you should do some logging in here
        // ..

        db.execSQL("DROP TABLE IF EXISTS " + WORDS);
        onCreate(db);
    }


}