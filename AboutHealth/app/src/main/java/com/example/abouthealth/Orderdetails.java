package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Orderdetails extends AppCompatActivity {

    ListView lst;
    Button btnbackhome;
    private  String[][] orderdetails={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sadp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        lst =findViewById(R.id.orderlistview11);
        btnbackhome=findViewById(R.id.medicinebackrohome);


        Database db= new Database(getApplicationContext(),"health",null,1);
        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData=db.getOrderData(username);


        orderdetails = new String[dbData.size()][];
        for( int i=0; i<orderdetails.length; i++)
        {
            orderdetails[i] =new String[5];
            String arrdata =dbData.get(i).toString();
            String[] strdata= arrdata.split(java.util.regex.Pattern.quote("$"));
            orderdetails[i][0]=strdata[0];
            orderdetails[i][1]=strdata[1];//+" "+strdata[3]
            if (strdata[7].compareTo("medicine")==0)
            {
                orderdetails[i][3]= "Del: "+strdata[4];
            }
            else {
                orderdetails[i][3]="Del: "+ strdata[4] + " "+strdata[5];

            }
            orderdetails[i][2]="RS: "+strdata[6];
            orderdetails[i][4]= strdata[7];
        }

        list = new ArrayList();
        for (int i=0;i<orderdetails.length;i++) {
            item = new HashMap<String, String>();
            item.put("line 1", orderdetails[i][0]);
            item.put("line 2", orderdetails[i][1]);
            item.put("line 3", orderdetails[i][2]);
            item.put("line 4", orderdetails[i][3]);
            item.put("line 5", orderdetails[i][4]);
            list.add(item);
        }
        sadp =new SimpleAdapter(this,list,
                R.layout.much_line,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        lst.setAdapter(sadp);

        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Orderdetails.this,HomeActivity.class));
            }
        });
    }
}