package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final EditText empCodeText = findViewById(R.id.empCodeText);
        final EditText nameText = findViewById(R.id.nameText);
        final RadioButton maleRadioButton = findViewById(R.id.maleRadioButton3);
        final RadioButton femaleRadioButton = findViewById(R.id.femaleRadioButton3);
        final RadioButton otherRadioButton = findViewById(R.id.otherRadioButton3);
        final RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup3);
        final Spinner deptSpinner = findViewById(R.id.deptSpinner);
        final EditText salaryText = findViewById(R.id.salaryText);
        final Button addButton = findViewById(R.id.addButton);
        final EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eCode = empCodeText.getText().toString();
                String eName = nameText.getText().toString();
                int selectedRadioButtonID = genderRadioGroup.getCheckedRadioButtonId();
                String department = (String) deptSpinner.getSelectedItem();
                String salaryValue = salaryText.getText().toString();

                //Validity Checking
                if(TextUtils.isEmpty(eCode)){
                    //Empty employee code field
                    Toast eCodeToast = Toast.makeText(InsertActivity.this, "Employee Code cannot be empty!", Toast.LENGTH_SHORT);
                    eCodeToast.show();
                } else if(TextUtils.isEmpty(eName)){
                    //Empty name field
                    Toast nameToast = Toast.makeText(InsertActivity.this, "Name Field cannot be empty!", Toast.LENGTH_SHORT);
                    nameToast.show();
                } else if(TextUtils.isEmpty(salaryValue)){
                    //Empty salary field
                    Toast salaryToast = Toast.makeText(InsertActivity.this, "Salary must not be empty!", Toast.LENGTH_SHORT);
                    salaryToast.show();
                } else if(eCode.charAt(0) != 'E'){
                    //Wrong employee code format
                    empCodeText.setText("");
                    Toast eCodeToast = Toast.makeText(InsertActivity.this, "Employee Code must be of the format E1024.", Toast.LENGTH_SHORT);
                    eCodeToast.show();
                } else if(eName.matches(".*\\d.*")){
                    //Name contains numeric characters
                    nameText.setText("");
                    Toast eNameToast = Toast.makeText(InsertActivity.this, "Employee Name must contain only alphabets.", Toast.LENGTH_SHORT);
                    eNameToast.show();
                } else if(selectedRadioButtonID < 0){
                    //Gender was not selected
                    Toast genderToast = Toast.makeText(InsertActivity.this, "Please identify your gender.", Toast.LENGTH_SHORT);
                    genderToast.show();
                }
                else{
                    //All information valid, proceed to add to database

                    RadioButton selectedRadioButton = genderRadioGroup.findViewById(selectedRadioButtonID);
                    String gender = selectedRadioButton.getText().toString();

                    double salary = Double.parseDouble(salaryValue);

                    try{
                        employeeDatabaseHelper.addRecord(eCode, eName, gender, department, salary);
                        Toast insertSuccessToast = Toast.makeText(InsertActivity.this, eCode + "'s record was added!", Toast.LENGTH_SHORT);
                        insertSuccessToast.show();
                    } catch (SQLiteException e){
                        Toast insertFailureToast = Toast.makeText(InsertActivity.this, e.getMessage() , Toast.LENGTH_SHORT);
                        insertFailureToast.show();
                    }
                }
            }
        });
    }
}
