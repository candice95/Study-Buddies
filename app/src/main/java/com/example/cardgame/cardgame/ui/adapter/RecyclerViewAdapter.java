package com.example.cardgame.cardgame.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.activity.UserpageActivity;
import com.example.cardgame.cardgame.ui.component.AppointmentCardLayout;

import java.util.List;

/**
 * Created by chenshiyu on 10/30/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Appointment> appointments;
//    static AdapterView.OnItemClickListener listener;

    public RecyclerViewAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                layout_appointment_cardview, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CardViewHolder) holder).appointmentCardLayout.setContent(appointments.get(position));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AppointmentCardLayout appointmentCardLayout;
        static AdapterView.OnItemClickListener listener;
        public CardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            appointmentCardLayout = (AppointmentCardLayout) itemView.findViewById(R.id.card_view);

        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                Log.d("clickeddd", "new Throwable tr");
                Log.i("clicked", "apt clicked");
            }
        }
    }
}
