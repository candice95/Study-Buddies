package com.example.cardgame.cardgame.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syuman on 10/26/15.
 */
public class SecondFragment extends Fragment {

    private RecyclerView rv;
    private List<Appointment> appointments = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        Log.d("onCreateView", rv + "");
        // hard code data

        Appointment appointment = new Appointment();
        appointment.title = "cse 110";
        appointment.detail = "midterm review";
        appointment.creator = "ariel chen";
        appointment.date = "11.3";
        appointment.location = "Geisel Room 619";
        appointment.expandText = "This is the expand detail for cse 110 - hahaha";
        appointments.add(appointment);

        Appointment appointment2 = new Appointment();
        appointment2.title = "cse 132A";
        appointment2.detail = "midterm review";
        appointment2.creator = "ariel chen";
        appointment2.date = "11.4";
        appointment2.location = "Geisel Room 716";
        appointment2.expandText = "This is the expand detail for cse 132a - hahaha";
        appointments.add(appointment2);

        Appointment appointment3 = new Appointment();
        appointment3.title = "cse 140L";
        appointment3.detail = "midterm review";
        appointment3.creator = "ariel chen";
        appointment3.date = "11.5";
        appointment3.location = "BML Room 218";
        appointment3.expandText = "This is the expand detail for cse 140L - hahaha";
        appointments.add(appointment3);

        Log.d("onCreateView", appointments + "");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(appointments);
        rv.setAdapter(recyclerViewAdapter);

        return view;
    }

}
