package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView dtv=findViewById(R.id.dtv);
        TextView ytv=findViewById(R.id.ytv);
        TextView mtv=findViewById(R.id.mtv);


        SharedPreferences Status=getSharedPreferences("status",MODE_PRIVATE);
        dtv.setText(Status.getString("day",null));
        mtv.setText(Status.getString("month",null));
        ytv.setText(Status.getString("year",null));

    }
}