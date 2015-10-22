package com.example.cardgame.cardgame;

import com.parse.Parse;

/**
 * Created by chenshiyu on 10/21/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, Constant.PARSE_APPLICATION, Constant.PARSE_CLIENT_ID);
    }
}
