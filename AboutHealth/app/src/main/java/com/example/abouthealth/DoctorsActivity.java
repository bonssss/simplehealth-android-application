package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        CardView back =findViewById(R.id.medicinebackrohome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorsActivity.this,HomeActivity.class));
            }
        });

        CardView doctor1= findViewById(R.id.doctor1);
        doctor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(DoctorsActivity.this,Doctor1Activity.class);
                it.putExtra("title","Family Doctors");
                startActivity(it);


            }
        });
        CardView doctor2= findViewById(R.id.doctor2);
        doctor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(DoctorsActivity.this,Doctor1Activity.class);
                it.putExtra("title","Dentist");
                startActivity(it);


            }
        });
        CardView doctor3= findViewById(R.id.doctor3);
        doctor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(DoctorsActivity.this,Doctor1Activity.class);
                it.putExtra("title","surgeon");
                startActivity(it);


            }
        });
        CardView doctor4= findViewById(R.id.doctor4);
        doctor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(DoctorsActivity.this,Doctor1Activity.class);
                it.putExtra("title","Eye Doctors");
                startActivity(it);


            }
        });
        CardView doctor5= findViewById(R.id.doctor5);
        doctor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(DoctorsActivity.this,Doctor1Activity.class);
                it.putExtra("title","Brain Doctors");
                startActivity(it);


            }
        });

    }
}