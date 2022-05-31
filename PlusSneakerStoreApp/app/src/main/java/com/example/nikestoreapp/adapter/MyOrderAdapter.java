package com.example.nikestoreapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nikestoreapp.R;
import com.example.nikestoreapp.model.MyCart;
import com.example.nikestoreapp.model.MyOrder;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder>  {
    private ArrayList<MyOrder> myOrderArrayList;

    public MyOrderAdapter(ArrayList<MyOrder> myOrderArrayList) {
        this.myOrderArrayList = myOrderArrayList;
    }
    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historyorder_item,parent,false);
        return new MyOrderViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        MyOrder myOrder = myOrderArrayList.get(position);
        holder.date.setText("Order Date:"+myOrder.getDate());
        holder.ordernumber.setText("Order Number: #"+myOrder.getOrdernumber());
        holder.total.setText("Total:"+myOrder.getTotal());
    }
    @Override
    public int getItemCount() {
        return myOrderArrayList.size();
    }
    class MyOrderViewHolder extends RecyclerView.ViewHolder{
        private TextView date,ordernumber,total;
        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_order);
            ordernumber = itemView.findViewById(R.id.order_number);
            total = itemView.findViewById(R.id.total_order);
        }
    }
}
