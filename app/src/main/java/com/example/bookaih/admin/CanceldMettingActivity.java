package com.example.bookaih.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookaih.R;
import com.example.bookaih.adapter.UpcommingMeetingAdatpter;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.MeetModel;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CanceldMettingActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.emptyTV)
    TextView emptyTV;

    UpcommingMeetingAdatpter adatpter;


    FireDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canceld_metting);
        ButterKnife.bind(this);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(layoutManager);
//        adatpter =  new IndividualAdatpter(this,new ArrayList<IndividualModel>());
//        recycler.setAdapter(adatpter);

        database = new FireDatabase(this);


        database.getMeetingsCanceld(new FireDatabase.MeetingCallback() {
            @Override
            public void onCallback(ArrayList<MeetModel> list) {
                if (list.size() == 0) {
                    loading.setVisibility(View.GONE);
                    emptyTV.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.GONE);
                    emptyTV.setVisibility(View.GONE);
                    Collections.reverse(list);
                    adatpter = new UpcommingMeetingAdatpter(getBaseContext(), list);
                    recycler.setAdapter(adatpter);
                }
            }
        });
    }
}

