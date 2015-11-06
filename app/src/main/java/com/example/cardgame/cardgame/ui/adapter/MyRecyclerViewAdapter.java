package com.example.cardgame.cardgame.ui.adapter;

/**
 * Created by ye on 15/11/5.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.component.MyAppointmentCardLayout;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Appointment> appointments;

    public MyRecyclerViewAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                layout_myappointment_cardview, parent, false);
        return new MyCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyCardViewHolder) holder).myappointmentCardLayout.setContent(appointments.get(position));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    private static class MyCardViewHolder extends RecyclerView.ViewHolder{
        MyAppointmentCardLayout myappointmentCardLayout;

        public MyCardViewHolder(View itemView) {
            super(itemView);
            myappointmentCardLayout = (MyAppointmentCardLayout) itemView.findViewById(R.id.my_card_view);
        }
    }
}
