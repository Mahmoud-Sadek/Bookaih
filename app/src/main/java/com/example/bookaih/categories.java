package com.example.bookaih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookaih.firebase.FireAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class categories extends AppCompatActivity {
    private Button wed;
    private Button indv;
    private Button setmeeting;

    FireAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        wed=(Button) findViewById(R.id.wedbtn);
        indv=(Button) findViewById(R.id.indvbtn);
        setmeeting=(Button) findViewById(R.id.setmeetingbtn);
        fireAuth = new FireAuth(this);
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),WeddingCategory.class));
            }});

        indv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),IndivudalCategory.class));
            }});

        setmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(categories.this, "Comming Soon!", Toast.LENGTH_SHORT).show();
            }});
    }

    @OnClick(R.id.logoutbtn)void logoutbtn(){
        fireAuth.mAuth.signOut();
        startActivity(new Intent(this,HomePage.class));
        finish();

    }
}
