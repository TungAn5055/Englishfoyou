package com.github.hien.englishforyou.object;

import java.util.HashMap;

/**
 * Created by Administrator on 19/03/2017.
 */

public class Words {
    private String word;//tu ma nguoi dung muốn tra
    private String urlIamge;//link de lay anh hiển thị
    private String urlMiusic;//link de lay file nhac ve
    private HashMap<String,String> arrProperties;//cac thuộc tính liên quan den tu can tra vd nhu nghia , vd....
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUrlIamge() {
        return urlIamge;
    }

    public void setUrlIamge(String urlIamge) {
        this.urlIamge = urlIamge;
    }

    public String getUrlMiusic() {
        return urlMiusic;
    }

    public void setUrlMiusic(String urlMiusic) {
        this.urlMiusic = urlMiusic;
    }

    public HashMap<String, String> getArrProperties() {
        return arrProperties;
    }

    public void setArrProperties() {
        this.arrProperties = new HashMap<>();
    }
    public void setArrProperties(String key,String va) {
        this.arrProperties.put(key,va);
    }

    public Words() {
        setArrProperties();
    }
}
