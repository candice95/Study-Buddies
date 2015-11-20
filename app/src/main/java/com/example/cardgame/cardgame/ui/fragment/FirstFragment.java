package com.example.cardgame.cardgame.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View.OnClickListener;

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
import com.parse.ParseRelation;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstFragment extends Fragment {

    private RecyclerView rv1;
    private SwipeRefreshLayout swipeRefreshLayout1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        rv1 = (RecyclerView) view.findViewById(R.id.rv1);
        swipeRefreshLayout1 = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv1.setLayoutManager(llm);
        Log.d("onCreateView", rv1 + "");

        getApts();

        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApts();
            }
        });
        return view;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((AptExpandableAdapter) rv1.getAdapter()).onSaveInstanceState(outState);
    }

    public void getApts() {
        // also need to getQuery("User")
        // joined: View Relations, query to get the list of appointments joined
        final List<ParentListItem> parentListItems = new ArrayList<>();
        final ParseUser currentUser = ParseUser.getCurrentUser();
        final ParseRelation<ParseObject> relation = currentUser.getRelation("joined");
//
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject parseObject : objects) {
                        if (parseObject.getString("creator").equals(currentUser.getUsername())) {
                            String title = parseObject.getString("title");
                            String detail = parseObject.getString("detail");
                            String location = parseObject.getString("location");
                            String phone = parseObject.getString("phone");
                            String email = parseObject.getString("email");
                            String month = parseObject.getString("month");
                            String day = parseObject.getString("day");
                            int dayInt = Integer.parseInt(day);
                            String hour = parseObject.getString("hour");
                            String minute = parseObject.getString("minute");
                            String time = hour + ":" + minute;
                            Log.d("generateApt", time);
                            Calendar aptDate = Calendar.getInstance();
                            if(parseObject.getString("month").equals("Nov")) {
                                aptDate.set(2015, Calendar.NOVEMBER, dayInt);
                            }
                            else if(parseObject.getString("month").equals("Dec")) {
                                aptDate.set(2015, Calendar.DECEMBER, dayInt);
                            }
                            Calendar now = Calendar.getInstance();
                            long diffMillis = Math.abs(now.getTimeInMillis() - aptDate.getTimeInMillis());
                            long differenceInDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
                            MyAptParent appointment = new MyAptParent();
                            appointment.title = title;
                            appointment.daysLeft = "" + differenceInDays;
                            if (now.getTimeInMillis() - aptDate.getTimeInMillis() > 0) {
                                appointment.daysLeftText = "days passed since";
                            } else {
                                appointment.daysLeftText = "days left until";
                            }
                            appointment.date = month + " " + day;
                            List<MyAptChild> childItemList = new ArrayList<>();
                            MyAptChild myAptChild = new MyAptChild(time, detail, currentUser.getUsername(), location, phone, email, "finish your practise midterm");
                            childItemList.add(myAptChild);
                            appointment.setChildItemList(childItemList);
                            parentListItems.add(appointment);
                        }
                    }
                    AptExpandableAdapter aptExpandableAdapter = new AptExpandableAdapter(getActivity(),parentListItems);
                    rv1.setAdapter(aptExpandableAdapter);
                }
                swipeRefreshLayout1.setRefreshing(false);
            }
        });
    }

}
