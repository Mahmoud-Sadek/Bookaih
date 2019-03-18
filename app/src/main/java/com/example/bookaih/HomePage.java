package com.example.bookaih;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookaih.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity  {


    private Button REgbtn;
    private Button loginbtn2;
    private TextView wlc;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        REgbtn=(Button) findViewById(R.id.btn_reg_homepage);
        loginbtn2=(Button) findViewById(R.id.btn_login_homepage);
        wlc=(TextView) findViewById(R.id.view_wlc);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        //ckeck if user is already log in
        if(user != null){
            finish();;
            startActivity(new Intent(HomePage.this,categories.class));
        }
        REgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(HomePage.this, Register.class); //تستخدم للانتقال من تفاعل لاخر
                startActivity(n);

            }});

        loginbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nn=new Intent(HomePage.this, Login.class); //تستخدم للانتقال من تفاعل لاخر
                startActivity(nn);
            }});}
}
