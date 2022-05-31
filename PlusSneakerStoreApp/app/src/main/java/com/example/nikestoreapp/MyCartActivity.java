package com.example.nikestoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nikestoreapp.adapter.MyCartRecycleViewAdapter;
import com.example.nikestoreapp.api.RetrofitConfig;
import com.example.nikestoreapp.email.FormEmailCustomHtml;
import com.example.nikestoreapp.email.GMailSender;
import com.example.nikestoreapp.model.MyCart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MyCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton imgbtn_delete,img_backtoHome;
    public Button btn_checkOut;
    public static MyCartRecycleViewAdapter myCartRecycleViewAdapter ;
    public static ArrayList<MyCart> myCarts = new ArrayList<>();
    public static TextView txtSubTotal,txtFeeShipping,txtEstimasteTotal;
    public String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        initView();
        setupRecycleview();
        setupForPayment();
        clickToPayment();
    }

    private void initView() {

        txtSubTotal = findViewById(R.id.mycart_subtotal);
        txtFeeShipping = findViewById(R.id.mycart_feeshipping);
        txtEstimasteTotal = findViewById(R.id.mycart_estimastetotal);
        img_backtoHome = findViewById(R.id.mycart_backtohome);
        img_backtoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCartActivity.this,MainActivity.class));
            }
        });
        recyclerView = findViewById(R.id.recycleview_mycart);

    }
    public static void setupForPayment(){
        int total = 0;
        if(myCarts.size() == 0){
            txtEstimasteTotal.setText("0$");
            txtFeeShipping.setText("0$");
            txtSubTotal.setText("0$");
        }
        if(myCarts.size() > 0){
            for(int i = 0 ; i< myCarts.size() ; i++){
                total += myCarts.get(i).getPrice();
            }
            txtSubTotal.setText(" "+total+"$");
            txtFeeShipping.setText(" 5$");
            txtEstimasteTotal.setText(total+5+" $");
        }
    }
    private void setupRecycleview(){
        myCartRecycleViewAdapter = new MyCartRecycleViewAdapter(this,myCarts);
        recyclerView.setAdapter(myCartRecycleViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myCartRecycleViewAdapter.notifyDataSetChanged();
    }
    public void clickToPayment(){
        btn_checkOut = findViewById(R.id.btn_checkout);
        btn_checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myCarts.size() == 0){
                    Toast.makeText(MyCartActivity.this, "Nothing to pay, please add product to cart", Toast.LENGTH_SHORT).show();
                }else if(myCarts.size() >0){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        email = user.getEmail();
                        String total = txtSubTotal.getText().toString().trim();
                        String estimastetotal = txtEstimasteTotal.getText().toString().trim();
                        String messenge = new FormEmailCustomHtml().emailCustom(total,estimastetotal);
                        insertOrder(total);
                        sendEmail(Until.EMAIL,Until.PASSWORD,email,"Order confirmation successful",messenge);
                        myCarts.clear();
                        setupForPayment();
                    } else {
                        return;
                    }
                }
            }
        });
    }
    private void insertOrder(String total){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        int ordernumber = ThreadLocalRandom.current().nextInt(1001,9999);
        RetrofitConfig.retrofit.insert_oder(MainActivity.mEmailUser,ordernumber+"",formatter.format(date),total).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MyCartActivity.this, "", Toast.LENGTH_SHORT).show();
                Log.d("777",response.body()+"");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MyCartActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendEmail(final String Sender,final String Password,final String Receiver,final String Title,final String Message)
    {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(Sender,Password);
                    sender.sendMail(Title, "<b>"+Message+"</b>", Sender, Receiver);
                    makeAlert();
                    startActivity(new Intent(MyCartActivity.this,OrderComplete.class));
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }
    private void makeAlert(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MyCartActivity.this, "Mail Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

