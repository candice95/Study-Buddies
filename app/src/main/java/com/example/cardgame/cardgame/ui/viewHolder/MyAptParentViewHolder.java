package com.example.cardgame.cardgame.ui.viewHolder;

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
    public TextView myAptViewDetail;
    public TextView myAptViewCreator;
    public TextView myAptViewLoc;

    public MyAptParentViewHolder(View itemView) {
        super(itemView);

        myAptViewDate = (TextView)itemView.findViewById(R.id.date);
        myAptViewTitle = (TextView)itemView.findViewById(R.id.title);
        myAptViewDetail = (TextView)itemView.findViewById(R.id.detail);
        myAptViewCreator = (TextView)itemView.findViewById(R.id.creator);
        myAptViewLoc = (TextView)itemView.findViewById(R.id.location);
    }
}
