package com.example.multithreading;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sleepbtn=findViewById(R.id.sleepBtn);
        Button startbtn=findViewById(R.id.startBtn);
        sleepbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setTitle("In Progress");
                pd.setMessage("Started sleeping");
                pd.show();
                EditText editText = findViewById(R.id.inputTime);
                int count = Integer.parseInt(editText.getText().toString());
                //Handler class can be used to ask the framework to run some code later on the same thread
                //Runnable is often used to provide the code that a thread should run
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                }, count*1000);
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar = findViewById(R.id.progressBar);
                EditText editText = findViewById(R.id.inputTime);
                int count = Integer.parseInt(editText.getText().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for(i=0; i<=100; i+=10){
                            progressBar.setProgress(i);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        progressBar.setProgress(100);
                        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
                    }
                }).start();
            }
        });
    }
}