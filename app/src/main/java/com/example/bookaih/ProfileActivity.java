package com.example.bookaih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.first_name)
    TextView Fname;
    @BindView(R.id.email_reg_page)
    TextView Email;
    @BindView(R.id.phone_reg_page)
    TextView phone_reg_page;

    @BindView(R.id.profileImageCIV)
    CircleImageView profileImageCIV;


    FireDatabase database;
    UserModel model;
    FireAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        database = new FireDatabase(this);
        fireAuth = new FireAuth(this);
        database.getUser(fireAuth.mAuth.getUid(), new FireDatabase.UserCallback() {
            @Override
            public void onCallback(UserModel model) {
                if (!model.getImage().isEmpty())
                    Glide.with(getBaseContext()).load(model.getImage()).into(profileImageCIV);
                Fname.setText(model.getName());
                Email.setText(model.getEmail());
                phone_reg_page.setText(model.getPhone());
            }
        });


    }


    @OnClick(R.id.btnLogout)
    void btnLogout() {
        fireAuth.mAuth.signOut();
        startActivity(new Intent(this, HomePage.class));
        finish();
    }
}
