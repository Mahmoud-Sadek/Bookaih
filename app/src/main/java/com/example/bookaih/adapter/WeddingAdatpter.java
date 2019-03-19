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
import com.example.bookaih.DetailActivity;
import com.example.bookaih.R;
import com.example.bookaih.firebase.FireAuth;
import com.example.bookaih.firebase.FireDatabase;
import com.example.bookaih.model.WeddingModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeddingAdatpter extends RecyclerView.Adapter<WeddingAdatpter.ViewHolder> {

    FireAuth auth;
    FireDatabase database;
    private Context context;
    private ArrayList<WeddingModel> data;

    public WeddingAdatpter(Context context, ArrayList<WeddingModel> data) {
        this.context = context;
        this.data = data;
        auth = new FireAuth(context);
        database = new FireDatabase(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_individual, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DetailActivity.model = data.get(position);
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
        holder.nameTV.setText(data.get(position).getName());
        holder.priceTxt.setText(data.get(position).getPrice());
        Glide.with(context).load(data.get(position).getImage()).into(holder.photoIV);


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
        @BindView(R.id.priceTxt)
        TextView priceTxt;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
