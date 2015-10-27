package com.example.cardgame.cardgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by chenshiyu on 10/21/15.
 */
public class UserpageActivity extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userPage);

        setupUi();
    }


    private void setupUi() {
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ParseUser.getCurrentUser() != null) {
                    ParseUser.logOut();
                    if (ParseUser.getCurrentUser() != null) {
                        showToast("log out failed");
                    } else {
                        showToast("log out success!!");
                        navigateToMainPage();
                    }
                }
            }
        });
    }

    private void navigateToMainPage() {
        Intent intent = new Intent(UserpageActivity.this, OnBoardingActivity.class);
        finish();
        startActivity(intent);
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

}
