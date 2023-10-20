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

public class LaboratouryActivity extends AppCompatActivity {

    private  String[][] packages ={
            {"package 1 : Full Body Checkup","","","","1000$"},
            {"package 2 : Blood Glucose Fasting ","","","","1000$"},
            {"package 3 : HIV","","","","1000$"},
            {"package 4 : Kidney","","","","1000$"},
            {"package 5 : Immune Check","","","","1000$"},

    };
    private String[] packageDetails ={
            "Blood Glucose Fasting\n"+"Complete Hemogram\n"
            +"HbAIC\n"+"Iron Studies\n"+
                    "Kidney Function\n"+"Ldh Lactate,Serum\n"+
                    "Lipid Profile\n"+
                    "Liver Function",
            "Blood Glucose Fasting",
            "HIV",
            "Kidney",
            "Complete Hemogram\n"+
                    "CRP (C reactive Protein),Serum\n"+
                    "Iron Studies\n"+
                    "Kidney Function\n"+
                    "Liver Function"+
                    "Lipid Profile\n"


    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sadp;
    Button orderbtn,btnback;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratoury);

        orderbtn=findViewById(R.id.medicineorder);
        btnback=findViewById(R.id.medicinebackrohome);
        listView =findViewById(R.id.medicinelistview);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaboratouryActivity.this,HomeActivity.class));
            }
        });

        list=new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item= new HashMap<String,String>();
            item.put("line 1",packages[i][0]);
            item.put("line 2",packages[i][1]);
            item.put("line 3",packages[i][2]);
            item.put("line 4",packages[i][3]);
            item.put("line 5","Total Cost:"+packages[i][4]+"");
            list.add(item);

        }
        sadp = new SimpleAdapter(this,list,
        R.layout.much_line,
                new  String[] {"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        listView.setAdapter(sadp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it= new Intent(LaboratouryActivity.this, LaboratouryDetails.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packageDetails[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });


        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaboratouryActivity.this,CartActivity.class));

            }
        });


    }
}