package com.example.cardgame.cardgame.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog.Builder;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.helper.Events;
import com.example.cardgame.cardgame.ui.adapter.AptExpandableAdapter;
import com.example.cardgame.cardgame.ui.fragment.FirstFragment;
import com.example.cardgame.cardgame.ui.component.MyAptChild;
import com.example.cardgame.cardgame.ui.component.MyAptParent;
import com.example.cardgame.cardgame.ui.component.AppointmentCardLayout;
import com.example.cardgame.cardgame.ui.fragment.SecondFragment;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by feicao on 11/6/15.
 */
public class MyAptChildViewHolder extends ChildViewHolder {

    public String id;
    public TextView myAptTime;
    public TextView myAptDetail;
    public TextView myAptInitiator;
    public TextView myAptLocation;
    public TextView myAptPhoneNum;
    public TextView myAptEmail;
    public TextView myAptComment;
    public TextView seat_available;
    public ImageButton imageButton;
    public AlertDialog.Builder builder;
    public Context context;

    private Events.RefreshEvent refreshEvent = new Events.RefreshEvent();
    private Events.JoinedEvent joinedEvent = new Events.JoinedEvent();


    public MyAptChildViewHolder(View itemView) {
        super(itemView);

        myAptTime = (TextView) itemView.findViewById(R.id.time);
        myAptDetail = (TextView) itemView.findViewById(R.id.detail);
        myAptInitiator = (TextView) itemView.findViewById(R.id.initiator);
        myAptLocation = (TextView) itemView.findViewById(R.id.location);
        myAptPhoneNum = (TextView) itemView.findViewById(R.id.phoneNum);
        myAptEmail = (TextView) itemView.findViewById(R.id.email);
        myAptComment = (TextView) itemView.findViewById(R.id.comment);
        seat_available = (TextView) itemView.findViewById(R.id.expand_text_seats);

        imageButton = (ImageButton) itemView.findViewById(R.id.button_cancel);
        context = itemView.getContext();
        builder = new AlertDialog.Builder(context);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAppointment();
            }
        });
    }

    public void cancelAppointment() {
        //set alert dialog and remove appointment from parse
        builder.setMessage("Are you sure to cancel current appointment?");
        builder.setTitle("Confirmation");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeAppointment();
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void removeAppointment() {
        final ParseUser user = ParseUser.getCurrentUser();
        final ParseRelation<ParseObject> relation = user.getRelation("joined");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject object, ParseException e) {
                if (e == null) {
                    relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null) {
                                if (objects.contains(object)) {
                                    relation.remove(object);
                                    user.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                object.increment("seats", 1); // increase available seats
                                                object.saveInBackground(new SaveCallback() {
                                                    @Override
                                                    public void done(ParseException e) {
                                                        if (e == null) {
                                                            EventBus.getDefault().post(refreshEvent);
                                                            EventBus.getDefault().post(joinedEvent);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void showToast(String string) {
        Toast.makeText(itemView.getContext(), string, Toast.LENGTH_LONG).show();
    }

}


