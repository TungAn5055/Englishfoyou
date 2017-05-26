package com.github.hien.englishforyou.methods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.hien.englishforyou.object.Sentence;
import com.github.hien.englishforyou.object.WordSave;
import com.github.hien.englishforyou.object.Words;
import com.github.hien.englishforyou.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 21/03/2017.
 */

public class MethodsSQLite {
    DatabaseHelper helper;
    MethodsExchange methodsExchange;
    Context ct;

    public MethodsSQLite(Context ct) {
        helper = new DatabaseHelper(ct);
        methodsExchange = new MethodsExchange();
        this.ct=ct;
    }

    public void addContact(Words k, ImageView imageView) {

        WordSave wordSave = methodsExchange.getWordSave(k,imageView);

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(helper.KEY_WORDS, wordSave.getWorld());
        values.put(helper.KEY_MUSIC, k.getUrlMiusic());
        values.put(helper.KEY_INFORMATION, wordSave.getInformation());
        values.put(helper.KEY_IMAGE,methodsExchange.getBytes(wordSave.getImage()));
        db.insert(helper.DB_TABLE, null, values);
        db.close();
        Toast.makeText(ct,"Đã lưu thành công",Toast.LENGTH_SHORT).show();
    }

    public void addContact_Sen(Sentence sentence) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(helper.KEY_SEN, sentence.getWord());
        values.put(helper.KEY_TRANSLATE_SEN, sentence.gettranslate());
        values.put(helper.KEY_URLMUSIC_SEN, sentence.getUrlMiusic());
        db.insert(helper.DB_TABLE_SEN, null, values);
        db.close();
        Toast.makeText(ct,"Đã lưu thành công",Toast.LENGTH_SHORT).show();
    }

    public List<WordSave> getAllContacts() {
        List<WordSave> wordSaveArrayList = new ArrayList<WordSave>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + helper.DB_TABLE;

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WordSave wordSave = new WordSave();
                wordSave.setWorld(cursor.getString(cursor.getColumnIndex(helper.KEY_WORDS)));
                wordSave.setInformation(cursor.getString(cursor.getColumnIndex(helper.KEY_INFORMATION)));
                wordSave.setMusic(cursor.getString(cursor.getColumnIndex(helper.KEY_MUSIC)));
                wordSave.setImage(methodsExchange.getImage(cursor.getBlob(cursor.getColumnIndex(helper.KEY_IMAGE))));
                wordSaveArrayList.add(wordSave);
            } while (cursor.moveToNext());
        }
        // return contact list
        return wordSaveArrayList;
    }

    public List<Sentence> getAllContacts_sen() {
        List<Sentence> wordSaveArrayList = new ArrayList<Sentence>();

        String selectQuery = "SELECT  * FROM " + helper.DB_TABLE_SEN;

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Sentence sentence = new Sentence();
                sentence.setWord(cursor.getString(cursor.getColumnIndex(helper.KEY_SEN)));
                sentence.settranslate(cursor.getString(cursor.getColumnIndex(helper.KEY_TRANSLATE_SEN)));
                sentence.setUrlMiusic(cursor.getString(cursor.getColumnIndex(helper.KEY_URLMUSIC_SEN)));
                wordSaveArrayList.add(sentence);
            } while (cursor.moveToNext());
        }

        return wordSaveArrayList;
    }
}
