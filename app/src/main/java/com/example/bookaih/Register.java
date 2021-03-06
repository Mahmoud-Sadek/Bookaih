package com.example.bookaih;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSIONS = 20;
    private EditText Fname;
    private EditText Lname;
    private EditText Email;
    private EditText password;

    FireAuth auth;
    FireDatabase database;
    UserModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(Register.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(Register.this,
                        Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (Register.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (Register.this, Manifest.permission.READ_CONTACTS)) {
                Snackbar.make(findViewById(android.R.id.content),
                        "Please Grant Permissions",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(Register.this,
                                        new String[]{Manifest.permission
                                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                                        REQUEST_PERMISSIONS);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(Register.this,
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSIONS);
            }
        } else {
            //Call whatever you want
            myMethod();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button reg = findViewById(R.id.btnreg_reg_page);
        Fname = findViewById(R.id.first_name);
        Lname = findViewById(R.id.last_name);
        Email = findViewById(R.id.email_reg_page);
        password = findViewById(R.id.password_reg_page);

        auth = new FireAuth(this);
        database = new FireDatabase(this);
        model = new UserModel();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validat()) {
                    String email = Email.getText().toString().trim();
                    String name = Fname.getText().toString().trim()+" " +Lname.getText().toString().trim();
                    String passworduser = password.getText().toString().trim();

                    model.setName(name);
                    model.setEmail(email);
                    model.setPassword(passworduser);

                    auth.signUp(model);

                    //upload to database
                    //.trime()  is to remove all the white spaces the user could enter

                    /*firebaseAuth.createUserWithEmailAndPassword(email, passworduser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //to tell the user that he regsiter operation is success or fail
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, categories.class));
                            } else {
                                Toast.makeText(Register.this, "Register Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if ((grantResults.length > 0) && (grantResults[0] +
                        grantResults[1]) == PackageManager.PERMISSION_GRANTED) {
                    //Call whatever you want
                    myMethod();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Enable Permissions from settings",
                            Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    startActivity(intent);
                                }
                            }).show();
                }
                return;
            }
        }
    }

    private void myMethod() {
    }

    @SuppressLint("NewApi")
    private Boolean validat() {

        boolean result = false;
        String fname = Fname.getText().toString();
        String lname = Lname.getText().toString();
        String email = Email.getText().toString();
        String pass = password.getText().toString();

        if (fname.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please Enter All Details!", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public void onClick(View v) {

    }

    //@Override
    public void onPermissionsGranted(final int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }
}
