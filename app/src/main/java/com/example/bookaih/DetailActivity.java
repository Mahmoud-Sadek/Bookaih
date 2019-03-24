package com.example.bookaih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.IndividualModel;
import com.example.bookaih.model.OrderIndividualModel;
import com.example.bookaih.utils.OrderCommentDialog;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    IndividualModel model;

    OrderCommentDialog orderCommentDialog;
    FireDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        database = new FireDatabase(this);
        model = (IndividualModel) getIntent().getSerializableExtra("model");

        Glide.with(this).load(model.getImage()).into(profileImageCIV);
        item_name.setText(model.getName());
        item_price.setText(model.getPrice());
        item_description.setText(model.getDescription());
        item_flower_type.setText(model.getFlowerType());
        item_paper_type.setText(model.getPaperType());
    }


    @OnClick(R.id.btn_cart)
    void btn_add(){
        orderCommentDialog = new OrderCommentDialog(DetailActivity.this, new OrderCommentDialog.orderCommentAction() {
            @Override
            public void onGetComment(String code) {
                OrderIndividualModel modelOrder = new OrderIndividualModel();
                modelOrder.setComment(code);
                modelOrder.setItemId(model.getId());
                modelOrder.setUserId(FirebaseAuth.getInstance().getUid());
                database.addOrderIndividual(modelOrder);
                orderCommentDialog.dismiss();
            }
        });
        orderCommentDialog.show();
    }
}
