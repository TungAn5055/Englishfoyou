package com.github.hien.englishforyou.object;

/**
 * Created by Administrator on 22/03/2017.
 */

public class Question1 {

    String answer;
    boolean result=false;

    public Question1(String answer) {
        this.answer = answer;

    }
    public void check(String s){
        result=false;
        result=answer.equals(s);
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
