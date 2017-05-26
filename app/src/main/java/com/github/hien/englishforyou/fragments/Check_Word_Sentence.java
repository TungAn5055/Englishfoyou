package com.github.hien.englishforyou.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.data.DataSQL;


public class Check_Word_Sentence extends Fragment {

    private View view;

    public Check_Word_Sentence() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_check, container, false);
        Button btn = (Button)view.findViewById(R.id.btn_word);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Check_words.class);
                if(DataSQL.getInstance().getArrayList().size() >= 5){
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Số từ bạn lưu chưa đủ 5 từ!", Toast.LENGTH_LONG).show();
                }
            }
        });
        Button btn3 = (Button)view.findViewById(R.id.btn_sentence);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent(view.getContext(), Check_Sentence.class);
                if(DataSQL.getInstance().getArrayList().size() >= 5){
                    startActivity(intent0);
                } else {
                        Toast.makeText(getContext(), "Số câu bạn lưu chưa đủ 5 câu!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

}
