package com.example.bookaih.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bookaih.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.individualBtn)
    void individualBtn() {
        startActivity(new Intent(this, AdminIndividual.class));
    }

    @OnClick(R.id.weddingBtn)
    void weddingBtn() {

    }

}
