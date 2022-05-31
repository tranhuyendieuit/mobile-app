package com.example.nikestoreapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nikestoreapp.Activity_Product_Details;
import com.example.nikestoreapp.R;
import com.example.nikestoreapp.model.NewRelease;

import java.util.ArrayList;

public class NewRelaseRecycleViewAdapter extends RecyclerView.Adapter<NewRelaseRecycleViewAdapter.NewReleaseViewHolder> {
    private ArrayList<NewRelease> arrNewRelease;
    private Context context;

    public NewRelaseRecycleViewAdapter(ArrayList<NewRelease> arrNewRelease, Context context) {
        this.arrNewRelease = arrNewRelease;
        this.context = context;
    }

    @NonNull
    @Override
    public NewReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_release_recycleview_items,parent,false);
        return new NewReleaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleaseViewHolder holder, int position) {
      NewRelease newRelease = arrNewRelease.get(position);
      if(newRelease==null){
          Log.d("AAA","Khong co dlieu");
      }
      holder.shoes_name.setText(newRelease.getName());
      holder.shoes_gender.setText(newRelease.getGender());
      holder.shoes_price.setText(newRelease.getPrice()+"");
      Glide.with(context).load(newRelease.getImgJpg()).into(holder.img_shoes);
      holder.item_newrelease.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(context, Activity_Product_Details.class);
              intent.putExtra("data_newrealse",newRelease);
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return arrNewRelease.size();
    }

    class NewReleaseViewHolder extends RecyclerView.ViewHolder {
        private TextView shoes_name,shoes_price,shoes_gender;
        private ImageView img_shoes;
        private LinearLayout item_newrelease;
        public NewReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            img_shoes = itemView.findViewById(R.id.img_shoe_release);
            shoes_name = itemView.findViewById(R.id.shoes_name);
            shoes_price = itemView.findViewById(R.id.shoes_price);
            shoes_gender = itemView.findViewById(R.id.shoes_gender);
            item_newrelease = itemView.findViewById(R.id.item_newrelease);
        }
    }
}
