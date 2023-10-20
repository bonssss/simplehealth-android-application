package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookLabTest extends AppCompatActivity {
    EditText edname,edaddress,edpincode,edphone;
    Button testbook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_lab_test);

        edname =findViewById(R.id.medicinebookfullname1);
        edaddress =findViewById(R.id.medicinebookaddress1);
        edpincode =findViewById(R.id.medicinebookpincode1);
        edphone= findViewById(R.id.medicnebookcontactnumber1);
        testbook =findViewById(R.id.medicinecheckbutton12);

        Intent intent=getIntent();
        String[] price =intent.getStringExtra("price").split(java.util.regex.Pattern.quote(":"));
        String date= intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        testbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db =new Database(getApplicationContext(),"health",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edphone.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeitemcart(username,"lab");

                Toast.makeText(getApplicationContext(), "Booked Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BookLabTest.this,HomeActivity.class));
            }
        });
    }
}