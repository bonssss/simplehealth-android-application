package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString() ;
        Toast.makeText(getApplicationContext(),"welcome "+username,Toast.LENGTH_SHORT) .show();

        CardView exit = findViewById(R.id.sixthgrid);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     SharedPreferences.Editor editor= sharedPreferences.edit();
                     editor.clear();
                     editor.apply();
                     startActivity(new Intent(HomeActivity.this,Login.class));



            }
        });
        
        CardView Doctors=findViewById(R.id.doctors);
        Doctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                     startActivity(new Intent(HomeActivity.this, DoctorsActivity.class));


            }
        });
        CardView labresult=findViewById(R.id.labtest);
        labresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LaboratouryActivity.class));
            }
        });

        CardView orderdetail =findViewById(R.id.orderdetails);
        orderdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Orderdetails.class));
            }
        });

        CardView buymedicine =findViewById(R.id.buymedicine);
        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });
        CardView heatlthnews =findViewById(R.id.calender);
        heatlthnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, HealthNews.class));
            }
        });

        
    }
}