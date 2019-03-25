package com.example.bookaih.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.bookaih.R;
import com.example.bookaih.categories;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.productBtn)
    void productBtn() {
        startActivity(new Intent(this, Products.class));
    }

    @OnClick(R.id.orderBtn)
    void orderBtn() {
        startActivity(new Intent(this, Orders.class));
    }

    @OnClick(R.id.upcommingBtn)
    void upcommingBtn() {
        startActivity(new Intent(this, UpcommingMeetingsActivity.class));

    }

    @OnClick(R.id.cancelBtn)
    void cancelBtn() {
        startActivity(new Intent(this, CanceldMettingActivity.class));

    }


}
