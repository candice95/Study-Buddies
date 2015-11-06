package com.example.cardgame.cardgame;

import com.example.cardgame.cardgame.helper.Constant;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by chenshiyu on 10/21/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, Constant.PARSE_APPLICATION, Constant.PARSE_CLIENT_ID);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
