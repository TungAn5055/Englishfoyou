package com.github.hien.englishforyou.fragments;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hien.englishforyou.MainActivity;
import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.data.DataSQL;
import com.github.hien.englishforyou.object.Question1;
import com.github.hien.englishforyou.object.Sentence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Tung on 5/13/2017.
 */

public class Check_Sentence extends AppCompatActivity {

    private int lay=0,cauhoi = 10,socautl=0;
    private Random random;
    private Sentence sentence;
    private ArrayList<Question1> question1ArrayList= new ArrayList<>();
    private ArrayList<RelativeLayout> CauHoiLayout= new ArrayList<>();
    //bat dau
    private TextView txvSoLuongDaTL;
    private RelativeLayout latstart;

    //cau hoi loai 1: ngh chọn câu
    private RelativeLayout ltCauHoi1;
    private RadioGroup radiogroup;
    private ImageView imgMusicCheck;
    private RadioButton radio11;
    private RadioButton radio12;
    private RadioButton radio13;
    private RadioButton radio14;
    private Button btnCheck;
    MediaPlayer mPlayer;

    //cau hoi loai 2 tieng viet dịch ra tiếng anh
    private RelativeLayout ltCauHoi2;
    private TextView txtthongbao;
    private TextView txtCauHoi2;
    private EditText edtCauTL2;
    private Button btnCheck2;

    //cau hoi 3 nghe câu viết ra tiêng anh
    private RelativeLayout ltCauHoi3;
    private ImageView imgMusicCheck3;
    private EditText edtCauTL3;
    private Button btnCheck3;

    //cau hoi 4 tiếng anh dịch ra tiếng việt
    private RelativeLayout ltCauHoi4;
    private TextView txtcauhoi4;
    private EditText edtCauTL4;
    private Button btnCheck4;

    //cau hoi 5 nghe tiếng anh dịch ra tiếng việt
    private RelativeLayout ltCauHoi5;
    private ImageView imagevie5;
    private EditText edtCauTL5;
    private Button btnCheck5;

    private  Button btngohome;
    private  TextView txt6;

    private ArrayList<RadioButton> radioArrylist= new ArrayList<>();
    private View.OnClickListener onClickListener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_sentence);

        txvSoLuongDaTL=(TextView) findViewById(R.id.txvSoLuongDaTL);
        latstart=(RelativeLayout)findViewById(R.id.latstart);
        CauHoiLayout.add(latstart);

        //cau 1
        ltCauHoi1=(RelativeLayout)findViewById(R.id.ltCauHoi1);
        CauHoiLayout.add(ltCauHoi1);
        imgMusicCheck=(ImageView) findViewById(R.id.imagevie1);
        radio11 = (RadioButton) findViewById(R.id.radio11);
        radio12 = (RadioButton) findViewById(R.id.radio12);
        radio13 = (RadioButton) findViewById(R.id.radio13);
        radio14 = (RadioButton) findViewById(R.id.radio14);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioArrylist.add(radio11);
        radioArrylist.add(radio12);
        radioArrylist.add(radio13);
        radioArrylist.add(radio14);
        btnCheck=(Button) findViewById(R.id.btnCheck);

        //cau 2
        ltCauHoi2=(RelativeLayout)findViewById(R.id.ltCauHoi2);
        CauHoiLayout.add(ltCauHoi2);
        txtCauHoi2=(TextView) findViewById(R.id.txtcauhoi2);
        edtCauTL2=(EditText) findViewById(R.id.edtCauTL2);
        btnCheck2=(Button) findViewById(R.id.btnCheck2);

        //cau 3
        ltCauHoi3=(RelativeLayout)findViewById(R.id.ltCauHoi3);
        CauHoiLayout.add(ltCauHoi3);
        imgMusicCheck3=(ImageView) findViewById(R.id.imagevie3);
        edtCauTL3=(EditText) findViewById(R.id.edtCauTL3);
        btnCheck3=(Button) findViewById(R.id.btnCheck3);

        //cau 4
        ltCauHoi4=(RelativeLayout)findViewById(R.id.ltCauHoi4);
        CauHoiLayout.add(ltCauHoi4);
        txtcauhoi4 =(TextView)findViewById(R.id.txtCauHoi4);
        edtCauTL4=(EditText) findViewById(R.id.edtCauTL4);
        btnCheck4=(Button) findViewById(R.id.btnCheck4);

        //cau 5
        ltCauHoi5=(RelativeLayout)findViewById(R.id.ltCauHoi5);
        CauHoiLayout.add(ltCauHoi5);
        imagevie5 =(ImageView) findViewById(R.id.imagevie5);
        edtCauTL5=(EditText) findViewById(R.id.edtCauTL5);
        btnCheck5=(Button) findViewById(R.id.btnCheck5);

        txt6 = (TextView) findViewById(R.id.txt6);
        btngohome = (Button) findViewById(R.id.btngohome);
        random = new Random();
        setClick();

    }

    private void setCauHOi(){
        socautl++;
        if(socautl <= 5){
            lay++;
            setData();
            setClick();
            setView();
        }else {
            int h=0;
            for(int i=0;i<question1ArrayList.size();i++){
                if(question1ArrayList.get(i).isResult()){
                    h++;
                }
            }
            Toast("Bạn trả lời đúng được \n"+ h +"/" + cauhoi);
            ltCauHoi5.setVisibility(View.GONE);
            latstart.setVisibility(View.VISIBLE);
            txt6.setText("Bạn trả lời đúng được "+ h +"/" + cauhoi);
            btngohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void setData(){
        switch (lay){
            case 1:
                final int i = random.nextInt(4);
                sentence = DataSQL.getInstance().getArrayList_sen().get(i);
                question1ArrayList.add(new Question1(sentence.getWord()));

                for (int j = 0;j < radioArrylist.size();j++){
                    if(i == j){
                        radioArrylist.get(j).setText(sentence.getWord());
                    } else {
                        radioArrylist.get(j).setText(DataSQL.getInstance().getArrayList_sen().get(j).getWord());
                    }
                }
                break;
            case 2:
                sentence = DataSQL.getInstance().getArrayList_sen().get(random.nextInt(DataSQL.getInstance().getArrayList_sen().size() - 1));
                question1ArrayList.add(new Question1(sentence.gettranslate()));
                break;
            case 3:
                final int i0 = random.nextInt(4);
                sentence = DataSQL.getInstance().getArrayList_sen().get(i0);
                question1ArrayList.add(new Question1(sentence.getUrlMiusic()));

//                onClickListener= new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(v.getId() == radioArrylist.get(i0).getId()){
//                            question1ArrayList.get(question1ArrayList.size()-1).setResult(true);
//                            Toast("ĐÚNG");
//                        } else {
//                            Toast("SAI");
//                        }
//                        setCauHOi();
//                    }
//                };
                break;
            case 4:
                sentence = DataSQL.getInstance().getArrayList_sen().get(random.nextInt(DataSQL.getInstance().getArrayList_sen().size() - 1));
                question1ArrayList.add(new Question1(sentence.getWord()));
                break;
            case 5:
                final int i1 = random.nextInt(4);
                sentence = DataSQL.getInstance().getArrayList_sen().get(i1);
                question1ArrayList.add(new Question1(sentence.getWord()));

                onClickListener= new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getId() == radioArrylist.get(i1).getId()){
                            question1ArrayList.get(question1ArrayList.size()-1).setResult(true);
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                };

                break;

        }
    }

    private void setClick(){
        switch (lay){
            case 0:
                latstart.setVisibility(View.GONE);
                setCauHOi();
                break;
            case 1:
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(sentence.getUrlMiusic());
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.prepare();
                } catch (IOException e) {
                }
                imgMusicCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mPlayer.isPlaying()) {
                            mPlayer.start();
                        } else {
                            mPlayer.pause();
                        }
                    }
                });

                btnCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton selectedRadioButton = (RadioButton) findViewById(radiogroup.getCheckedRadioButtonId());
                        final String selectedRadioButtonText = selectedRadioButton.getText().toString().trim();
                        if(selectedRadioButtonText.equals(sentence.getWord())){
                            question1ArrayList.get(question1ArrayList.size()-1).setResult(true);
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });
                break;
            case 2:
                txtCauHoi2.setText("Từ "+ "\""+ sentence.gettranslate()+ "\" dịch sang tiếng Anh là gì?");

                btnCheck2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = edtCauTL2.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });
                break;
            case 3:
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(sentence.getUrlMiusic());
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.prepare();
                } catch (IOException e) {
                }
                imgMusicCheck3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mPlayer.isPlaying()) {
                            mPlayer.start();
                        } else {
                            mPlayer.pause();
                        }
                    }
                });

                String s = edtCauTL3.getText().toString().trim();
                btnCheck3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = edtCauTL3.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });

                break;
            case 4:
                txtcauhoi4.setText("Từ "+ "\""+ sentence.gettranslate() + "\" dịch sang tiếng Anh là gì?");
                btnCheck4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = edtCauTL4.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });

                break;
            case 5:
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(sentence.getUrlMiusic());
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.prepare();
                } catch (IOException e) {
                }
                imgMusicCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mPlayer.isPlaying()) {
                            mPlayer.start();
                        } else {
                            mPlayer.pause();
                        }
                    }
                });


                btnCheck5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s5 = edtCauTL5.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s5);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });

                break;

        }
        txvSoLuongDaTL.setText(socautl + "/" +cauhoi);
    }

    private void setView(){
        for (int i=1;i<CauHoiLayout.size();i++){
            if(i!=lay){
                CauHoiLayout.get(i).setVisibility(View.GONE);
            }else {
                CauHoiLayout.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    private void Toast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
