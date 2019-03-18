package com.example.bookaih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class categories extends AppCompatActivity {
    private Button wed;
    private Button indv;
    private Button setmeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        wed=(Button) findViewById(R.id.wedbtn);
        indv=(Button) findViewById(R.id.indvbtn);
        setmeeting=(Button) findViewById(R.id.setmeetingbtn);

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }});

        indv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }});

        setmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }});
    }
}
