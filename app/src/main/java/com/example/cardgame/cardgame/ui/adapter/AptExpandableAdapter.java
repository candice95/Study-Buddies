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
        pHolder.myAptViewTitle.setText(aptParent.getTitle());
        pHolder.myAptViewDaysLeft.setText(aptParent.getDaysLeft());
        pHolder.myAptViewDaysLeftText.setText(aptParent.getDaysLeftText());
        pHolder.myAptViewDate.setText(aptParent.getDate());
        pHolder.myAptViewDetail.setText(aptParent.getDetail());
    }

    @Override
    public void onBindChildViewHolder(MyAptChildViewHolder cHolder, int i, Object cList) {
        MyAptChild aptChild = (MyAptChild)cList;
        cHolder.id = aptChild.id;
        cHolder.myAptTime.setText("Time: " + aptChild.getTime());
        cHolder.myAptInitiator.setText("Initiator: " + aptChild.getInitiator());
        cHolder.myAptLocation.setText("Location: " + aptChild.getLocation());
        cHolder.myAptPhoneNum.setText("PhoneNumber: " + aptChild.getPhoneNum());
        cHolder.myAptEmail.setText("Email: " + aptChild.getEmail());
        cHolder.myAptComment.setText("Comment: " + aptChild.getComment());

    }
}
