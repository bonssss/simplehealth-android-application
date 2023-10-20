package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MedicineDetails extends AppCompatActivity {

    TextView txt1,txt2packagename;

    EditText editText1;

    Button addtocart,btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        txt1=findViewById(R.id.medicinetotalcost);
        editText1=findViewById(R.id.medicineedit);
        editText1.setKeyListener(null);
        txt2packagename=findViewById(R.id.medicedetailstitle);

        addtocart=findViewById(R.id.medicineorderbutton);
        btnback=findViewById(R.id.medicinebuttonback1);

        Intent intent=getIntent();
        txt2packagename.setText(intent.getStringExtra("text1"));
        editText1.setText(intent.getStringExtra("text2"));
        txt1.setText("Total cost : "+intent.getStringExtra("text3"));


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineDetails.this,BuyMedicineActivity.class));
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username =sharedPreferences.getString("username","").toString();
                String product = txt2packagename.getText().toString();
                Float price =Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"health",null,1);

                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "product is already in the cart", Toast.LENGTH_SHORT).show();

                }
                else {
                    db.addToCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(), "product added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MedicineDetails.this,BuyMedicineActivity.class));

                }


            }
        });


    }
}