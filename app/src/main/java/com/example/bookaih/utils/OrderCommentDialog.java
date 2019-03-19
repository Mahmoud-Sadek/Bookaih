package com.example.bookaih.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.bookaih.R;

public class OrderCommentDialog extends Dialog {
    orderCommentAction orderCommentAction;
    EditText codeInput;
    Button done, back;

    public OrderCommentDialog(@NonNull final Context context, final orderCommentAction orderCommentAction) {
        super(context);
        setContentView(R.layout.order_comment_dialog);
        this.orderCommentAction = orderCommentAction;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        codeInput = findViewById(R.id.code_txt_input);
        done = findViewById(R.id.apply_btn);
        back = findViewById(R.id.back_btn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codeInput.getText().toString().isEmpty()) {
                    codeInput.setError("error");
                } else {
                    orderCommentAction.onGetComment(codeInput.getText().toString());
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface orderCommentAction {
        void onGetComment(String code);
    }
}