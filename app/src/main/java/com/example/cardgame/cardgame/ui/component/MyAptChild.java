package com.example.cardgame.cardgame.ui.component;

import android.util.Log;

/**
 * Created by apple on 11/6/15.
 */
public class MyAptChild {

    public String time;
    public String detail;
    public String initiator;
    public String location;
    public String phoneNum;
    public String email;
    public String comment;


    public MyAptChild(String paramTime, String paramDetail, String paramInitiator, String paramLocation, String paramPhoneNum, String paramEmail, String paramComment) {
        time = paramTime;
        detail = paramDetail;
        initiator = paramInitiator;
        location = paramLocation;
        phoneNum = paramPhoneNum;
        email = paramEmail;
        comment = paramComment;
    }

    public String getTime() {
        return time;
    }

    public String getDetail() {
        return detail;
    }

    public String getInitiator() {
        return initiator;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }
}
