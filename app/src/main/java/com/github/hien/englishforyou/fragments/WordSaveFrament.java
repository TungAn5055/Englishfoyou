package com.github.hien.englishforyou.fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.adapter.WordSaveAdapter;
import com.github.hien.englishforyou.data.DataSQL;
import com.github.hien.englishforyou.object.WordSave;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 21/03/2017.
 */

public class WordSaveFrament extends Fragment {
    ListView lsvWordSave;
    WordSave wordSave;
    LinearLayout layView;
    Button btnView;
    ImageView imgWordSave, imgMussicWordSave;
    TextView txvWordSave, txvMoralWordSave;
    private TextView  txvdta, txvdtv, txvddta, txvddtv, txvtta, txvttv;
    private View view1;
    MediaPlayer mPlayer;
    ArrayList<String> arr;
    WordSaveAdapter saveAdapter;


    public WordSaveFrament() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.frament_word_save, container, false);
        arr = new ArrayList<String>();

        for (WordSave w : DataSQL.getInstance().getArrayList()) {
            arr.add(w.getWorld());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr);

        lsvWordSave = (ListView) view1.findViewById(R.id.lsvWordSave);
        imgMussicWordSave = (ImageView) view1.findViewById(R.id.imgMussicWordSave);
        imgWordSave = (ImageView) view1.findViewById(R.id.imgWordSave);
        txvWordSave = (TextView) view1.findViewById(R.id.txvWordSave);
        txvMoralWordSave = (TextView) view1.findViewById(R.id.txvMoralWordSave1);
        lsvWordSave.setAdapter(a);

        txvdta = (TextView) view1.findViewById(R.id.txvdta);
        txvdtv = (TextView) view1.findViewById(R.id.txvdtv);

        txvddta = (TextView) view1.findViewById(R.id.txvddta);
        txvddtv = (TextView) view1.findViewById(R.id.txvddtv);

        txvtta = (TextView) view1.findViewById(R.id.txvtta);
        txvttv = (TextView) view1.findViewById(R.id.txvttv);

        btnView = (Button) view1.findViewById(R.id.btnView);
        layView = (LinearLayout) view1.findViewById(R.id.layView);




        lsvWordSave.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wordSave = DataSQL.getInstance().getArrayList().get(position);
                wordSave.setArrProperties();
                setWordSave();
            }
        });

        return view1;
    }

    private void setWordSave() {
        txvWordSave.setText(wordSave.getWorld());

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(wordSave.getMusic());
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.prepare();
        } catch (IOException e) {
            System.out.println("dulieu "+e.toString());
        }
        imgMussicWordSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                } else {
                    mPlayer.pause();
                }
            }
        });

        txvMoralWordSave.setText(wordSave.getArrProperties().get("nghia"));
        imgWordSave.setImageBitmap(wordSave.getImage());

        if (wordSave.getArrProperties().containsKey("dt")) {
            txvdta.setText(wordSave.getArrProperties().get("dta"));
            txvdtv.setText(wordSave.getArrProperties().get("dtv"));

            txvdta.setVisibility(View.VISIBLE);
            txvdtv.setVisibility(View.VISIBLE);
        } else {
            txvdta.setVisibility(View.GONE);
            txvdtv.setVisibility(View.GONE);
        }

        if (wordSave.getArrProperties().containsKey("ddt")) {
            txvddta.setText(wordSave.getArrProperties().get("ddta"));
            txvddtv.setText(wordSave.getArrProperties().get("ddtv"));

            txvddta.setVisibility(View.VISIBLE);
            txvddtv.setVisibility(View.VISIBLE);
        } else {

            txvddta.setVisibility(View.GONE);
            txvddtv.setVisibility(View.GONE);
        }

        if (wordSave.getArrProperties().containsKey("tt")) {
            txvtta.setText(wordSave.getArrProperties().get("tta"));
            txvttv.setText(wordSave.getArrProperties().get("ttv"));

            txvtta.setVisibility(View.VISIBLE);
            txvttv.setVisibility(View.VISIBLE);
        } else {
            txvtta.setVisibility(View.GONE);
            txvttv.setVisibility(View.GONE);
        }
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(layView.getVisibility()==View.GONE){
                    layView.setVisibility(View.VISIBLE);
                }else {
                    layView.setVisibility(View.GONE);
                }
            }
        });
    }
}
