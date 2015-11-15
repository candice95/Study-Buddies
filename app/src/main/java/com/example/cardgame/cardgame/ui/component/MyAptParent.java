package com.example.cardgame.cardgame.ui.component;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by apple on 11/6/15.
 */
public class MyAptParent implements ParentListItem {
    public List<MyAptChild> mChildrenList;
    public String title;
    public String daysLeft;
    public String daysLeftText;
    public String date;
    public String location;

    public MyAptParent() {}

    @Override
    public List<MyAptChild> getChildItemList() {
        return mChildrenList;
    }

    public void setChildItemList(List<MyAptChild> child) {
        mChildrenList = child;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getTitle() {
        return title;
    }

    public String getDaysLeft() {
        return daysLeft;
    }

    public String getDaysLeftText() {return daysLeftText; }

    public String getDate() {
        return date;
    }
}
