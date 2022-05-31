package com.example.nikestoreapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nikestoreapp.MyCartActivity;
import com.example.nikestoreapp.R;
import com.example.nikestoreapp.model.MyCart;

import java.util.ArrayList;

public class MyCartRecycleViewAdapter extends RecyclerView.Adapter<MyCartRecycleViewAdapter.MyCartViewHoler> {
    private Context context;
    private ArrayList<MyCart> myCartArrayList;

    public MyCartRecycleViewAdapter(Context context, ArrayList<MyCart> myCartArrayList) {
        this.context = context;
        this.myCartArrayList = myCartArrayList;
    }

    @NonNull
    @Override
    public MyCartViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_recycleview_items,parent,false);
        return new MyCartViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHoler holder, int position) {
        MyCart myCart = myCartArrayList.get(position);
        holder.cart_amount.setText(myCart.getAmount()+"");
        holder.cart_price.setText(myCart.getPrice()+"");
        holder.cart_name.setText(myCart.getName());
        holder.cart_size.setText(myCart.getSize()+"");
        holder.mycart_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartArrayList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                MyCartActivity.setupForPayment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myCartArrayList.size();
    }

    class MyCartViewHoler extends RecyclerView.ViewHolder {
        private TextView cart_price,cart_name,cart_size,cart_amount;
        private FrameLayout mycart_delete;
        public MyCartViewHoler(@NonNull View itemView) {
            super(itemView);
            mycart_delete = itemView.findViewById(R.id.mycart_delete);
            cart_name = itemView.findViewById(R.id.mycart_name);
            cart_price = itemView.findViewById(R.id.mycart_price);
            cart_size = itemView.findViewById(R.id.mycart_size);
            cart_amount = itemView.findViewById(R.id.mycart_amount);
        }
    }
}
