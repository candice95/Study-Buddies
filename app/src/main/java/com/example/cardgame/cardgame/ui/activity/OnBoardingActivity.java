package com.example.cardgame.cardgame.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgame.cardgame.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by chenshiyu on 10/21/15.
 */
public class OnBoardingActivity extends AppCompatActivity {

    //Onboarding
    private EditText username;
    private EditText password;
    private Button submit;
    private LinearLayout options;
    private TextView description;
    private TextView action;

    private String usernameString;
    private String passwordString;
    private boolean register;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        setupUi();
    }


    private void setupUi() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            navigateToGamePage();
        }

        options = (LinearLayout) findViewById(R.id.options);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        description = (TextView) findViewById(R.id.description);
        action = (TextView) findViewById(R.id.action);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameString = username.getText().toString().trim();
                passwordString = password.getText().toString().trim();
                if (register) {
                    if (!usernameString.isEmpty() && !passwordString.isEmpty()) {
                        submit.setEnabled(false);
                        showProgressDialog(R.string.loading);
                        ParseUser user = new ParseUser();
                        user.setUsername(usernameString);
                        user.setPassword(passwordString);
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                submit.setEnabled(true);
                                dismissProgressDialog();
                                if (e == null) {
                                    navigateToGamePage();
                                } else {
                                    showToast(e.getMessage());
                                }
                            }
                        });
                    }
                } else {
                    if (!usernameString.isEmpty() && !passwordString.isEmpty()) {
                        submit.setEnabled(false);
                        showProgressDialog(R.string.loading);
                        ParseUser.logInInBackground(usernameString, passwordString, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                submit.setEnabled(true);
                                dismissProgressDialog();
                                if (user != null) {
                                    navigateToGamePage();
                                } else {
                                    showToast(e.getMessage());
                                }
                            }
                        });
                    }
                }
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!register) {
                    register = true;
                    description.setText("Already have an account? ");
                    action.setText("Log in.");
                    submit.setText("Sign Up");
                } else {
                    register = false;
                    description.setText("Don't have an account? ");
                    action.setText("Sign up.");
                    submit.setText("Log In");
                }
            }
        });
    }

    private void navigateToGamePage() {
        Intent intent = new Intent(OnBoardingActivity.this, MainPageActivity.class);
        finish();
        startActivity(intent);
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    private void showProgressDialog(int resId) {
        if (progressDialog != null) {
            dismissProgressDialog();
            progressDialog = null;
        }
        progressDialog = ProgressDialog.show(this, "", getString(resId), true);
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
