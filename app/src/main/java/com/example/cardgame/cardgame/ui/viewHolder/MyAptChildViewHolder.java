package com.example.cardgame.cardgame.ui.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.cardgame.cardgame.R;

/**
 * Created by feicao on 11/6/15.
 */
public class MyAptChildViewHolder extends ChildViewHolder {

    public TextView myAptTime;
    public TextView myAptDetail;
    public TextView myAptInitiator;
    public TextView myAptLocation;
    public TextView myAptPhoneNum;
    public TextView myAptEmail;
    public TextView myAptComment;

    public MyAptChildViewHolder(View itemView) {
        super(itemView);

        myAptTime = (TextView) itemView.findViewById(R.id.time);
        myAptDetail = (TextView) itemView.findViewById(R.id.detail);
        myAptInitiator = (TextView) itemView.findViewById(R.id.initiator);
        myAptLocation = (TextView) itemView.findViewById(R.id.location);
        myAptPhoneNum = (TextView) itemView.findViewById(R.id.phoneNum);
        myAptEmail = (TextView) itemView.findViewById(R.id.email);
        myAptComment = (TextView) itemView.findViewById(R.id.comment);
    }
}
