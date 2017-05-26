package com.github.hien.englishforyou.data;

import com.github.hien.englishforyou.methods.MethodsFribase;
import com.github.hien.englishforyou.object.Sentence;
import com.github.hien.englishforyou.object.Words;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 19/03/2017.
 */

public class DataFribase {
    private static DataFribase dataFribase=null;
    //nhung du lieu can dung
    private ArrayList<Words> wordsArrayList=new ArrayList<>();// list de lay ve cac tu co tren data fribase
    private HashMap<String,Words> wordsHashMap=new HashMap<>();//hastmap chua cac tu lay ve
    private MethodsFribase methods = new MethodsFribase();
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ArrayList<Sentence> stringArrayList_sentence = new ArrayList<>();
    static {
        if(dataFribase==null){
            dataFribase= new DataFribase();
        }
    }

    public void setWordsArrayList(DatabaseReference data){
        methods.fribaseToArrayWords(wordsArrayList,data,wordsHashMap);
        methods.fribaseToArrayString(stringArrayList,data);
        methods.fribasegetSentence(stringArrayList_sentence,data);
    }

    public static DataFribase getInstance(){
        return dataFribase;
    }

    public ArrayList<Words> getWordsArrayList() {
        return wordsArrayList;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }

    public ArrayList<Sentence> getStringArrayList_Sentence() {
        return stringArrayList_sentence;
    }

    public HashMap<String, Words> getWordsHashMap() {
        return wordsHashMap;
    }

    public void getArrayList(DatabaseReference data){
        methods.fribaseToArrayWords(wordsArrayList,data,wordsHashMap);
        methods.fribaseToArrayString(stringArrayList,data);
        methods.fribasegetSentence(stringArrayList_sentence,data);
    }

}
