package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sadp;
    TextView txttotal;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton,timecartbutton,cartback,cartcheck;
    private String[][] packages={};
    ListView lst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        datebutton = findViewById(R.id.medicinecartdatebutton);
        timecartbutton = findViewById(R.id.medicinecartdatebutton2);
        cartback = findViewById(R.id.medicinebuttonback1);
        cartcheck = findViewById(R.id.medicinecheckbutton12);
        txttotal = findViewById(R.id.medicinecarttextviewcost);
        lst = findViewById(R.id.medicinecartlistview);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "health", null, 1);

        float totalamount = 0;
        ArrayList dbdata = db.getCartData(username, "lab");
        Toast.makeText(this, "" + packages.length, Toast.LENGTH_LONG).show();


        packages = new String[dbdata.size()][];

        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }
        for (int i = 0; i < dbdata.size(); i++) {
            String arrData = dbdata.get(i).toString();
            String[] strdata = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strdata[0];
            packages[i][4] = "Cost: " + strdata[1] + "$";
            totalamount = totalamount + Float.parseFloat(strdata[1]);
        }
        txttotal.setText("Total Cost: " + totalamount);

//            String[] strdata = arrData.split(java.util.regex.Pattern.quote("$"));
//            packages[i][0] = "Hekkidfahkf";
//            packages[i][1] = "";
//            packages[i][2] = "";
//            packages[i][3] = "";
//            packages[i][4] = "Hey my name is";  // Corrected to use strdata
////            totalamount += Float.parseFloat(strdata[1]);
//        }
//
//        Toast.makeText(this, "Tade"+packages.length, Toast.LENGTH_SHORT).show();
//
//        txttotal.setText("Total Cost "+totalamount);

        list = new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("line 1",packages[i][0]);
            item.put("line 2",packages[i][1]);
            item.put("line 3",packages[i][2]);
            item.put("line 4",packages[i][3]);
            item.put("line 5",packages[i][4]);
            list.add(item);

        }
        sadp =new SimpleAdapter(this,list,
                R.layout.much_line,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        lst.setAdapter(sadp);

        cartback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,LaboratouryActivity.class));

            }
        });


        cartcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CartActivity.this,BookLabTest.class);
                it.putExtra("price",txttotal.getText());
                it.putExtra("date",datebutton.getText());
                it.putExtra("time",timecartbutton.getText());
                startActivity(it);

            }
        });

        //pick date

        initdatepicker();

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();
            }
        });

        initTimePicker();
        timecartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerDialog.show();
            }
        });



    }
    private void initdatepicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                datebutton.setText(i2 + "/" + i1 + "/" + i);
            }
        };
        Calendar cal =Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month =cal.get(Calendar.MONTH);
        int day =cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timecartbutton.setText(i+":"+i1);
            }
        };

        Calendar cal =Calendar.getInstance();
        int hrs =cal.get(Calendar.HOUR);
        int mins =cal.get(Calendar.MINUTE);


        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
}