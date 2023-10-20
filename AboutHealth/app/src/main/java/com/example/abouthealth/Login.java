package com.example.abouthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnlogin ;
    TextView textView3;
    EditText user,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin= findViewById(R.id.buttonlogin);
        textView3 =findViewById(R.id.textView3);
        user =findViewById(R.id.username);
        pwd =findViewById(R.id.password);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String username= user.getText().toString();
                String password = pwd.getText().toString();
                Database db = new Database(getApplicationContext(),"health",null,1);


                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill username and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (db.Login(username,password)==1){
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("username",username);
                      editor.apply();
                        startActivity(new Intent(Login.this,HomeActivity.class));

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,RegisterActivity.class));
            }
        });
    }

}