package com.example.bookaih.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookaih.R;
import com.example.bookaih.adapter.AdminWeddingAdatpter;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.WeddingModel;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminWedding extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.emptyTV)
    TextView emptyTV;

    AdminWeddingAdatpter adatpter;


    FireDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_wedding);
        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(layoutManager);
//        adatpter =  new IndividualAdatpter(this,new ArrayList<IndividualModel>());
//        recycler.setAdapter(adatpter);

        database = new FireDatabase(this);
        database.getWedding(new FireDatabase.WeddingCallback() {
            @Override
            public void onCallback(ArrayList<WeddingModel> list) {

                if (list.size() == 0) {
                    loading.setVisibility(View.GONE);
                    emptyTV.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.GONE);
                    emptyTV.setVisibility(View.GONE);
                    Collections.reverse(list);
                    adatpter = new AdminWeddingAdatpter(getBaseContext(), list);
                    recycler.setAdapter(adatpter);

                }

            }
        });

    }

    @OnClick(R.id.btn_add)
    void btn_add() {
        startActivity(new Intent(this, AddWedding.class));
    }
}
