package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RetrieveManyActivity extends AppCompatActivity {

    public void enableButton(ImageButton button, boolean enabled){
        //To enable and disable the scroll buttons
        button.setEnabled(enabled);

        //Change background color of the button
        if(!enabled){
            //dark gray color
            button.setBackgroundTintList(this.getResources().getColorStateList(R.color.darker_gray, this.getTheme()));
        } else{
            //holo red light color
            button.setBackgroundTintList(this.getResources().getColorStateList(R.color.holo_red_light, this.getTheme()));
        }
    }

    Cursor results = null;  //To store the retrieved records

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_many);

        final Spinner deptSpinner = findViewById(R.id.deptSpinner5);
        final TextView empCodeTextView = findViewById(R.id.empCodeTextView5);
        final TextView nameTextView = findViewById(R.id.nameTextView5);
        final TextView genderTextView = findViewById(R.id.genderTextView5);
        final TextView salaryTextView = findViewById(R.id.salaryTextView5);
        final Button getButton = findViewById(R.id.getButton);
        final ImageButton prevButton = findViewById(R.id.prevButton);
        final ImageButton nextButton = findViewById(R.id.nextButton);
        final EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        //Disable scroll behavior on initial load
        enableButton(prevButton, false);
        enableButton(nextButton, false);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Disable scroll behavior by default
                enableButton(prevButton, false);
                enableButton(nextButton, false);

                String department = (String) deptSpinner.getSelectedItem();

                results = employeeDatabaseHelper.retrieveByDeptRecords(department);

                if(results == null){
                    //No records were retrieved
                    Toast noRecordsToast = Toast.makeText(RetrieveManyActivity.this, "There are no employees in the " + department + " department.", Toast.LENGTH_SHORT);
                    noRecordsToast.show();

                    //Clear the TextViews
                    empCodeTextView.setText("");
                    nameTextView.setText("");
                    genderTextView.setText("");
                    salaryTextView.setText("");

                    return;
                }

                //Records were retrieved, display the first one by default
                results.moveToFirst();

                empCodeTextView.setText(results.getString(0));
                nameTextView.setText(results.getString(1));
                genderTextView.setText(results.getString(2));
                salaryTextView.setText("$ " + results.getString(3));

                if(results.getCount() > 1){
                    //More than one record was retrieved
                    enableButton(nextButton, true);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButton(prevButton, true);     //enable prevButton to move backwards
                results.moveToNext();   //move by 1 position forward

                empCodeTextView.setText(results.getString(0));
                nameTextView.setText(results.getString(1));
                genderTextView.setText(results.getString(2));
                salaryTextView.setText("$ " + results.getString(3));

                if(results.isLast()){
                    enableButton(nextButton, false);
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButton(nextButton, true);     //enable nextButton to move forwards
                results.moveToPrevious();   //move by 1 position backward

                empCodeTextView.setText(results.getString(0));
                nameTextView.setText(results.getString(1));
                genderTextView.setText(results.getString(2));
                salaryTextView.setText("$ " + results.getString(3));

                if(results.isFirst()){
                    enableButton(prevButton, false);
                }
            }
        });
    }
}
