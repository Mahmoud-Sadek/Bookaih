package com.example.bookaih;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.OrderWeddingModel;
import com.example.bookaih.model.WeddingModel;
import com.example.bookaih.utils.OrderWeddindCommentDialog;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeddingDetailActivity extends AppCompatActivity {

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

     WeddingModel model;

    OrderWeddindCommentDialog orderWeddindCommentDialog;
    FireDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        database = new FireDatabase(this);

        model = (WeddingModel) getIntent().getSerializableExtra("model");

        Glide.with(this).load(model.getImage()).into(profileImageCIV);
        item_name.setText(model.getName());
        item_price.setText(model.getPrice());
        item_description.setText(model.getDescription());
        item_flower_type.setText(model.getFlowerType());
        item_paper_type.setText(model.getPaperType());
    }


    @OnClick(R.id.btn_cart)
    void btn_add(){
        orderWeddindCommentDialog = new OrderWeddindCommentDialog(WeddingDetailActivity.this, new OrderWeddindCommentDialog.orderCommentAction() {
            @Override
            public void onGetComment(String comment, String date, String place) {
                OrderWeddingModel modelOrder = new OrderWeddingModel();
                modelOrder.setComment(comment);
                modelOrder.setDate(date);
                modelOrder.setPlace(place);
                modelOrder.setItemId(model.getId());
                modelOrder.setUserId(FirebaseAuth.getInstance().getUid());
                database.addOrderWedding(modelOrder);
                orderWeddindCommentDialog.dismiss();
            }

        });
        orderWeddindCommentDialog.show();
    }
}
