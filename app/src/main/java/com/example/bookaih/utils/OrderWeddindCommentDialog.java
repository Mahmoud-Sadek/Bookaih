package com.example.bookaih.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookaih.MeetingActivity;
import com.example.bookaih.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OrderWeddindCommentDialog extends Dialog {
    orderCommentAction orderCommentAction;
    EditText commentInput, placeInput;
    Button done, back, dateInput;

    public OrderWeddindCommentDialog(@NonNull final Context context, final orderCommentAction orderCommentAction) {
        super(context);
        setContentView(R.layout.order_wedding_comment_dialog);
        this.orderCommentAction = orderCommentAction;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        commentInput = findViewById(R.id.coment_txt_input);
        dateInput = findViewById(R.id.date_txt_input);
        placeInput = findViewById(R.id.place_txt_input);

        done = findViewById(R.id.apply_btn);
        back = findViewById(R.id.back_btn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentInput.getText().toString().isEmpty()) {
                    commentInput.setError("error");
                } else if (dateInput.getText().toString().isEmpty()) {
                    dateInput.setError("error");
                } else if (placeInput.getText().toString().isEmpty()) {
                    placeInput.setError("error");
                } else {
                    orderCommentAction.onGetComment(commentInput.getText().toString()
                            , dateInput.getText().toString()
                            , placeInput.getText().toString());
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "MM-dd-yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        if (myCalendar.getTime().before(new Date()))
                            Toast.makeText(context, "وقت غير صالح", Toast.LENGTH_SHORT).show();
                        else
                            dateInput.setText(sdf.format(myCalendar.getTime()));
                    }

                };
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    public interface orderCommentAction {
        void onGetComment(String comment, String date, String place);
    }

}