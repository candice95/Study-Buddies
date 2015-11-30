package com.example.cardgame.cardgame.ui.viewHolder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.cardgame.cardgame.R;

/**
 * Created by feicao on 11/6/15.
 */
public class MyAptParentViewHolder extends ParentViewHolder {
    public TextView myAptViewDate;
    public TextView myAptViewTitle;
    public TextView myAptViewDaysLeft;
    public TextView myAptViewDaysLeftText;
    public TextView myAptViewLoc;
    public TextView myAptViewDetail;

    public MyAptParentViewHolder(View itemView) {
        super(itemView);

        myAptViewTitle = (TextView)itemView.findViewById(R.id.title);
        myAptViewDaysLeft = (TextView)itemView.findViewById(R.id.daysLeft);
        myAptViewDaysLeftText = (TextView) itemView.findViewById(R.id.daysLeftText);
        myAptViewDate = (TextView)itemView.findViewById(R.id.date);
        myAptViewLoc = (TextView)itemView.findViewById(R.id.location);
        myAptViewDetail = (TextView) itemView.findViewById(R.id.detail);

    }
}
