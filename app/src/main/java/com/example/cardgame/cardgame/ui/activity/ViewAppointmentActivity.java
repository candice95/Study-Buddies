package com.example.cardgame.cardgame.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewAppointmentActivity extends AppCompatActivity {

    //view appointements
    private RecyclerView rv;
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

//        rv = (RecyclerView) findViewById(R.id.rv);
//
//        // hard code data
//        for (int i = 0; i < 5; i++) {
//            Appointment appointment = new Appointment();
//            appointment.title = "cse 110";
//            appointment.detail = "midterm review";
//            appointment.creator = "ariel chen";
//            appointments.add(appointment);
//        }
//
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(appointments);
//        rv.setAdapter(recyclerViewAdapter);

    }
    
}
