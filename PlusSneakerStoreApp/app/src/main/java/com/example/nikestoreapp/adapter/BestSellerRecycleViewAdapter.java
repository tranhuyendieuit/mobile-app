package com.example.nikestoreapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nikestoreapp.Activity_Product_Details;
import com.example.nikestoreapp.R;
import com.example.nikestoreapp.model.BestSeller;

import java.util.ArrayList;

public class BestSellerRecycleViewAdapter extends RecyclerView.Adapter<BestSellerRecycleViewAdapter.BestSellerViewHolder>{
    private Context context;
    private ArrayList<BestSeller> bestSellerArrayList;
    public BestSellerRecycleViewAdapter(Context context, ArrayList<BestSeller> bestSellerArrayList) {
        this.context = context;
        this.bestSellerArrayList = bestSellerArrayList;
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_seller_recycleview_items,parent,false);
        return new BestSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerViewHolder holder, int position) {
       BestSeller bestSeller = bestSellerArrayList.get(position);
        Glide.with(context).load(bestSeller.getImgJpg()).into(holder.imageView);
        holder.ratingBar.setRating(bestSeller.getRating());
        holder.price_shoes.setText(bestSeller.getPrice()+"");
        holder.name_shoes.setText(bestSeller.getName());
        holder.gender_shoes.setText(bestSeller.getType());
        holder.item_bestseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity_Product_Details.class);
                intent.putExtra("data_bestseller",bestSeller);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bestSellerArrayList.size();
    }

    class BestSellerViewHolder extends RecyclerView.ViewHolder {
        private TextView name_shoes,price_shoes,gender_shoes;
        private RatingBar ratingBar;
        private ImageView imageView;
        private LinearLayout item_bestseller ;
        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);
            item_bestseller = itemView.findViewById(R.id.item_bestseller);
            name_shoes = itemView.findViewById(R.id.shoes_name_bestseller);
            price_shoes = itemView.findViewById(R.id.shoes_price_bestseller);
            gender_shoes = itemView.findViewById(R.id.shoes_gender_bestseller);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageView = itemView.findViewById(R.id.image_bestseller);
        }
    }
}
