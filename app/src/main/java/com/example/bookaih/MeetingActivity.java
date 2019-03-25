package com.example.bookaih;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.MeetModel;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends AppCompatActivity {

    @BindView(R.id.edtDay)
    EditText edtDay;
    @BindView(R.id.edtTime)
    EditText edtTime;
    @BindView(R.id.edtReason)
    EditText edtReason;


    FireDatabase database;

    MeetModel meetModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        ButterKnife.bind(this);

        database = new FireDatabase(this);

        meetModel = new MeetModel();


    }


    @OnClick(R.id.btn_add_meeting)
    void btn_add() {
        if (!validate()) {
            return;
        }
        meetModel.setReason(edtReason.getText().toString());
        meetModel.setUserId(FirebaseAuth.getInstance().getUid());
        database.addMeeting(meetModel);
    }

    @OnClick(R.id.edtDay)
    void edtDay() {

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM-dd-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                if (myCalendar.getTime().before(new Date()))
                    Toast.makeText(MeetingActivity.this, "وقت غير صالح", Toast.LENGTH_SHORT).show();
                else {
                    edtDay.setText(sdf.format(myCalendar.getTime()));
                    meetModel.setDay(myCalendar.getTimeInMillis());
                }
            }

        };
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.edtTime)
    void edtTime() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                meetModel.setTime(selectedHour + ":" + selectedMinute);
                edtTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private boolean validate() {
        if (edtDay.getText().toString().isEmpty()) {
            edtDay.setError("Error");
            edtDay.requestFocus();
            return false;
        } else if (edtTime.getText().toString().isEmpty()) {
            edtTime.setError("Error");
            edtTime.requestFocus();
            return false;
        } else if (edtReason.getText().toString().isEmpty()) {
            edtReason.setError("Error");
            edtReason.requestFocus();
            return false;
        }

        return true;
    }
}
