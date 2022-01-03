package com.example.sms;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText t1,t2;
    String num1="1";
    String num2="2";
    String num3="3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{READ_SMS,SEND_SMS,RECEIVE_SMS,READ_PHONE_STATE},1);
        b1=findViewById(R.id.button);
        t1=findViewById(R.id.phone);
        t2=findViewById(R.id.name);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager mng = SmsManager.getDefault();
                String cur=t1.getText().toString();
                num1=num2;
                num2=num3;
                num3=cur;

                if(num1.equals(num2) && num2.equals(num3)){
                    Toast.makeText(getApplicationContext(), "Tried to send SMS more than twice to same number", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    mng.sendTextMessage(t1.getText().toString(), null, t2.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}