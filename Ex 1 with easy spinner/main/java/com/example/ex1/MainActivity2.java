package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TextView Name=findViewById(R.id.nameop);
        final TextView Age=findViewById(R.id.ageop);
        final TextView Dob=findViewById(R.id.dobop);
        final TextView Gender=findViewById(R.id.genderop);
        final TextView Marital=findViewById(R.id.maritalop);
        final TextView Contact=findViewById(R.id.contactop);
        final TextView Reg=findViewById(R.id.timeop);
        final TextView Addiction=findViewById(R.id.addictionop);

        SharedPreferences Status=getSharedPreferences("status",MODE_PRIVATE);
        Name.setText(Status.getString("name",null));
        Age.setText(Status.getString("age",null));
        Dob.setText(Status.getString("dob",null));
        Gender.setText(Status.getString("gender",null));
        Marital.setText(Status.getString("marital",null));
        Contact.setText(Status.getString("phone",null));
        Reg.setText(Status.getString("regtime",null));
        Addiction.setText(Status.getString("addiction",null));
    }
}