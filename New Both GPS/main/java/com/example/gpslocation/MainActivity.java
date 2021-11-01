package com.example.gpslocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    TextView lat;
    TextView lon;
    EditText locinput;
    Button gpsbutton,locbutton;
    LocationManager locman;
    LocationListener lis,lis1;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat = (TextView) findViewById(R.id.latTV);
        lon = (TextView) findViewById(R.id.lonTV);
        gpsbutton = (Button) findViewById(R.id.gclbutton);
        locbutton=(Button)findViewById(R.id.gilbutton);
        locinput=(EditText)findViewById(R.id.locationET);

        locman=(LocationManager) getSystemService(LOCATION_SERVICE);
        lis=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat.setText(""+String.format("%.2f",location.getLatitude()));
                lon.setText(""+String.format("%.2f",location.getLongitude()));
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

        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    List<Address> list = geocoder.getFromLocationName(locinput.getEditableText().toString(), 1 );
                    if ( list != null && list.size() > 0 ) {
                        Address adr = list.get(0);
                        lat.setText(""+String.format("%.2f",adr.getLatitude()));
                        lon.setText(""+String.format("%.2f",adr.getLongitude()));

                    } else {
                        lat.setText("not found");
                        lon.setText("not found");
                    }
                }
                catch ( Exception e ){
                    e.printStackTrace();
                }
            }
        });
    }
}
