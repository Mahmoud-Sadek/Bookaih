package com.example.bookaih.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookaih.R;
import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.MeetModel;
import com.example.bookaih.model.UserModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CanceldMeetingAdatpter extends RecyclerView.Adapter<CanceldMeetingAdatpter.ViewHolder> {

    private Context context;

    private ArrayList<MeetModel> data;
    FireAuth auth;
    FireDatabase database;

    public CanceldMeetingAdatpter(Context context, ArrayList<MeetModel> data) {
        this.context = context;
        this.data = data;
        auth = new FireAuth(context);
        database = new FireDatabase(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_meet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


database.getUser(data.get(position).getUserId(), new FireDatabase.UserCallback() {
    @Override
    public void onCallback(UserModel model) {
        holder.nameTV.setText(model.getName());
        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        holder.dateTV.setText(sdf.format(data.get(position).getDay()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
});



    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTV)
        TextView nameTV;
        @BindView(R.id.dateTV)
        TextView dateTV;
        @BindView(R.id.photoIV)
        ImageView photoIV;


        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
