package com.example.gpssms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView lat;
    TextView lon;
    Button gpsbutton;
    LocationManager locman;
    LocationListener lis;
    String textmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat = (TextView) findViewById(R.id.latTV);
        lon = (TextView) findViewById(R.id.lonTV);
        gpsbutton = (Button) findViewById(R.id.gclbutton);

        locman=(LocationManager) getSystemService(LOCATION_SERVICE);
        lis=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat.setText(""+String.format("%.2f",location.getLatitude()));
                lon.setText(""+String.format("%.2f",location.getLongitude()));
                textmsg="Latitide: "+String.format("%.2f",location.getLatitude())+" Longitude: "+String.format("%.2f",location.getLongitude());
                SmsManager mng = SmsManager.getDefault();
                mng.sendTextMessage("5554",null,textmsg,null,null);
                Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();


            }
            @Override
            public void onStatusChanged(String s,int i,Bundle bundle){

            }
        };

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_SMS,Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.READ_PHONE_STATE},1);

        gpsbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                locman.requestLocationUpdates("gps",5000,1,lis);
            }
        });

    }
}