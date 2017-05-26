package com.github.hien.englishforyou.object;

/**
 * Created by TungAn on 5/11/2017.
 */

public class Sentence {
    private String word;//tu ma nguoi dung muốn tra
    private String urlMiusic;//link de lay file nhac ve
    private String translate;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String gettranslate() {
        return translate;
    }

    public void settranslate(String translate) {
        this.translate = translate;
    }

    public String getUrlMiusic() {
        return urlMiusic;
    }

    public void setUrlMiusic(String urlMiusic) {
        this.urlMiusic = urlMiusic;
    }

}
