package com.example.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step 1
        final EditText name=findViewById(R.id.name_input);
        final EditText age=findViewById(R.id.age_input);
        final EditText dob=findViewById(R.id.dob_input);
        final RadioGroup gender=findViewById(R.id.gender);
        final Spinner marital=(Spinner)findViewById(R.id.spinner);
        final EditText phone=findViewById(R.id.phone_input);
        final EditText regtime=findViewById(R.id.time_input);
        final CheckBox alcohol=findViewById(R.id.checkBoxAlcohol);
        final CheckBox smoking=findViewById(R.id.checkBoxAlcohol2);
        final CheckBox others=findViewById(R.id.checkBoxOthers);
        Button submit=findViewById(R.id.button1);
        Button reset=findViewById(R.id.button2);

        //step 2: Spinner add implements above

        //step 4 :reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                age.setText("");
                dob.setText("");
                phone.setText("");
                regtime.setText("");
                gender.clearCheck();
                alcohol.setChecked(false);
                smoking.setChecked(false);
                others.setChecked(false);
                marital.setSelection(0);
            }
        });

        //Step 5:Submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences Status=getSharedPreferences("status",MODE_PRIVATE);
                SharedPreferences.Editor EStatus=Status.edit();
                EStatus.putString("name",name.getEditableText().toString());
                EStatus.apply();
                EStatus.putString("age",age.getEditableText().toString());
                EStatus.apply();
                EStatus.putString("dob",dob.getEditableText().toString());
                EStatus.apply();
                EStatus.putString("phone",phone.getEditableText().toString());
                EStatus.apply();
                EStatus.putString("regtime",regtime.getEditableText().toString());
                EStatus.apply();
                EStatus.putString("gender",((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString());
                EStatus.apply();
                EStatus.putString("marital",marital.getSelectedItem().toString());
                EStatus.apply();
                String addiction="";
                if(alcohol.isChecked())addiction+="Alcohol ";
                if(smoking.isChecked())addiction+="Smoking ";
                if(others.isChecked())addiction+="Others ";
                EStatus.putString("addiction",addiction);
                EStatus.apply();
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });
    }


    //Step 3 automatically comes to implemements methods
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    //selected action
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}