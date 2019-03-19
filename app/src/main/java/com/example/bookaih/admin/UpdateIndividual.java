package com.example.bookaih.admin;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookaih.R;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.firebase.FireStorage;
import com.example.bookaih.model.IndividualModel;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateIndividual extends AppCompatActivity implements IPickResult {


    @BindView(R.id.profileImageCIV)
    ImageView profileImageCIV;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtDescription)
    EditText edtDescription;
    @BindView(R.id.edtPrice)
    EditText edtPrice;
    @BindView(R.id.edtType1)
    EditText edtType1;
    @BindView(R.id.edtType2)
    EditText edtType2;
    @BindView(R.id.edtshreta)
    EditText edtshreta;


    FireDatabase database;
    FireStorage storage;
    IndividualModel model;
    Bitmap userImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_individual);
        ButterKnife.bind(this);
        database = new FireDatabase(this);
        storage = new FireStorage(this);
        model = (IndividualModel) getIntent().getSerializableExtra("model");

        edtName.setText(model.getName());
        edtDescription.setText(model.getDescription());
        edtPrice.setText(model.getPrice());
        edtshreta.setText(model.getShreta());
        edtType1.setText(model.getFlowerType());
        edtType2.setText(model.getPaperType());
        Glide.with(this).load(model.getImage()).into(profileImageCIV);


    }


    @OnClick(R.id.btnreg_edit_page)
    void btnreg_edit_page() {
        if (!validate()) {
            return;
        }

        model.setName(edtName.getText().toString());
        model.setPrice(edtPrice.getText().toString());
        model.setDescription(edtDescription.getText().toString());
        model.setFlowerType(edtType1.getText().toString());
        model.setPaperType(edtType2.getText().toString());
        model.setShreta(edtshreta.getText().toString());

        if (userImage != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("loading...");
            progressDialog.show();
            storage.uploadImage(userImage, new FireStorage.urlCallback() {
                @Override
                public void onCallback(String url) {
                    progressDialog.dismiss();
                    model.setImage(url);
                    database.editIndividual(model);

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "لم يتم إختيار صورة", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btnreg_delete_page)
    void btnreg_delete_page() {
        database.deleteIndividual(model.getId());
    }

    @OnClick(R.id.profileImageCIV)
    void profileImageCIV() {
        PickImageDialog.build(new PickSetup()).show(this);
    }

    private boolean validate() {
        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Error");
            edtName.requestFocus();
            return false;
        }
        if (edtDescription.getText().toString().isEmpty()) {
            edtDescription.setError("Error");
            edtDescription.requestFocus();
            return false;
        }
        if (edtPrice.getText().toString().isEmpty()) {
            edtPrice.setError("Error");
            edtPrice.requestFocus();
            return false;
        }
        if (edtType1.getText().toString().isEmpty()) {
            edtType1.setError("Error");
            edtType1.requestFocus();
            return false;
        }
        if (edtType2.getText().toString().isEmpty()) {
            edtType2.setError("Error");
            edtType2.requestFocus();
            return false;
        }

        if (edtshreta.getText().toString().isEmpty()) {
            edtshreta.setError("Error");
            edtshreta.requestFocus();
            return false;
        }


        return true;
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        if (pickResult.getError() == null) {
            profileImageCIV.setImageBitmap(pickResult.getBitmap());
            userImage = pickResult.getBitmap();
        }

    }
}

