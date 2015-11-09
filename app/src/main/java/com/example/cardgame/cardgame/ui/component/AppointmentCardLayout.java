package com.example.cardgame.cardgame.ui.component;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;

/**
 * Created by chenshiyu on 10/30/15.
 */
public class AppointmentCardLayout extends CardView {

    private CardView cardView;
    private View expandView;
    private int descriptionViewMinHeight;
    private int descriptionViewFullHeight;
    boolean isCalled = false;

    public AppointmentCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        cardView = (CardView) findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleHeight();
            }
        });
    }

    public void setContent(Appointment appointment) {
        ((TextView) findViewById(R.id.date_month)).setText(appointment.month);
        ((TextView) findViewById(R.id.date_day)).setText(appointment.day);
        ((TextView) findViewById(R.id.title)).setText(appointment.title);
        ((TextView) findViewById(R.id.detail)).setText(appointment.detail);
        ((TextView) findViewById(R.id.creator)).setText("Initiator: "+appointment.creator);
        ((TextView) findViewById(R.id.location)).setText("Location: " + appointment.location);
        ((TextView) findViewById(R.id.time)).setText("Time: " + appointment.hour + " : " + appointment.minute);

        expandView = findViewById(R.id.card_view_expand_area);
        ((TextView) findViewById(R.id.expand_text_capacity)).setText("Capacity: "+appointment.capacity);
        ((TextView) findViewById(R.id.expand_text_seats)).setText("Seats available: "+appointment.seats);
        ((TextView) findViewById(R.id.expand_text_phone)).setText("Phone number: " + appointment.phone);
        ((TextView) findViewById(R.id.expand_text_email)).setText("Email: "+appointment.email);

        findViewById(R.id.button_join).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(isCalled) {
                    cardView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    return;
                }
                isCalled = true;
                descriptionViewMinHeight = cardView.getHeight();
                descriptionViewFullHeight = cardView.getHeight() * 2;
            }
        });
    }

    private void toggleHeight() {
        if (cardView.getHeight() == descriptionViewMinHeight) {
            expandView.animate().alpha(1.0f).withLayer().withStartAction(new Runnable() {
                @Override
                public void run() {
                    toggleHeight(true);
                    expandView.setVisibility(VISIBLE);
                }
            }).start();
        } else {
            expandView.animate().alpha(0.0f).withLayer().withStartAction(new Runnable() {
                @Override
                public void run() {
                    toggleHeight(false);
                }
            }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    expandView.setVisibility(GONE);
                }
            }).start();
        }
    }

    private void toggleHeight(boolean expand) {
        ValueAnimator anim;
        if (expand) {
            // expand
            anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                    descriptionViewFullHeight);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                    layoutParams.height = val;
                    cardView.setLayoutParams(layoutParams);
                }
            });
        } else {
            // collapse
            anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(),
                    descriptionViewMinHeight);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int val = (Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                    layoutParams.height = val;
                    cardView.setLayoutParams(layoutParams);
                }
            });
        }
        anim.start();
    }

}
