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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView txtview;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,btnbook,btback,timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ed1 = findViewById(R.id.bookfullname);
        ed2 = findViewById(R.id.bookaddress);
        ed3 = findViewById(R.id.bookpincode);
        ed4 = findViewById(R.id.bookcontactnumber);
        txtview = findViewById(R.id.medicinebooktitile);
        dateButton= findViewById(R.id.medicinecartdatebutton);
        timeButton = findViewById(R.id.medicinecartdatebutton2);
        btnbook= findViewById(R.id.checkbutton);
        btback = findViewById(R.id.medicinebuttonback1);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it =getIntent();
        String title =it.getStringExtra("text1");
        String full_name =it.getStringExtra("text2");
        String address =it.getStringExtra("text3");
        String phone_number =it.getStringExtra("text4");
        String fees =it.getStringExtra("text5");

        //set edityable texts
        txtview.setText(title);
        ed1.setText(full_name);
        ed2.setText(address);
        ed3.setText(phone_number);
        ed4.setText("fees:"+fees+"$");

        initdatepicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timePickerDialog.show();
            }
        });

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppointmentActivity.this,DoctorsActivity.class));
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Database db = new Database(getApplicationContext(),"health",null,1);
                SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username =sharedPreferences.getString("username","").toString();


                if (db.checkAppointment(username,title+"=>"+full_name,address,phone_number,dateButton.getText().toString(),timeButton.getText().toString())==1)
                {
                    Toast.makeText(getApplicationContext(), "Appointment already reserved", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addOrder(username,"title"+ "=>"+full_name,address,phone_number,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(), "Appointment done successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AppointmentActivity.this,HomeActivity.class));
                }





            }
        });
    }
    private void initdatepicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(i2 + "/" + i1 + "/" + i);
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
                timeButton.setText(i+":"+i1);
            }
        };

        Calendar cal =Calendar.getInstance();
        int hrs =cal.get(Calendar.HOUR);
        int mins =cal.get(Calendar.MINUTE);


        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);




    }
    }
