package com.example.bookaih.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookaih.R;
import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.IndividualModel;
import com.example.bookaih.model.OrderIndividualModel;
import com.example.bookaih.model.OrderWeddingModel;
import com.example.bookaih.model.UserModel;
import com.example.bookaih.model.WeddingModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderIndividualDetail extends AppCompatActivity {

    @BindView(R.id.first_name)
    TextView Fname;
    @BindView(R.id.email_reg_page)
    TextView Email;
    @BindView(R.id.phone_reg_page)
    TextView phone_reg_page;
    @BindView(R.id.user_comment)
    TextView user_comment;

    @BindView(R.id.profileImageCIV)
    CircleImageView profileImageCIV;
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

    IndividualModel individualModel;

    FireDatabase database;
    UserModel userModel;
    FireAuth fireAuth;
    OrderIndividualModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_individual_detail);
        ButterKnife.bind(this);

        database = new FireDatabase(this);

        model = (OrderIndividualModel) getIntent().getSerializableExtra("model");

        database.getUser(model.getUserId(), new FireDatabase.UserCallback() {
            @Override
            public void onCallback(UserModel userModel) {
                if (!userModel.getImage().isEmpty())
                    Glide.with(getBaseContext()).load(userModel.getImage()).into(profileImageCIV);
                Fname.setText(userModel.getName());
                Email.setText(userModel.getEmail());
                phone_reg_page.setText(userModel.getPhone());
            }
        });

        user_comment.setText(model.getComment());

        database.getIndiviualProduct(model.getItemId(), new FireDatabase.IndividualProductCallback() {
            @Override
            public void onCallback(IndividualModel model) {
                item_name.setText(model.getName());
                item_price.setText(model.getPrice());
                item_description.setText(model.getDescription());
                item_flower_type.setText(model.getFlowerType());
                item_paper_type.setText(model.getPaperType());
            }
        });

    }

    @OnClick(R.id.btn_cancel_order)
    void btn_cancel_order() {
        database.deleteIndividual(model.getId());
    }
    @OnClick(R.id.phone_reg_page)
    void phone_reg_page() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phone_reg_page.getText()));
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }
}
