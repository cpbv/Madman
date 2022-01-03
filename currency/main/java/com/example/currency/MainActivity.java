package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner froms=findViewById(R.id.spinner1);
        Spinner tos=findViewById(R.id.spinner2);
        TextView tv=findViewById(R.id.tv);
        EditText et=findViewById(R.id.amtet);
        Button b=findViewById(R.id.button);




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fs=froms.getSelectedItem().toString();
                String ts=tos.getSelectedItem().toString();
                String a=et.getText().toString();
                float f=Float.parseFloat(a);
                float t;
                if(fs.equals(ts))
                    t=f;
                else if(fs.equals("INR") && ts.equals("USD"))
                    t=f/100;
                else if(fs.equals("USD") && ts.equals("INR"))
                    t=f*100;
                else if(fs.equals("RIAL") && ts.equals("USD"))
                    t=f/50;
                else if(fs.equals("USD") && ts.equals("RIAL"))
                    t=f*50;
                else if(fs.equals("RIAL") && ts.equals("INR"))
                    t=f/2;
                else if(fs.equals("INR") && ts.equals("RIAL"))
                    t=f*2;
                else
                    t=0;

                String s = Float.toString(t);
                tv.setText(s);
            }
        });


    }
}