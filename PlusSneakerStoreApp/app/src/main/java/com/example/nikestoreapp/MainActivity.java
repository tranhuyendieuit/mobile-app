package com.example.nikestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikestoreapp.adapter.BestSellerRecycleViewAdapter;
import com.example.nikestoreapp.adapter.NewRelaseRecycleViewAdapter;
import com.example.nikestoreapp.api.RetrofitConfig;
import com.example.nikestoreapp.model.Account;
import com.example.nikestoreapp.model.BestSeller;
import com.example.nikestoreapp.model.NewRelease;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout navDrawer;
    private RecyclerView recyclerView,recyclerViewBestSeller;
    private NewRelaseRecycleViewAdapter newRelaseRecycleViewAdapter;
    private BestSellerRecycleViewAdapter bestSellerRecycleViewAdapter;
    public ImageButton mycart_button;
    public ImageView imgOpenNav;
    public TextView txt_nameFirebase;
    public EditText txtSearch;
    public NavigationView navigationView;
    public ArrayList<BestSeller> bestSellerArrayList;
    public ArrayList<NewRelease> newReleaseArrayList ;
    public static String mEmailUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        getData();
        showUserInformation();
    }
    public void getData(){
        RetrofitConfig.retrofit.getInfor_NewRealeses().enqueue(new Callback<ArrayList<NewRelease>>() {
            @Override
            public void onResponse(Call<ArrayList<NewRelease>> call, Response<ArrayList<NewRelease>> response) {
                if(response.body()!= null){
                    newReleaseArrayList = response.body();
                    addDataNewRealese(newReleaseArrayList);
                }else{
                    Log.d("AAA","Không có dữ liệu");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewRelease>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        RetrofitConfig.retrofit.getInfor_BestSeller().enqueue(new Callback<ArrayList<BestSeller>>() {
            @Override
            public void onResponse(Call<ArrayList<BestSeller>> call, Response<ArrayList<BestSeller>> response) {
                if(response.body() != null){
                    bestSellerArrayList = response.body();
                    addDataBestSeller(bestSellerArrayList);
                    Log.d("AAA",bestSellerArrayList+"");
                }else{
                    Log.d("AAA",response.body()+"Null bestseller");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BestSeller>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addDataNewRealese(ArrayList<NewRelease> arrNewrealeses){
        newRelaseRecycleViewAdapter = new NewRelaseRecycleViewAdapter(arrNewrealeses,MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(newRelaseRecycleViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    public void addDataBestSeller(ArrayList<BestSeller> arrBestseller){
        bestSellerRecycleViewAdapter = new BestSellerRecycleViewAdapter(getApplicationContext(),arrBestseller);
        recyclerViewBestSeller.setHasFixedSize(true);
        recyclerViewBestSeller.setAdapter(bestSellerRecycleViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewBestSeller.setLayoutManager(linearLayoutManager);
    }
    public void anhXa(){
        imgOpenNav = findViewById(R.id.imageView5);
        navDrawer = findViewById(R.id.drawer_layout);
        mycart_button = findViewById(R.id.mycart_button);
        recyclerView = findViewById(R.id.recycleview);
        recyclerViewBestSeller = findViewById(R.id.recycleview_bestseller);
        navigationView = findViewById(R.id.navigation_view);
        txt_nameFirebase = navigationView.getHeaderView(0).findViewById(R.id.name_firebase);
        navigationView.setNavigationItemSelectedListener(this);
        txtSearch = findViewById(R.id.search);
        txtSearch.clearFocus();
        txtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && txtSearch.getText().toString() != null && txtSearch.getText().toString() != "") {
                    String searchParam = txtSearch.getText().toString();
                    RetrofitConfig.retrofit.getInfor_NewRealeses().enqueue(new Callback<ArrayList<NewRelease>>() {
                        @Override
                        public void onResponse(Call<ArrayList<NewRelease>> call, Response<ArrayList<NewRelease>> response) {
                            if(response.body()!= null){
                                List<NewRelease> responseDatas = response.body();
                                newReleaseArrayList = new ArrayList<>();
                                for (NewRelease responseData : responseDatas) {
                                    if (responseData.getName().toLowerCase().contains(searchParam.toLowerCase().trim())) {
                                        newReleaseArrayList.add(responseData);
                                    }
                                }
                                if (!newReleaseArrayList.isEmpty()) {
                                    addDataNewRealese(newReleaseArrayList);
                                } else {
                                    addDataNewRealese(newReleaseArrayList);
                                    Log.d("Data don't exist","Không có dữ liệu");
                                }
                            }else{
                                Log.d("Data don't exist","Không có dữ liệu");
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<NewRelease>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                    });
                    RetrofitConfig.retrofit.getInfor_BestSeller().enqueue(new Callback<ArrayList<BestSeller>>() {
                        @Override
                        public void onResponse(Call<ArrayList<BestSeller>> call, Response<ArrayList<BestSeller>> response) {
                            if(response.body() != null){
                                List<BestSeller> responseDatas = response.body();
                                bestSellerArrayList = new ArrayList<>();
                                for (BestSeller responseData : responseDatas) {
                                    if (responseData.getName().toLowerCase().contains(searchParam.toLowerCase().trim())) {
                                        bestSellerArrayList.add(responseData);
                                    }
                                }
                                if (!bestSellerArrayList.isEmpty()) {
                                    addDataBestSeller(bestSellerArrayList);
                                } else {
                                    addDataBestSeller(bestSellerArrayList);
                                    Log.d("Data don't exist","Không có dữ liệu");
                                }
                            }else{
                                Log.d("Data don't exist","Không có dữ liệu");
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<BestSeller>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return true;
            }
        });
        mycart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyCartActivity.class);
                startActivity(intent);
            }
        });
        imgOpenNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });
    }
    private void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mEmailUser = user.getEmail();
            txt_nameFirebase.setText("Email :"+ user.getEmail());
        } else {
            return;
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            navDrawer.closeDrawer(GravityCompat.START);
        }else if(id == R.id.nav_mycart){
            navDrawer.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(MainActivity.this,MyCartActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }else if(id == R.id.nav_myprofile){
            Intent intent = new Intent(MainActivity.this,MyProfile_Order.class);
            startActivity(intent);
        }
        navDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_navgation,menu);
        return super.onCreateOptionsMenu(menu);
    }
}