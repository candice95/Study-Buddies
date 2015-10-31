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
        for (int i = 0; i < 5; i++) {
            Appointment appointment = new Appointment();
            appointment.title = "cse 110";
            appointment.detail = "midterm review";
            appointment.creator = "ariel chen";
            appointment.date = "11.3";
            appointment.location = "Geisel Room 619";
            appointments.add(appointment);
        }
        Log.d("onCreateView", appointments + "");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(appointments);
        rv.setAdapter(recyclerViewAdapter);

        return view;
    }

}
