package com.example.nikestoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nikestoreapp.R;
import com.example.nikestoreapp.adapter.MyOrderAdapter;
import com.example.nikestoreapp.api.RetrofitConfig;
import com.example.nikestoreapp.model.MyOrder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile_Order extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<MyOrder> myOrders = new ArrayList<>();
    private ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_order);
        recyclerView = findViewById(R.id.recycleview_myorder);
        imgBack = findViewById(R.id.myorder_backtohome);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile_Order.this,MainActivity.class));
            }
        });
        getOrder();
    }
    private void getOrder(){
        RetrofitConfig.retrofit.get_myOrder(MainActivity.mEmailUser).enqueue(new Callback<ArrayList<MyOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<MyOrder>> call, Response<ArrayList<MyOrder>> response) {
                myOrders = response.body();
                Log.d("AAA",myOrders.toString());
                MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrders);
                recyclerView.setAdapter(myOrderAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MyProfile_Order.this));
            }

            @Override
            public void onFailure(Call<ArrayList<MyOrder>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}