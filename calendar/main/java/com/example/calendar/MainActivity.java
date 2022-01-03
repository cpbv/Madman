package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView cv=findViewById(R.id.calendarView);
        Button b=findViewById(R.id.button);
        TextView tv=findViewById(R.id.datetv);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date=day+":"+(month+1)+":"+year;
                tv.setText(date);
                SharedPreferences Status=getSharedPreferences("status",MODE_PRIVATE);
                SharedPreferences.Editor EStatus=Status.edit();
                EStatus.putString("day",Integer.toString(day));
                EStatus.apply();
                EStatus.putString("month",Integer.toString(month+1));
                EStatus.apply();
                EStatus.putString("year",Integer.toString(year));
                EStatus.apply();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });
    }
}