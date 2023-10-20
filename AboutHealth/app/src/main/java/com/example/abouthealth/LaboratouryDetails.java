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

public class LaboratouryDetails extends AppCompatActivity {
    TextView textpackagename,textViewtotalcost;
    EditText  edmultiline;
    Button addtocart,btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratoury_details);
        textpackagename =findViewById(R.id.medicedetailstitle);
        textViewtotalcost =findViewById(R.id.medicinetotalcost);
        edmultiline =findViewById(R.id.medicineedit);
        addtocart =findViewById(R.id.medicineorderbutton);
        btnback =findViewById(R.id.medicinebuttonback1);

        edmultiline.setKeyListener(null);

        Intent intent=getIntent();
        textpackagename.setText(intent.getStringExtra("text1"));
        edmultiline.setText(intent.getStringExtra("text2"));
        textViewtotalcost.setText("Total cost : "+intent.getStringExtra("text3"));


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaboratouryDetails.this,LaboratouryActivity.class));
            }
        });

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username =sharedPreferences.getString("username","").toString();
                String product = textpackagename.getText().toString();
               // float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                String value = intent.getStringExtra("text3"); // Assuming "text3" is the key for the extra
                value = value.replace("$", ""); // Remove the dollar sign
                float price = Float.parseFloat(value);

                Database db = new Database(getApplicationContext(),"health",null,1);

                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "product is already in the cart", Toast.LENGTH_SHORT).show();

                }
                else {
                    db.addToCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "product added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LaboratouryDetails.this,LaboratouryActivity.class));
                }

            }
        });
    }
}