package com.example.cardgame.cardgame.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Appointment;
import com.example.cardgame.cardgame.ui.adapter.AptExpandableAdapter;
import com.example.cardgame.cardgame.ui.adapter.RecyclerViewAdapter;
import com.example.cardgame.cardgame.ui.component.MyAptChild;
import com.example.cardgame.cardgame.ui.component.MyAptParent;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Syuman on 10/26/15.
 */
public class FirstFragment extends Fragment {

    private RecyclerView rv1;
    private List<MyAptParent> appointments = new ArrayList<>();
    private ParseUser currentUser = ParseUser.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        rv1 = (RecyclerView) view.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv1.setLayoutManager(llm);
        Log.d("onCreateView", rv1 + "");

        Log.d("onCreateView", appointments + "");
//        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(appointments);

        AptExpandableAdapter aptExpandableAdapter = new AptExpandableAdapter(getActivity(),generateApts());
        aptExpandableAdapter.onRestoreInstanceState(savedInstanceState);
        rv1.setAdapter(aptExpandableAdapter);
        return view;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((AptExpandableAdapter) rv1.getAdapter()).onSaveInstanceState(outState);
    }

    private List<ParentListItem> generateApts() {

        List<ParentListItem> parentListItems = new ArrayList<>();
        // hard code data

        Calendar aptDate=Calendar.getInstance();
        aptDate.set(2015, Calendar.NOVEMBER, 13);
        Calendar now = Calendar.getInstance();
        long diffMillis= Math.abs(now.getTimeInMillis() - aptDate.getTimeInMillis());
        long differenceInDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);

        MyAptParent appointment = new MyAptParent();
        appointment.title = "cse 110";
        appointment.daysLeft = ""+differenceInDays;
        if(now.getTimeInMillis() - aptDate.getTimeInMillis() > 0 ) {
            appointment.daysLeftText = "days passed since";
        }
        else {
            appointment.daysLeftText = "days left until";
        }
        appointment.date = "Nov 13";
        List<MyAptChild> childItemList = new ArrayList<>();
        MyAptChild myAptChild = new MyAptChild("19:15", "Midterm Review", "Ariel", "Geisel Room 619", "8589997857", "shc143@ucsd.edu", "bring your practise midterm");
        childItemList.add(myAptChild);
        appointment.setChildItemList(childItemList);
        parentListItems.add(appointment);


        MyAptParent appointment2 = new MyAptParent();
        appointment2.title = "cse 132A";
        appointment2.daysLeft = "3";
        appointment2.date = "Nov 14";
        appointment2.location = "Geisel Room 716";
        List<MyAptChild> childItemList2 = new ArrayList<>();
        MyAptChild myAptChild2 = new MyAptChild("16:30", "Midterm Review", "Feicao", "Geisel Room 716", "8588888888", "example@ucsd.edu", "bring your laptop");
        childItemList2.add(myAptChild2);
        appointment2.setChildItemList(childItemList2);
        parentListItems.add(appointment2);

        MyAptParent appointment3 = new MyAptParent();
        appointment3.title = "cse 134";
        appointment3.daysLeft = "5";
        appointment3.date = "Nov 16";
        appointment3.location = "GH 204";
        List<MyAptChild> childItemList3 = new ArrayList<>();
        MyAptChild myAptChild3 = new MyAptChild("08:00", "Midterm Review", "Ariel", "GH 204", "8589997857", "shc143@ucsd.edu", "bring your practise midterm");
        childItemList3.add(myAptChild3);
        appointment3.setChildItemList(childItemList3);
        parentListItems.add(appointment3);

        return parentListItems;
    }

}
