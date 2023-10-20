package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Doctor1Activity extends AppCompatActivity {
    private String[][] doctor_details1 ={

            {"Doctor Name: Helen Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"},
            {"Doctor Name: Abrham Kelkay","Hospital Address: Addis Ababa","Exp:10years","Mobile:0923456767","10000"},
            {"Doctor Name: Abel Kelkay","Hospital Address: Addis Ababa","Exp:5years","Mobile:0923456767","10000"},
            {"Doctor Name: Eden Biruk","Hospital Address: Addis Ababa","Exp:4years","Mobile:0923456767","10000"},
            {"Doctor Name: Meron Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"}

    };
    private String[][] doctor_details2 ={

            {"Doctor Name: Helen Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"},
            {"Doctor Name: Abrham Kelkay","Hospital Address: Addis Ababa","Exp:10years","Mobile:0923456767","10000"},
            {"Doctor Name: Abel Kelkay","Hospital Address: Addis Ababa","Exp:5years","Mobile:0923456767","10000"},
            {"Doctor Name: Eden Biruk","Hospital Address: Addis Ababa","Exp:4years","Mobile:0923456767","10000"},
            {"Doctor Name: Meron Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"}

    };
    private String[][] doctor_details3 ={

            {"Doctor Name: Helen Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"},
            {"Doctor Name: Abrham Kelkay","Hospital Address: Addis Ababa","Exp:10years","Mobile:0923456767","10000"},
            {"Doctor Name: Abel Kelkay","Hospital Address: Addis Ababa","Exp:5years","Mobile:0923456767","10000"},
            {"Doctor Name: Eden Biruk","Hospital Address: Addis Ababa","Exp:4years","Mobile:0923456767","10000"},
            {"Doctor Name: Meron Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"}

    };
    private String[][] doctor_details4 ={

            {"Doctor Name: Helen Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"},
            {"Doctor Name: Abrham Kelkay","Hospital Address: Addis Ababa","Exp:10years","Mobile:0923456767","10000"},
            {"Doctor Name: Abel Kelkay","Hospital Address: Addis Ababa","Exp:5years","Mobile:0923456767","10000"},
            {"Doctor Name: Eden Biruk","Hospital Address: Addis Ababa","Exp:4years","Mobile:0923456767","10000"},
            {"Doctor Name: Meron Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"}

    };
    private String[][] doctor_details5 ={

            {"Doctor Name: Belay Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"},
            {"Doctor Name: Abrham Kelkay","Hospital Address: Addis Ababa","Exp:10years","Mobile:0923456767","10000"},
            {"Doctor Name: Abel Kelkay","Hospital Address: Addis Ababa","Exp:5years","Mobile:0923456767","10000"},
            {"Doctor Name: Eden Biruk","Hospital Address: Addis Ababa","Exp:4years","Mobile:0923456767","10000"},
            {"Doctor Name: Meron Kelkay","Hospital Address: Addis Ababa","Exp:2years","Mobile:0923456767","10000"}

    };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    ArrayList arrlist;
    SimpleAdapter adp;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor1);
        btn= findViewById(R.id.backtodoctors);

        tv= findViewById(R.id.medicinetitle);
        Intent it =getIntent();
        String title =it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Doctors")==0)
            doctor_details =doctor_details1;
        else
            if (title.compareTo("Dentist")==0)
            doctor_details =doctor_details2;
              else
        if (title.compareTo("Surgeon")==0)
            doctor_details =doctor_details3;
        else
        if (title.compareTo("Eye Doctors")==0)
            doctor_details =doctor_details4;
        else
            doctor_details =doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Doctor1Activity.this,DoctorsActivity.class));

            }
        });
        arrlist=new ArrayList();
        for (int i=0;i<doctor_details.length;i++)
        {
            item= new HashMap<String,String>();
            item.put("line 1",doctor_details[i][0]);
            item.put("line 2",doctor_details[i][1]);
            item.put("line 3",doctor_details[i][2]);
            item.put("line 4",doctor_details[i][3]);
            item.put("line 5","Fees:"+doctor_details[i][4]+"$");
            arrlist.add(item);

        }
        adp =new SimpleAdapter(this,arrlist,
                R.layout.much_line,
                new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        ListView lst=findViewById(R.id.familydoctors);
        lst.setAdapter(adp);

       lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                       Intent it= new Intent(Doctor1Activity.this,AppointmentActivity.class);
                       it.putExtra("text1",title);
                       it.putExtra("text2",doctor_details[i][0]);
                       it.putExtra("text3",doctor_details[i][1]);
                       it.putExtra("text4",doctor_details[i][3]);
                       it.putExtra("text5",doctor_details[i][4]);
                        startActivity(it);


           }
       });


    }
}