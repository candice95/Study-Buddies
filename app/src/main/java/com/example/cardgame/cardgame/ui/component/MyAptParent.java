package com.example.cardgame.cardgame.ui.component;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by apple on 11/6/15.
 */
public class MyAptParent implements ParentListItem {
    public List<MyAptChild> mChildrenList;
    public String title;
    public String detail;
    public String creator;
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
}
