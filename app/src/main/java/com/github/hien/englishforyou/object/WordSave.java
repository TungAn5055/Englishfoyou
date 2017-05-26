package com.github.hien.englishforyou.object;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 21/03/2017.
 */

public class WordSave {
    private String world;
    private String music;
    private String images;
    private String information;
    private Bitmap image;
    private HashMap<String,String> arrProperties;

    public HashMap<String, String> getArrProperties() {
        return arrProperties;
    }

    public void setArrProperties() {
        this.arrProperties =new HashMap<>();
        try {
           JSONObject j= new JSONObject(information.substring(information.indexOf("{"), information.lastIndexOf("}") + 1));
            JSONArray array = j.getJSONArray("arr");
            for (int i = 0; i < array.length(); i++) {
                JSONObject k = array.getJSONObject(i);
                setArrProperties(k.getString("key"), k.getString("va"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setArrProperties(String key,String va) {
        this.arrProperties.put(key,va);
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getMusic() {
        return music;
    }

    public void setUrlImage(String image) {
        this.images = images;
    }

    public String getUrlImage() {
        return images;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "WordSave{" +
                "\nworld='" + world + '\'' +
                "\n, music='" + music + '\'' +
                "\n, information='" + information + '\'' +
                "\n, image=" + image +
                '}';
    }

}
