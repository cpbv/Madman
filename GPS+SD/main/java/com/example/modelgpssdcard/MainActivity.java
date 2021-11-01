package com.example.modelgpssdcard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    EditText fileName,content;
    Button gpsBtn,rdBtn;
    LocationManager loc;
    LocationListener lis;
    String lati,longi;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName= findViewById(R.id.nameInput);
        gpsBtn = findViewById(R.id.gpsButton);
        rdBtn = findViewById(R.id.readBtn);
        content=findViewById(R.id.fileContentTV);

        final File sdcard= Environment.getExternalStorageDirectory();
        //File file = new File(getFilesDir(),fileName.getEditableText().toString());
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION},1);
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        loc=(LocationManager) getSystemService(LOCATION_SERVICE);
        lis=new LocationListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lati = String.format("%.2f",location.getLatitude());
                longi = String.format("%.2f",location.getLongitude());
                String path = sdcard.getAbsolutePath()+"/"+fileName.getText().toString();
                String content = lati+"/"+longi;
                writerFunc(path,content);
            }

            @Override
            public void onStatusChanged(String s,int i,Bundle bundle){

            }
        };

        gpsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                loc.requestLocationUpdates("gps",5000,1,lis);
            }
        });

        rdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path= sdcard.getAbsolutePath()+"/"+fileName.getText().toString();
                readerFunc(path);
            }
        });

    }

    void writerFunc(String path, String content)
    {
        try {
            FileWriter fw = new FileWriter(path,false);
            fw.write(content);
            fw.close();

            Toast.makeText(this,"FILE HAS BEEN WRITTEN!!",Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            Toast.makeText(this,"ERROR!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    void readerFunc(String path)
    {
        try {
            FileReader fw = new FileReader(path);
            char[] buff=new char[1024];
            fw.read(buff);
            String text = new String(buff);
            fw.close();
            content.setText(text);
            Toast.makeText(this,"FILE HAS BEEN READ!!",Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            Toast.makeText(this,"ERROR!!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}