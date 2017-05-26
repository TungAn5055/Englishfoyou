package com.github.hien.englishforyou.methods;

import android.util.Log;

import com.github.hien.englishforyou.data.DataFribase;
import com.github.hien.englishforyou.object.Sentence;
import com.github.hien.englishforyou.object.Words;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 19/03/2017.
 */

public class MethodsFribase {

    public void fribaseToArrayWords(final ArrayList<Words>  arr, DatabaseReference data, final HashMap<String,Words> map) {
        data.child("danhsachword").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot a : dataSnapshot.getChildren()) {
                    String h = a.getValue().toString();
                    try {
                        JSONObject j = stringToJson(h);
                        Words w = jsonToWord(j);
                        arr.add(w);
                        map.put(w.getWord(),w);

                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void fribasegetSentence( final ArrayList<Sentence> arr0, DatabaseReference data) {
        data.child("danhsachcau").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot a : dataSnapshot.getChildren()) {
                    String h = a.getValue().toString();
                    try {
                        JSONObject j = stringToJson(h);
                        Sentence sentence = jsonToSentence(j);
                        arr0.add(sentence);
                    } catch (Exception e) {
                        Log.e(TAG,"Exception eeeeeeeee" +  e);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void fribaseToArrayString(final ArrayList<String> arr1, DatabaseReference data) {
        data.child("danhsachtu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot a : dataSnapshot.getChildren()) {
                    String h = a.getValue().toString();
                    try {
                        JSONArray array = new JSONArray(h.substring(h.indexOf("["), h.lastIndexOf("]") + 1));
                        for (int i = 0; i < array.length(); i++) {
                            arr1.add(array.getString(i));
                        }

                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Words sechWord(String s) {
        return DataFribase.getInstance().getWordsHashMap().get(s);
    }

    public Sentence jsonToSentence(JSONObject j) throws Exception {
        Sentence sentence = new Sentence();
        sentence.setWord(j.getString("sentence"));
        sentence.settranslate(j.getString("translate"));
        sentence.setUrlMiusic(j.getString("music"));
        return sentence;
    }

    public Words jsonToWord(JSONObject j) throws Exception {
        Words words = new Words();
        words.setWord(j.getString("word"));
        words.setUrlIamge(j.getString("image"));
        words.setUrlMiusic(j.getString("music"));
        JSONArray array = j.getJSONArray("arr");
        for (int i = 0; i < array.length(); i++) {
            JSONObject k = array.getJSONObject(i);
            words.setArrProperties(k.getString("key"), k.getString("va"));
        }
        return words;
    }

    public Sentence jsonToWord_cau(JSONObject j) throws Exception {
        Sentence sentence = new Sentence();
        sentence.setWord(j.getString("sentence"));
        sentence.settranslate(j.getString("translate"));
        sentence.setUrlMiusic(j.getString("music"));
        return sentence;
    }

    public JSONObject stringToJson(String h) throws Exception {
        return new JSONObject(h.substring(h.indexOf("{"), h.lastIndexOf("}") + 1));

    }

}
