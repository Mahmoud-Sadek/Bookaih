package com.example.bookaih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookaih.adapter.IndividualAdatpter;
import com.example.bookaih.admin.AddIndivudalActivity;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.IndividualModel;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndivudalCategory extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.emptyTV)
    TextView emptyTV;

    IndividualAdatpter adatpter;



    FireDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indivudal_category);
        ButterKnife.bind(this);

        GridLayoutManager layoutManager =  new GridLayoutManager(this,2);
        recycler.setLayoutManager(layoutManager);
//        adatpter =  new IndividualAdatpter(this,new ArrayList<IndividualModel>());
//        recycler.setAdapter(adatpter);

        database = new FireDatabase(this);
        database.getAvaliableIndivid(new FireDatabase.IdividualCallback() {
            @Override
            public void onCallback(ArrayList<IndividualModel> list) {
                if (list.size() == 0){
                    loading.setVisibility(View.GONE);
                    emptyTV.setVisibility(View.VISIBLE);
                }else {
                    loading.setVisibility(View.GONE);
                    emptyTV.setVisibility(View.GONE);
                    Collections.reverse(list);
                    adatpter =  new IndividualAdatpter(getBaseContext(),list);
                    recycler.setAdapter(adatpter);

                }
            }
        });

    }

}
