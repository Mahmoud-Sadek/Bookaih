package com.example.bookaih.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookaih.R;
import com.example.bookaih.admin.OrderWeddingDetail;
import com.example.bookaih.admin.UpdateIndividual;
import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.OrderIndividualModel;
import com.example.bookaih.model.OrderWeddingModel;
import com.example.bookaih.model.UserModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OrderWeddingAdatpter extends RecyclerView.Adapter<OrderWeddingAdatpter.ViewHolder> {

    private Context context;

    private ArrayList<OrderWeddingModel> data;
    FireAuth auth;
    FireDatabase database;

    public OrderWeddingAdatpter(Context context, ArrayList<OrderWeddingModel> data) {
        this.context = context;
        this.data = data;
        auth = new FireAuth(context);
        database = new FireDatabase(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        database.getUser(data.get(position).getUserId(), new FireDatabase.UserCallback() {
            @Override
            public void onCallback(UserModel model) {
                holder.nameTV.setText(model.getName());
                if (!model.getImage().isEmpty())
                    Glide.with(context).load(model.getImage()).into(holder.photoIV);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderWeddingDetail.class);
                intent.putExtra("model", data.get(position));
                context.startActivity(intent);
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
        @BindView(R.id.photoIV)
        ImageView photoIV;


        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
