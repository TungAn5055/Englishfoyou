package com.github.hien.englishforyou.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 21/03/2017.
 */

public class DatabaseHelper  extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "Englishforyou2";

    // Table Names
    public static final String DB_TABLE = "table_words";
    public static final String DB_TABLE_SEN = "table_sentence";

    // column names
    public static final String KEY_WORDS = "words";
    public static final String KEY_MUSIC = "music";
    public static final String KEY_INFORMATION = "information";
    public static final String KEY_IMAGE = "image";

    public static final String KEY_SEN = "sentence";
    public static final String KEY_TRANSLATE_SEN = "translate";
    public static final String KEY_URLMUSIC_SEN = "urlmusic";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "("+
            KEY_WORDS + " TEXT," +
            KEY_MUSIC + " TEXT," +
            KEY_INFORMATION + " TEXT," +
            KEY_IMAGE + " BLOB);";

    private static final String CREATE_TABLE_IMAGE0 = "CREATE TABLE " + DB_TABLE_SEN + "("+
            KEY_SEN + " TEXT," +
            KEY_TRANSLATE_SEN + " TEXT," +
            KEY_URLMUSIC_SEN + " TEXT);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IMAGE);
        db.execSQL(CREATE_TABLE_IMAGE0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
//        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_SEN);
        onCreate(db);
    }
}