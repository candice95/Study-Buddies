package com.example.cardgame.cardgame.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.ui.component.MyAptChild;
import com.example.cardgame.cardgame.ui.component.MyAptParent;
import com.example.cardgame.cardgame.ui.viewHolder.MyAptChildViewHolder;
import com.example.cardgame.cardgame.ui.viewHolder.MyAptParentViewHolder;

import java.util.List;

/**
 * Created by apple on 11/6/15.
 */
public class AptExpandableAdapter extends ExpandableRecyclerAdapter<MyAptParentViewHolder,MyAptChildViewHolder> {
    private LayoutInflater mInflator;

    public AptExpandableAdapter(Context context, List<ParentListItem> itemList) {
        super(itemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public MyAptParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflator.inflate(R.layout.layout_myappointment_cardview, viewGroup,false);
        return new MyAptParentViewHolder(view);
    }

    @Override
    public MyAptChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflator.inflate(R.layout.layout_expand_myapt_cardview, viewGroup,false);
        return new MyAptChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyAptParentViewHolder pHolder, int i, ParentListItem pList) {
        MyAptParent aptParent = (MyAptParent)pList;
        pHolder.myAptViewDate.setText("");
        pHolder.myAptViewTitle.setText("title");
        pHolder.myAptViewDetail.setText("3");
        pHolder.myAptViewCreator.setText("days left until");
        pHolder.myAptViewLoc.setText("Nov 11");
    }

    @Override
    public void onBindChildViewHolder(MyAptChildViewHolder cHolder, int i, Object cList) {
        MyAptChild aptChild = (MyAptChild)cList;
        cHolder.reminder.setText("reminder: bring your textbook");
    }
}
