package com.example.cardgame.cardgame.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardgame.cardgame.R;
import com.example.cardgame.cardgame.helper.Events;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.greenrobot.event.EventBus;

public class CreateAppointmentActivity extends AppCompatActivity {

    //Creating a new appointment
    private MaterialEditText title, detail, creator, location, capacity, phone, email;
    private Spinner month, day, hour, minute;
    private Button submit;

    private Events.JoinedEvent joinedEvent = new Events.JoinedEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appt);

        setupUi();
    }


    private void setupUi() {
        month = (Spinner) findViewById(R.id.month);
        day = (Spinner) findViewById(R.id.day);
        hour = (Spinner) findViewById(R.id.hour);
        minute = (Spinner) findViewById(R.id.minute);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.months, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.hours, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.minutes, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapter);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter1);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hour.setAdapter(adapter2);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minute.setAdapter(adapter3);

        title = (MaterialEditText) findViewById(R.id.title);
        detail = (MaterialEditText) findViewById(R.id.detail);
        creator = (MaterialEditText) findViewById(R.id.creator);
        location = (MaterialEditText) findViewById(R.id.location);
        capacity = (MaterialEditText) findViewById(R.id.capacity);
        phone = (MaterialEditText) findViewById(R.id.phone);
        email = (MaterialEditText) findViewById(R.id.email);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasEmpty()) {
                    submit.setEnabled(false);
                    final ParseObject parseObject = new ParseObject("Appointment");
                    parseObject.put("title", getString(title));
                    parseObject.put("detail", getString(detail));
                    parseObject.put("creator", getString(creator));
                    parseObject.put("location", getString(location));
                    parseObject.put("capacity", getString(capacity));
                    parseObject.put("seats", Integer.parseInt(getString(capacity)));
                    parseObject.put("phone", getString(phone));
                    parseObject.put("email", getString(email));
                    parseObject.put("month", month.getSelectedItem().toString());
                    parseObject.put("day", day.getSelectedItem().toString());
                    parseObject.put("hour", hour.getSelectedItem().toString());
                    parseObject.put("minute", minute.getSelectedItem().toString());
                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            submit.setEnabled(true);
                            if (e == null) {
                                showToast("Created Successfully");
                                ParseUser user = ParseUser.getCurrentUser();
                                ParseRelation<ParseObject> relation = user.getRelation("joined");
                                relation.add(parseObject);
                                user.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            parseObject.increment("seats", -1); // decrease available seats
                                            parseObject.saveInBackground(new SaveCallback() {
                                                @Override
                                                public void done(ParseException e) {
                                                    if (e == null) {
                                                        EventBus.getDefault().post(joinedEvent);
                                                    }
                                                }
                                            });
                                        } else {
                                            showToast("error occurs, retry");
                                        }
                                    }
                                });
                                finish();
                            } else {
                                showToast("Created fail");
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean hasEmpty() {
        boolean empty = getString(title).isEmpty();
        empty |= getString(detail).isEmpty();
        empty |= getString(creator).isEmpty();
        empty |= getString(location).isEmpty();
        empty |= getString(capacity).isEmpty();
        empty |= getString(phone).isEmpty();
        empty |= getString(email).isEmpty();
        return empty;
    }

    private String getString(MaterialEditText editText) {
        return editText.getText().toString().trim();
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}
