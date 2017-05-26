package com.github.hien.englishforyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.object.Sentence;

import java.util.List;

/**
 * Created by an on 4/17/2016.
 */
public class CustomArrayListCentence extends ArrayAdapter<Sentence> {
    Context context;
    int resource;
    List<Sentence> object;
    TextView txtEn,txtVi;
    ImageButton btn_listen, btn_save;
    LinearLayout linearLayout, linearLayout_all;


    public CustomArrayListCentence(Context context, int resource, List<Sentence> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.object =object;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(resource,parent,false);
        View view= inflater.inflate(R.layout.list_single_sentence, parent, false);

        txtEn = (TextView) view.findViewById(R.id.txtEn);
        txtVi = (TextView) view.findViewById(R.id.txtVi);
        btn_listen = (ImageButton) view.findViewById(R.id.btn_listen);
        btn_save = (ImageButton) view.findViewById(R.id.btn_save);
//        linearLayout = (LinearLayout) view.findViewById(R.id.hide);
//        linearLayout_all = (LinearLayout) view.findViewById(R.id.all);

        txtEn.setText(object.get(position).getWord());
        txtVi.setText(object.get(position).gettranslate());

        btn_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_save.setVisibility(View.VISIBLE);
            }
        });
     return view;
    }
}
