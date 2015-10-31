package com.example.cardgame.cardgame.ui.component;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;

/**
 * Created by chenshiyu on 10/30/15.
 */
public class AppointmentCardLayout extends CardView {

    public AppointmentCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(Appointment appointment) {
        ((TextView) findViewById(R.id.date)).setText(appointment.date);
        ((TextView) findViewById(R.id.title)).setText(appointment.title);
        ((TextView) findViewById(R.id.detail)).setText(appointment.detail);
        ((TextView) findViewById(R.id.creator)).setText("Initiator: "+appointment.creator);
        ((TextView) findViewById(R.id.location)).setText("Location: "+appointment.location);
    }

}
