package com.example.bookaih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Returnpassword extends AppCompatActivity {
private EditText Email22;
private Button Confirm;
private EditText confirmcode;
private Button Okay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_password);

        Email22=(EditText)findViewById(R.id.email_returnpassword_page);
        Confirm=(Button) findViewById(R.id.btn_confirmEmail_returnpass_page);
        confirmcode=(EditText)findViewById(R.id.confirmtion_code_returnpass_page);
        Okay=(Button) findViewById(R.id.btn_ok_reurnpass_page);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }});

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }});
    }
}
