package com.github.hien.englishforyou.data;

import android.content.Context;

import java.util.ArrayList;

import com.github.hien.englishforyou.methods.MethodsSQLite;
import com.github.hien.englishforyou.object.Sentence;
import  com.github.hien.englishforyou.object.WordSave;

/**
 * Created by Administrator on 21/03/2017.
 */

public class DataSQL {
    private MethodsSQLite methodsSQLite;
    private ArrayList<WordSave> arrayList;
    private ArrayList<Sentence> arrayList_sen;
    private static DataSQL dataSqlite=null;
    static {
        if(dataSqlite==null){
            dataSqlite= new DataSQL();
        }
    }
    public void setDataSqlite(Context ct){
        methodsSQLite = new MethodsSQLite(ct);
        arrayList = new ArrayList<>( methodsSQLite.getAllContacts());
        arrayList_sen =  new ArrayList<>( methodsSQLite.getAllContacts_sen());
    }
    public static DataSQL getInstance(){// tra vê biến stt
        return dataSqlite;
    }

    public ArrayList<WordSave> getArrayList() {
        return arrayList;
    }

    public ArrayList<Sentence> getArrayList_sen() {
        return arrayList_sen;
    }

    public boolean checkWords(String s1){
        boolean f=false;
        for (WordSave s:arrayList){
            if(s.getWorld().equals(s1)){
                f=true;
            }
        }
        return f;
    }

    public boolean checkWords_Sen(String s1){
        boolean f=false;
        for (Sentence s : arrayList_sen){
            if(s.getWord().equals(s1)){
                f=true;
            }
        }
        return f;
    }
}
