package com.example.cardgame.cardgame.ui.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.cardgame.cardgame.R;

/**
 * Created by feicao on 11/6/15.
 */
public class MyAptChildViewHolder extends ChildViewHolder {

    public TextView reminder;

    public MyAptChildViewHolder(View itemView) {
        super(itemView);

        reminder = (TextView) itemView.findViewById(R.id.expand_apt);
    }
}
