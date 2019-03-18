package com.example.bookaih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Adminpage extends AppCompatActivity {
 private TextView Neworders;
 private TextView Newmeetingsdate;
 private TextView ordershistory;
 private TextView canceledmeetings;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Neworders=(TextView) findViewById(R.id.view_new_orders);
        Newmeetingsdate=(TextView) findViewById(R.id.view_new_meeting_dates);
        ordershistory=(TextView) findViewById(R.id.view_orders_history);
        canceledmeetings=(TextView) findViewById(R.id.view_canceld_meetings);



    }}
