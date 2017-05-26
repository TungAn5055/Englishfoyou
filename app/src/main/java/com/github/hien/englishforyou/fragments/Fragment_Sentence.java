package com.github.hien.englishforyou.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.github.hien.englishforyou.R;
import com.github.hien.englishforyou.data.DataFribase;
import com.github.hien.englishforyou.object.Sentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Fragment_Sentence extends Fragment {

    private View view;
    private ExpandableListView expandableListView;
    private List<String> parentHeaderInformation = new ArrayList<String>();
    private ArrayList<Sentence> arr;
    int i = 0;

    public Fragment_Sentence() {}

      @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          parentHeaderInformation = new ArrayList<String>();
          arr = DataFribase.getInstance().getStringArrayList_Sentence();
          for (Sentence w : arr) {
              parentHeaderInformation.add(w.gettranslate());
          }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_sentence, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        HashMap<String, List<Sentence>> allChildItems = returnGroupedChildItems();
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(getContext(), parentHeaderInformation, allChildItems);
        expandableListView.setAdapter(expandableListViewAdapter);

        return view;

    }

    private HashMap<String, List<Sentence>> returnGroupedChildItems(){

        HashMap<String, List<Sentence>> childContent = new HashMap<String, List<Sentence>>();

        arr = DataFribase.getInstance().getStringArrayList_Sentence();
        List<Sentence> sentence;
        for (Sentence w : arr) {
            sentence = new ArrayList<Sentence>();
            sentence.add(w);
            childContent.put(parentHeaderInformation.get(i), sentence);
            i++;
        }
        return childContent;
    }
}
