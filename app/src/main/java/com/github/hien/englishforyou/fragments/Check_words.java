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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hien.englishforyou.MainActivity;
import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.data.DataSQL;
import com.github.hien.englishforyou.object.Question1;
import com.github.hien.englishforyou.object.WordSave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tung on 5/14/2017.
 */

public class Check_words extends AppCompatActivity {

    private int lay=0,cauhoi = 5,socautl=0;
    private TextView txt6 ;
    private Button btngohome;
    private Random random;
    private WordSave wordSave;
    private ArrayList<Question1> question1ArrayList= new ArrayList<>();
    private ArrayList<RelativeLayout> CauHoiLayout= new ArrayList<>();
    //bat dau
    private TextView txvSoLuongDaTL;
    private RelativeLayout latstart;

    //cau hoi loai 1
    private RelativeLayout ltCauHoi1;
    private ImageView imgMusicCheck;
    private EditText edtCauTL;
    private Button btnCheck;
    MediaPlayer mPlayer;
    //cau hoi loai 2
    private RelativeLayout ltCauHoi2;
    private TextView txvCauHoi2;
    private EditText edtCauTL2;
    private Button btnCheck2;
    //cau hoi 3
    private RelativeLayout ltCauHoi13;
    private ImageView imgMusicCheck3;
    private EditText edtCauTL3;
    private Button btnCheck3;
    //cau hoi 4
    private RelativeLayout ltCauHoi4;
    private ImageView imgMusicCheck4,imgAnh1,imgAnh2,imgAnh3,imgAnh4;
    private ArrayList<ImageView> imageViewArrayList= new ArrayList<>();
    private View.OnClickListener onClickListener;

    //cau hoi loai 2
    private RelativeLayout ltCauHoi5;
    private TextView txvCauHoi5;
    private EditText edtCauTL5;
    private Button btnCheck5;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_words);
        txt6 = (TextView) findViewById(R.id.txt6);
        btngohome = (Button) findViewById(R.id.btngohome);

        txvSoLuongDaTL=(TextView) findViewById(R.id.txvSoLuongDaTL);
        latstart=(RelativeLayout)findViewById(R.id.latstart);
        CauHoiLayout.add(latstart);

        ltCauHoi1=(RelativeLayout)findViewById(R.id.ltCauHoi1);
        CauHoiLayout.add(ltCauHoi1);
        imgMusicCheck=(ImageView) findViewById(R.id.imgMusicCheck);
        edtCauTL=(EditText) findViewById(R.id.edtCauTL);
        btnCheck=(Button) findViewById(R.id.btnCheck);

        //cau 2
        ltCauHoi2=(RelativeLayout)findViewById(R.id.ltCauHoi2);
        CauHoiLayout.add(ltCauHoi2);
        txvCauHoi2=(TextView) findViewById(R.id.txvCauHoi2);
        edtCauTL2=(EditText) findViewById(R.id.edtCauTL2);
        btnCheck2=(Button) findViewById(R.id.btnCheck2);

        //cau 3
        ltCauHoi13=(RelativeLayout)findViewById(R.id.ltCauHoi3);
        CauHoiLayout.add(ltCauHoi13);
        imgMusicCheck3=(ImageView) findViewById(R.id.imgMusicCheck3);
        edtCauTL3=(EditText) findViewById(R.id.edtCauTL3);
        btnCheck3=(Button) findViewById(R.id.btnCheck3);

        //cau 4
        ltCauHoi4=(RelativeLayout)findViewById(R.id.ltCauHoi4);
        CauHoiLayout.add(ltCauHoi4);
        imgMusicCheck4=(ImageView)findViewById(R.id.imgMusicCheck4);
        imgAnh1=(ImageView)findViewById(R.id.imgAnh1);
        imgAnh2=(ImageView)findViewById(R.id.imgAnh2);
        imgAnh3=(ImageView)findViewById(R.id.imgAnh3);
        imgAnh4=(ImageView)findViewById(R.id.imgAnh4);
        imageViewArrayList.add(imgAnh1);
        imageViewArrayList.add(imgAnh2);
        imageViewArrayList.add(imgAnh3);
        imageViewArrayList.add(imgAnh4);

        //cau 2
        ltCauHoi5=(RelativeLayout)findViewById(R.id.ltCauHoi5);
        CauHoiLayout.add(ltCauHoi5);
        txvCauHoi5=(TextView) findViewById(R.id.txvCauHoi5);
        edtCauTL5=(EditText) findViewById(R.id.edtCauTL5);
        btnCheck5=(Button) findViewById(R.id.btnCheck5);

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
        } else {
            int h = 0;
            for(int i=0;i<question1ArrayList.size();i++){
                if(question1ArrayList.get(i).isResult()){
                    h++;
                    Toast.makeText(this,h,Toast.LENGTH_SHORT).show();
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
                wordSave = DataSQL.getInstance().getArrayList().get(random.nextInt(DataSQL.getInstance().getArrayList().size() - 1));
                question1ArrayList.add(new Question1(wordSave.getWorld()));
                break;
            case 2:
                wordSave = DataSQL.getInstance().getArrayList().get(random.nextInt(DataSQL.getInstance().getArrayList().size() - 1));
                wordSave.setArrProperties();
                question1ArrayList.add(new Question1(wordSave.getWorld()));
                break;
            case 3:
                wordSave = DataSQL.getInstance().getArrayList().get(random.nextInt(DataSQL.getInstance().getArrayList().size()));
                question1ArrayList.add(new Question1(wordSave.getWorld()));
                break;
            case 4:
                final int i = random.nextInt(4);
                wordSave= DataSQL.getInstance().getArrayList().get(i);
                question1ArrayList.add(new Question1(wordSave.getWorld()));

                for (int j = 0;j < imageViewArrayList.size();j++){
                    if(i == j){
                        imageViewArrayList.get(j).setImageBitmap(wordSave.getImage());
                    } else {
                        imageViewArrayList.get(j).setImageBitmap(DataSQL.getInstance().getArrayList().get(j).getImage());
                    }
                }
                onClickListener= new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getId() == imageViewArrayList.get(i).getId()){
                            question1ArrayList.get(question1ArrayList.size()-1).setResult(true);
                            Toast("ĐÚNG");
                        } else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                };
                break;
            case 5:
                wordSave = DataSQL.getInstance().getArrayList().get(random.nextInt(DataSQL.getInstance().getArrayList().size() - 1));
                wordSave.setArrProperties();
                question1ArrayList.add(new Question1(wordSave.getWorld()));
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
                    mPlayer.setDataSource(wordSave.getMusic());
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
                        String s = edtCauTL.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        }else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });
                break;
            case 2:
                txvCauHoi2.setText("Từ "+ "\"" + wordSave.getArrProperties().get("nghia") + "\" dịch sang tiếng Anh là gì?");
                btnCheck2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = edtCauTL2.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        }else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });
                break;
            case 3:
                imgMusicCheck3.setImageBitmap(wordSave.getImage());
                btnCheck3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s=edtCauTL3.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        }else {
                            Toast("SAI");
                        }
                        setCauHOi();
                    }
                });
                break;
            case 4:
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(wordSave.getMusic());
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.prepare();
                } catch (IOException e) {
                }
                imgMusicCheck4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mPlayer.isPlaying()) {
                            mPlayer.start();
                        } else {
                            mPlayer.pause();
                        }
                    }
                });
                imgAnh1.setOnClickListener(onClickListener);
                imgAnh2.setOnClickListener(onClickListener);
                imgAnh3.setOnClickListener(onClickListener);
                imgAnh4.setOnClickListener(onClickListener);
                break;
            case 5:
                txvCauHoi5.setText("Từ "+ "\""+wordSave.getArrProperties().get("nghia") + "\" dịch sang tiếng Anh là gì?");
                btnCheck5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = edtCauTL5.getText().toString().trim();
                        question1ArrayList.get(question1ArrayList.size()-1).check(s);
                        if(question1ArrayList.get(question1ArrayList.size()-1).isResult()){
                            Toast("ĐÚNG");
                        }else {
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
