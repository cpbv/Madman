package com.example.sdanother;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    final File sdcard = Environment.getExternalStorageDirectory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRead(View v) throws IOException {
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            EditText filename = (EditText) findViewById(R.id.filename);
            EditText filecontent = (EditText) findViewById(R.id.filecontent);
            FileReader reader = new FileReader(new File(sdcard, filename.getText().toString()));
            char[] buff = new char[1024];
            reader.read(buff);
            String text = new String(buff);
            filecontent.setText(text);
            Toast.makeText(this, "File Read", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
    }

    public void onClickWrite(View v) throws IOException {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            EditText filename = (EditText) findViewById(R.id.filename);
            EditText filecontent = (EditText) findViewById(R.id.filecontent);
            String c = filecontent.getText().toString();
            try {
                FileWriter writer = new FileWriter(new File(sdcard, filename.getText().toString()));
                writer.write(c);
                writer.close();
                Toast.makeText(getApplicationContext(), "Write complete!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }

}