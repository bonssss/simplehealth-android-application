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

 public class BuyMedicineActivity extends AppCompatActivity {

    private  String[][] packages =
            {
                    { "Cabenuva","","","","60"},
                    {"Crestor","","","","6"},
                    {"Cyanocobalamin","","","","60"},
                    {"Cyclobenzaprine","","","","60"},
                    { "Cabenuva","","","","60"},
                    {"Crestor","","","","6"},
                    {"Cyanocobalamin","","","","60"},
                    {"Cyclobenzaprine","","","","60"},
                    {"Cyanocobalamin","","","","60"},



            };

    private String[] packagedetails ={
            "Cabenuva is a medication used for the treatment of HIV-1 infection in certain individuals. It is a combination product that contains two active ingredients:" +
                    " cabotegravir and rilpivirine.\n",

            "Crestor is primarily used to treat high " +
                    "levels of low-density lipoprotein (LDL) cholesterol, " +
                    "often referred to as \"bad\" cholesterol, and triglycerides in the blood. " +
                    "It can also help increase high-density lipoprotein (HDL) cholesterol, often" +
                    " referred to as \"good\" cholesterol.\n",

            "Building ang keeping the bones & teeth strength\n"+
                    "Building ang keeping the bones & teeth strength\n"+
                    "Building ang keeping the bones & teeth strength",
            "Building ang keeping the bones & teeth strength",
            "Building ang keeping the bones & teeth strength\n"+
                    "Building ang keeping the bones & teeth strength",
            "Building ang keeping the bones & teeth strength\n"+
                    "Building ang keeping the bones & teeth strength",
            "Building ang keeping the bones & teeth strength\n"+
                    "Building ang keeping the bones & teeth strength",
            "Building ang keeping the bones & teeth strength"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sadp;
    Button btnback,btntoorder;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        btnback = findViewById(R.id.medicinebackrohome);
        btntoorder = findViewById(R.id.medicineorder);
        lst = findViewById(R.id.medicinelistview);


        btntoorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, MedicineCart.class));

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));

            }
        });
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line 1", packages[i][0]);
            item.put("line 2", packages[i][1]);
            item.put("line 3", packages[i][2]);
            item.put("line 4", packages[i][3]);
            item.put("line 5", "Total Cost: " + packages[i][4] + "$");
            list.add(item);
        }
        sadp = new SimpleAdapter(this, list,
                R.layout.much_line,
                new String[]{"line 1", "line 2", "line 3", "line 4", "line 5"},
                new int[]{R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5});
        lst.setAdapter(sadp);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, MedicineDetails.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", packagedetails[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);
            }

            ;


        });
    }
}