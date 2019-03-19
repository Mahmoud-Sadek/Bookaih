package com.example.bookaih;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Login extends AppCompatActivity {


    @BindView(R.id.email_loginpage)
    EditText email_loginpage;
    @BindView(R.id.password_loginpage)
    EditText password_loginpage;


    FireAuth auth;
    FireDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = new FireAuth(this);
        database = new FireDatabase(this);


    }


    @OnClick(R.id.btnlogin_loginpage)
    void btnlogin_loginpage() {
        if (!validat()) {
            return;
        }
        auth.signIn(email_loginpage.getText().toString(), password_loginpage.getText().toString());
    }


    @SuppressLint("NewApi")
    private Boolean validat() {
        boolean result = false;
        String email = email_loginpage.getText().toString();
        String pass = password_loginpage.getText().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please Enter All Details!", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (auth.mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, categories.class));
        }
    }
}

