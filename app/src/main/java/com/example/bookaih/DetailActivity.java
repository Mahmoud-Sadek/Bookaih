package com.example.bookaih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookaih.model.IndividualModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.profileImageCIV)
    ImageView profileImageCIV;
    @BindView(R.id.item_name)
    TextView item_name;
    @BindView(R.id.item_price)
    TextView item_price;
    @BindView(R.id.item_description)
    TextView item_description;
    @BindView(R.id.item_flower_type)
    TextView item_flower_type;
    @BindView(R.id.item_paper_type)
    TextView item_paper_type;

    public static IndividualModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Glide.with(this).load(model.getImage()).into(profileImageCIV);
        item_name.setText(model.getName());
        item_price.setText(model.getPrice());
        item_description.setText(model.getDescription());
        item_flower_type.setText(model.getFlowerType());
        item_paper_type.setText(model.getPaperType());
    }
}
