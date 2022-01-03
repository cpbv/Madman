package com.example.gpssd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView lat;
    TextView lon,readtv;
    Button gpsbutton,readbtn;
    LocationManager locman;
    LocationListener lis;
    String textmsg;

    final File sdcard = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat = (TextView) findViewById(R.id.latTV);
        lon = (TextView) findViewById(R.id.lonTV);
        gpsbutton = (Button) findViewById(R.id.gclbutton);
        readbtn=findViewById(R.id.readbtn);
        readtv=findViewById(R.id.SDTV);
        locman=(LocationManager) getSystemService(LOCATION_SERVICE);

        lis=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat.setText(""+String.format("%.2f",location.getLatitude()));
                lon.setText(""+String.format("%.2f",location.getLongitude()));
                textmsg="Latitide: "+String.format("%.2f",location.getLatitude())+" Longitude: "+String.format("%.2f",location.getLongitude());


                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                    EditText filename = (EditText) findViewById(R.id.filename);


                    try {
                        Log.i("path", sdcard.getAbsolutePath());
                        FileWriter writer = new FileWriter("/sdcard" + "/" + filename.getText().toString(), false);
                        writer.write(textmsg);
                        writer.close();
                        Toast.makeText(getApplicationContext(), "Write complete!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                }
            }
            @Override
            public void onStatusChanged(String s,int i,Bundle bundle){

            }
        };

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);

        gpsbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                locman.requestLocationUpdates("gps",5000,1,lis);
            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                     EditText filename = (EditText) findViewById(R.id.filename);

                    String pathtoread = sdcard.getAbsolutePath() + "/" + filename.getText().toString();
                    FileReader reader=null;
                try {
                    reader = new FileReader(pathtoread);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                char[] buff = new char[1024];
                try {
                    reader.read(buff);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String text = new String(buff);
                    readtv.setText(text);
                Toast.makeText(getApplicationContext(), "File Read", Toast.LENGTH_SHORT).show();

            }
        });
    }
}