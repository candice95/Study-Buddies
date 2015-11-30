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
import com.example.cardgame.cardgame.helper.DateUtil;
import com.example.cardgame.cardgame.helper.Events;
import com.example.cardgame.cardgame.ui.adapter.AptExpandableAdapter;
import com.example.cardgame.cardgame.ui.adapter.RecyclerViewAdapter;
import com.example.cardgame.cardgame.ui.component.MyAptChild;
import com.example.cardgame.cardgame.ui.component.MyAptParent;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;

public class FirstFragment extends Fragment {

    private RecyclerView rv1;
    private SwipeRefreshLayout swipeRefreshLayout1;

    private Events.JoinedEvent joinedEvent = new Events.JoinedEvent();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        rv1 = (RecyclerView) view.findViewById(R.id.rv1);
        swipeRefreshLayout1 = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout1);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv1.setLayoutManager(llm);

        getApts();

        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getApts();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(Events.RefreshEvent refreshEvent) {
        getApts();
    }

    private void getApts() {
        final List<ParentListItem> parentListItems = new ArrayList<>();
        final ParseUser currentUser = ParseUser.getCurrentUser();
        final ParseRelation<ParseObject> relation = currentUser.getRelation("joined");
        relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (final ParseObject parseObject : objects) {
                        String id = parseObject.getObjectId();
                        String title = parseObject.getString("title");
                        String detail = parseObject.getString("detail");
                        String creator = parseObject.getString("creator");
                        String location = parseObject.getString("location");
                        String phone = parseObject.getString("phone");
                        String email = parseObject.getString("email");
                        String month = parseObject.getString("month");
                        String day = parseObject.getString("day");
                        String hour = parseObject.getString("hour");
                        String minute = parseObject.getString("minute");
                        String time = hour + ":" + minute;
                        long differenceInDays = DateUtil.getDiffInDays(month, day);
                        MyAptParent appointment = new MyAptParent();
                        appointment.title = title;
                        if (differenceInDays < 0) {
                            // remove past appoint
                            relation.remove(parseObject);
                            currentUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    parseObject.deleteInBackground(new DeleteCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                EventBus.getDefault().post(joinedEvent);
                                            }
                                        }
                                    });
                                }
                            });

                        } else {
                            appointment.daysLeft = "" + differenceInDays;
                            appointment.daysLeftText = "days left until";
                            appointment.date = month + " " + day;
                            appointment.detail = detail;
                            List<MyAptChild> childItemList = new ArrayList<>();
                            MyAptChild myAptChild = new MyAptChild(id, time, detail, creator, location, phone, email, "finish your practise midterm");
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
