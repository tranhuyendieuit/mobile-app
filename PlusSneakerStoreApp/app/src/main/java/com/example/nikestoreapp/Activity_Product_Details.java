package com.example.nikestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nikestoreapp.model.BestSeller;
import com.example.nikestoreapp.model.MyCart;
import com.example.nikestoreapp.model.NewRelease;
import com.google.android.material.tabs.TabLayout;

public class Activity_Product_Details extends AppCompatActivity {
    private TextView detail_name,detail_description,detail_price;
    private RatingBar detail_ratingbar;
    private ImageView detail_image;
    private ImageButton back_home_detailproduct;
    private TabLayout tabLayout;
    private Button btn_addToCart;
    int position_size_checkedTab;
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        intent = getIntent();
        anhXa();
        receiveData();
        addShoesToCart();
    }
    private void anhXa(){
        detail_name =findViewById(R.id.detail_name);
        detail_ratingbar = findViewById(R.id.detail_rating);
        detail_description = findViewById(R.id.detail_description);
        detail_price = findViewById(R.id.detail_price);
        detail_image = findViewById(R.id.detail_image);
        back_home_detailproduct = findViewById(R.id.mycart_backtohome);
        tabLayout = findViewById(R.id.tablayout);
        btn_addToCart = findViewById(R.id.btn_addToCart);
        back_home_detailproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Product_Details.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void receiveData(){
        BestSeller bestSeller = (BestSeller) intent.getSerializableExtra("data_bestseller");
        NewRelease newRelease = (NewRelease) intent.getSerializableExtra("data_newrealse");
            if(bestSeller != null && newRelease == null){
                Glide.with(this).load(bestSeller.getImgPng()).into(detail_image);
                detail_name.setText(bestSeller.getName());
//                detail_description.setText(bestSeller.getDescription());
                detail_price.setText(bestSeller.getPrice()+"");
                detail_ratingbar.setRating(bestSeller.getRating());
            }
            if(newRelease != null && bestSeller == null){
                Glide.with(this).load(newRelease.getImgPng()).into(detail_image);
                detail_name.setText(newRelease.getName());
                detail_description.setText(newRelease.getDescription());
                detail_price.setText(newRelease.getPrice()+"");
                detail_ratingbar.setRating(newRelease.getRating());
            }
    }
    private int sizeShoesSelected(){
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position_size_checkedTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return position_size_checkedTab;
    }
    private void addShoesToCart(){
        BestSeller bestSeller = (BestSeller) intent.getSerializableExtra("data_bestseller");
        NewRelease newRelease = (NewRelease) intent.getSerializableExtra("data_newrealse");
        btn_addToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(bestSeller != null){
                       MyCartActivity.myCarts.add(new MyCart(bestSeller.getImgPng(),bestSeller.getName(),bestSeller.getPrice(),sizeShoesSelected(),1));
                       Intent intent = new Intent(Activity_Product_Details.this,MyCartActivity.class);
                       startActivity(intent);
                }
                if(newRelease != null){
                        MyCartActivity.myCarts.add(new MyCart(newRelease.getImgPng(),newRelease.getName(),newRelease.getPrice(),sizeShoesSelected(),1));
                        Intent intent = new Intent(Activity_Product_Details.this,MyCartActivity.class);
                        startActivity(intent);
                }
            }
        });
    }
}