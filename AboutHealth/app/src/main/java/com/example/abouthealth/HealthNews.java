package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthNews extends AppCompatActivity {

    private String[][] health_details1 = {

            {"Walking Daily", "", "", "", "more details"},
            {"covid 19", "", "", "", "more details"},
            {"Smoking", "", "", "", "more details"},
            {"Menstrual", "", "", "", "more details"},
            {"health Gut","","","","more details"},
    };

    private int[] images ={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };

    ArrayList list;
    SimpleAdapter sadp;
    HashMap<String,String> item;

    Button btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_news);

        btnback=findViewById(R.id.backtodoctors1);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthNews.this, HomeActivity.class));

            }
        });
        list=new ArrayList();
        for (int i=0;i<health_details1.length;i++)
        {
            item= new HashMap<String,String>();
            item.put("line 1",health_details1[i][0]);
            item.put("line 2",health_details1[i][1]);
            item.put("line 3",health_details1[i][2]);
            item.put("line 4",health_details1[i][3]);
            item.put("line 5",health_details1[i][4]);
            list.add(item);

        }
        sadp =new SimpleAdapter(this,list,
                R.layout.much_line,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
       ListView lst=findViewById(R.id.familydoctors1);
        lst.setAdapter(sadp);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it= new Intent(HealthNews.this,HealthDetailsActivity.class);

                it.putExtra("text1",health_details1[i][0]);
                it.putExtra("text2",images[i]);

                startActivity(it);


            }
        });
    }
}