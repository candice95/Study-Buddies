package com.example.cardgame.cardgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by chenshiyu on 10/21/15.
 */
public class MainPageActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    private String usernameString;
    private String passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        setupUi();
    }


    private void setupUi() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            navigateToGamePage();
        }

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameString = username.getText().toString().trim();
                passwordString = password.getText().toString().trim();

                if (!usernameString.isEmpty() && !passwordString.isEmpty()) {
                    ParseUser.logInInBackground(usernameString, passwordString, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                navigateToGamePage();
                            } else {
                                showToast(e.getMessage());
                            }
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameString = username.getText().toString().trim();
                passwordString = password.getText().toString().trim();

                if (!usernameString.isEmpty() && !passwordString.isEmpty()) {
                    ParseUser user = new ParseUser();
                    user.setUsername(usernameString);
                    user.setPassword(passwordString);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                navigateToGamePage();
                            } else {
                                showToast(e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    private void navigateToGamePage() {
        Intent intent = new Intent(MainPageActivity.this, GamePageActivity.class);
        finish();
        startActivity(intent);
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

}
