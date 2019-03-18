package com.example.bookaih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookaih.adapter.IndividualAdatpter;
import com.example.bookaih.model.IndividualModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndivudalCategory extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.emptyTV)
    TextView emptyTV;

    IndividualAdatpter adatpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indivudal_category);
        ButterKnife.bind(this);

        GridLayoutManager layoutManager =  new GridLayoutManager(this,3);
        recycler.setLayoutManager(layoutManager);
        adatpter =  new IndividualAdatpter(this,new ArrayList<IndividualModel>());
        recycler.setAdapter(adatpter);


    }


}
