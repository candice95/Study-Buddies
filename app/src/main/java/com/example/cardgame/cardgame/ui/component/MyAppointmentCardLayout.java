package com.example.cardgame.cardgame.ui.component;

import android.support.v7.widget.CardView;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;

/**
 * Created by yufei ye on 15/11/5.
 */

public class MyAppointmentCardLayout extends CardView {

    public MyAppointmentCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setContent(Appointment appointment) {
        ((TextView) findViewById(R.id.date)).setText(appointment.date);
        ((TextView) findViewById(R.id.title)).setText(appointment.title);
        ((TextView) findViewById(R.id.detail)).setText(appointment.detail);
        ((TextView) findViewById(R.id.creator)).setText("Initiator: "+appointment.creator);
        ((TextView) findViewById(R.id.location)).setText("Location: "+appointment.location);

        (findViewById(R.id.my_card_view)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("testing","click my appointment");
            }
        });
    }

}
