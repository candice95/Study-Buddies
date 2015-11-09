package com.example.cardgame.cardgame.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.adapter.RecyclerViewAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syuman on 10/26/15.
 */
public class SecondFragment extends Fragment {

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        refresh();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        return view;
    }

    private void refresh() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    List<Appointment> appointments = new ArrayList<>();
                    for (ParseObject parseObject : objects) {
                        Appointment appointment = new Appointment();
                        appointment.title = parseObject.getString("title");
                        appointment.detail = parseObject.getString("detail");
                        appointment.creator = parseObject.getString("creator");
                        appointment.location = parseObject.getString("location");
                        appointment.capacity = parseObject.getString("capacity");
                        appointment.seats = parseObject.getInt("seats");
                        appointment.phone = parseObject.getString("phone");
                        appointment.email = parseObject.getString("email");
                        appointment.month = parseObject.getString("month");
                        appointment.day = parseObject.getString("day");
                        appointment.hour = parseObject.getString("hour");
                        appointment.minute = parseObject.getString("minute");
                        appointments.add(appointment);
                    }
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(appointments);
                    rv.setAdapter(recyclerViewAdapter);
                } else {

                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
