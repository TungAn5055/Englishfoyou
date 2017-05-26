package com.github.hien.englishforyou.fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.data.DataSQL;
import com.github.hien.englishforyou.methods.MethodsSQLite;
import com.github.hien.englishforyou.object.Sentence;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentDataSource;
    private HashMap<String, List<Sentence>> childDataSource;

    public ExpandableListViewAdapter(Context context, List<String> childParent, HashMap<String, List<Sentence>> child) {
        this.context = context;
        this.parentDataSource = childParent;
        this.childDataSource = child;
    }

    @Override
    public int getGroupCount() {
        return this.parentDataSource.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childDataSource.get(this.parentDataSource.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentDataSource.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.childDataSource.get(parentDataSource.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_layout, parent, false);
        }

        String parentHeader = (String)getGroup(groupPosition);

        TextView parentItem = (TextView)view.findViewById(R.id.parent_layout);
        parentItem.setText(parentHeader);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_layout, parent, false);
        }

        final Sentence sentence_now = (Sentence) getChild(groupPosition, childPosition);

        final TextView txtEn = (TextView)view.findViewById(R.id.txtEn);
        final ImageButton btn_listen = (ImageButton)view.findViewById(R.id.btn_listen);
        final TextView txtVi = (TextView)view.findViewById(R.id.txtVi);
        final ImageButton btn_save = (ImageButton)view.findViewById(R.id.btn_save);



        final MethodsSQLite methodsSQLite = new MethodsSQLite(parent.getContext());

        txtEn.setText(sentence_now.getWord());
        txtVi.setText("("+sentence_now.gettranslate()+")");

        btn_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean chayNhac = false;
                MediaPlayer mediaPlayer = new MediaPlayer();
                final boolean finalChayNhac = chayNhac;
                final MediaPlayer finalMediaPlayer = mediaPlayer;
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(sentence_now.getUrlMiusic());
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    chayNhac = true;
                } catch (IOException e){
                    //  Log.e(Tag,"Eror" + e);
                }
                if (finalChayNhac) {
                    if (!finalMediaPlayer.isPlaying()) {
                        finalMediaPlayer.start();
                    } else {
                        finalMediaPlayer.pause();
                    }
                } else {
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DataSQL.getInstance().checkWords_Sen(sentence_now.getWord())) {
                    methodsSQLite.addContact_Sen(sentence_now);
                } else {
                    Toast.makeText(parent.getContext(), "Từ này bạn đã lưu!", Toast.LENGTH_LONG).show();
                }
                DataSQL.getInstance().setDataSqlite(parent.getContext());
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
