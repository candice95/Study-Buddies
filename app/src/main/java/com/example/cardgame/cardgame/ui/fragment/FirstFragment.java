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
import com.example.cardgame.cardgame.ui.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syuman on 10/26/15.
 */
public class FirstFragment extends Fragment {

    private RecyclerView rv1;
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        rv1 = (RecyclerView) view.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv1.setLayoutManager(llm);
        Log.d("onCreateView", rv1 + "");
        // hard code data

        Appointment appointment = new Appointment();
        appointment.title = "cse 110";
        appointment.detail = "midterm review";
        appointment.creator = "ariel chen";
        appointment.date = "11.3";
        appointment.location = "Geisel Room 619";
        appointments.add(appointment);

        Appointment appointment2 = new Appointment();
        appointment2.title = "cse 132A";
        appointment2.detail = "midterm review";
        appointment2.creator = "ariel chen";
        appointment2.date = "11.4";
        appointment2.location = "Geisel Room 716";
        appointments.add(appointment2);

        Appointment appointment3 = new Appointment();
        appointment3.title = "cse 140L";
        appointment3.detail = "midterm review";
        appointment3.creator = "ariel chen";
        appointment3.date = "11.5";
        appointment3.location = "BML Room 218";
        appointments.add(appointment3);

        Appointment appointment4 = new Appointment();
        appointment4.title = "cse 170";
        appointment4.detail = "midterm review";
        appointment4.creator = "feicao";
        appointment4.date = "11.5";
        appointment4.location = "BML Room 218";
        appointments.add(appointment4);

        Appointment appointment5 = new Appointment();
        appointment5.title = "cse 170";
        appointment5.detail = "midterm review";
        appointment5.creator = "feicao";
        appointment5.date = "11.5";
        appointment5.location = "BML Room 218";
        appointments.add(appointment4);

        Appointment appointment6 = new Appointment();
        appointment6.title = "cse 170";
        appointment6.detail = "midterm review";
        appointment6.creator = "feicao";
        appointment6.date = "11.5";
        appointment6.location = "BML Room 218";
        appointments.add(appointment4);



        Log.d("onCreateView", appointments + "");
        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(appointments);
        rv1.setAdapter(recyclerViewAdapter);
        return view;
    }

}
