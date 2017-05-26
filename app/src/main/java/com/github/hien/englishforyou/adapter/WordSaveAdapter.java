package com.github.hien.englishforyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.object.WordSave;


/**
 * Created by Administrator on 21/03/2017.
 */

public class WordSaveAdapter extends ArrayAdapter<WordSave> {
    private ArrayList<WordSave> wordSaveArrayList;
    private Context context;
    private int resource;

    public WordSaveAdapter(Context context, int resource, List<WordSave> objects) {
        super(context, resource, objects);
        this.wordSaveArrayList = new ArrayList<>(objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_word_save, null);
        }


        Button imgMusic;
        TextView txvWord, txvDich;

        imgMusic=(Button)convertView.findViewById(R.id.imgMusic1);
        txvWord=(TextView) convertView.findViewById(R.id.txvWordSave);
        txvDich=(TextView) convertView.findViewById(R.id.txvMoralWordSave);


        WordSave wordSave = wordSaveArrayList.get(position);
        wordSave.setArrProperties();
        txvWord.setText(wordSave.getWorld());
        txvDich.setText(wordSave.getArrProperties().get("nghia"));

        /* MediaPlayer mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(wordSave.getMusic());
            mPlayer.prepare();
            imgMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"aaaaaaaaa",Toast.LENGTH_SHORT).show();
                    if (!mPlayer.isPlaying()) {
                        mPlayer.start();
                    } else {
                        mPlayer.pause();
                    }
                }
            });
        } catch (IOException e) {
            Log.e("AUDIO PLAYBACK", "prepare() failed");
        }*/

        convertView.setEnabled(true);
        return convertView;
    }

}
