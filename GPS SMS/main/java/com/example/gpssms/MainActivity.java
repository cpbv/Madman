package com.example.gpssms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView lat;
    TextView lon;
    Button gpsbutton;
    LocationManager locman;
    LocationListener lis;
    String contact,msg;

    @SuppressLint("MissingPermission")
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
                sendSMSMessage();
            }

            @Override
            public void onStatusChanged(String s,int i,Bundle bundle){

            }
        };
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        gpsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locman.requestLocationUpdates("gps",5000,1,lis);
            }
        });

    }

    protected void sendSMSMessage() {
        contact = "5556";
        msg = lat.getText().toString() + " "+ lon.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(contact, null, msg, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}