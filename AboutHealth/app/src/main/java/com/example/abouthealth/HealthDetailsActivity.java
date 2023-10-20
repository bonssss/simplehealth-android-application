package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthDetailsActivity extends AppCompatActivity {
    TextView txt1;
    ImageView img;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);

        btnback=findViewById(R.id.healthdeatailsback);
        txt1 =findViewById(R.id.hdtext);
        img=findViewById(R.id.imageview);

        Intent intent=getIntent();
        txt1.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            int resId =bundle.getInt("text2");
            img.setImageResource(resId);
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthDetailsActivity.this, HealthNews.class));


            }
        });
    }
}