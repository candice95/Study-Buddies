package com.example.cardgame.cardgame.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.adapter.AptExpandableAdapter;
import com.example.cardgame.cardgame.ui.component.MyAptChild;
import com.example.cardgame.cardgame.ui.component.MyAptParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syuman on 10/26/15.
 */
public class FirstFragment extends Fragment {

    private RecyclerView rv1;
    private List<MyAptParent> appointments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        rv1 = (RecyclerView) view.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv1.setLayoutManager(llm);
        Log.d("onCreateView", rv1 + "");

        Log.d("onCreateView", appointments + "");
//        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(appointments);

        AptExpandableAdapter aptExpandableAdapter = new AptExpandableAdapter(getActivity(),generateApts());
        aptExpandableAdapter.onRestoreInstanceState(savedInstanceState);
        rv1.setAdapter(aptExpandableAdapter);
        return view;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((AptExpandableAdapter) rv1.getAdapter()).onSaveInstanceState(outState);
    }

    private List<ParentListItem> generateApts() {

        List<MyAptChild> childItemList = new ArrayList<>();
        List<ParentListItem> parentListItems = new ArrayList<>();
        // hard code data
        MyAptParent appointment = new MyAptParent();
        appointment.title = "cse 110";
        appointment.detail = "midterm review";
        appointment.creator = "ariel chen";
        appointment.date = "11.3";
        appointment.location = "Geisel Room 619";
        appointments.add(appointment);
        childItemList.add(new MyAptChild());
        appointment.setChildItemList(childItemList);
        parentListItems.add(appointment);

        MyAptParent appointment2 = new MyAptParent();
        appointment2.title = "cse 132A";
        appointment2.detail = "midterm review";
        appointment2.creator = "ariel chen";
        appointment2.date = "11.4";
        appointment2.location = "Geisel Room 716";
        appointments.add(appointment2);
        childItemList.add(new MyAptChild());
        appointment2.setChildItemList(childItemList);
        parentListItems.add(appointment2);

        return parentListItems;
    }

}
