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
import com.example.bookaih.model.MeetModel;
import com.example.bookaih.model.OrderIndividualModel;
import com.example.bookaih.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class OrderMeetingDetail extends AppCompatActivity {

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


    FireDatabase database;
    UserModel userModel;
    FireAuth fireAuth;
    MeetModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_meeting_detail);
        ButterKnife.bind(this);

        model = (MeetModel) getIntent().getSerializableExtra("model");
        database = new FireDatabase(this);
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
        user_comment.setText(model.getReason());


    }

    @OnClick(R.id.btn_cancel_order)
    void btn_cancel_order() {
        database.deleteMeeting(model.getId(), model);
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
