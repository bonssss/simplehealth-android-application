package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname,edaddress,edpincode,edphone;
    Button testbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname =findViewById(R.id.medicinebookfullname11);
        edaddress =findViewById(R.id.medicinebookaddress11);
        edpincode =findViewById(R.id.medicinebookpincode11);
        edphone= findViewById(R.id.medicnebookcontactnumber11);
        testbook =findViewById(R.id.medicinecheckbutton121);


        Intent intent=getIntent();
        String[] price =intent.getStringExtra("price").split(java.util.regex.Pattern.quote(":"));
        String date= intent.getStringExtra("date");
        //String time = intent.getStringExtra("time");

        testbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db =new Database(getApplicationContext(),"health",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edphone.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeitemcart(username,"medicine");

                Toast.makeText(getApplicationContext(), "Booked Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
            }
        });
    }
}