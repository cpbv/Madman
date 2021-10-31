package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RetrieveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        final EditText empCodeText = findViewById(R.id.empCodeText4);
        final TextView nameTextView = findViewById(R.id.empCodeTextView5);
        final TextView genderTextView = findViewById(R.id.nameTextView5);
        final TextView departmentTextView = findViewById(R.id.genderTextView5);
        final TextView salaryTextView = findViewById(R.id.salaryTextView5);
        final Button fetchButton = findViewById(R.id.getButton);
        final EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empCode = empCodeText.getText().toString();

                if(TextUtils.isEmpty(empCode)){
                    //If the employee code hasn't been entered
                    Toast empCodeToast = Toast.makeText(RetrieveActivity.this, "Please enter the Employee Code.", Toast.LENGTH_SHORT);
                    empCodeToast.show();
                    return;
                }

                String[] results = employeeDatabaseHelper.retrieveRecord(empCode);

                if(results == null){
                    //Record does not exist
                    Toast missingRecordToast = Toast.makeText(RetrieveActivity.this, empCode + "'s record doesn't exist!", Toast.LENGTH_SHORT);
                    missingRecordToast.show();
                    return;
                }

                //Record exists, set the TextViews accordingly
                nameTextView.setText(results[0]);
                genderTextView.setText(results[1]);
                departmentTextView.setText(results[2]);
                salaryTextView.setText("$ " + results[3]);
            }
        });
    }
}