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

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final EditText empCodeText = findViewById(R.id.empCodeText3);
        final EditText nameText = findViewById(R.id.nameText3);
        final RadioButton maleRadioButton = findViewById(R.id.maleRadioButton3);
        final RadioButton femaleRadioButton = findViewById(R.id.femaleRadioButton3);
        final RadioButton otherRadioButton = findViewById(R.id.otherRadioButton3);
        final RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup3);
        final Spinner deptSpinner = findViewById(R.id.deptSpinner3);
        final EditText salaryText = findViewById(R.id.salaryText3);
        final Button changeButton = findViewById(R.id.changeButton);
        final EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empCode = empCodeText.getText().toString();
                String empName = nameText.getText().toString();
                int selectedRadioButtonID = genderRadioGroup.getCheckedRadioButtonId();
                String department = (String) deptSpinner.getSelectedItem();
                String salaryValue = salaryText.getText().toString();

                if(TextUtils.isEmpty(empCode)){
                    //Employee code not entered
                    Toast empCodeToast = Toast.makeText(UpdateActivity.this, "Please enter an employee code.", Toast.LENGTH_SHORT);
                    empCodeToast.show();
                } else if(TextUtils.isEmpty(empName)){
                    //Employee name not entered
                    Toast empNameToast = Toast.makeText(UpdateActivity.this, "Please enter the employee name.", Toast.LENGTH_SHORT);
                    empNameToast.show();
                } else if(TextUtils.isEmpty(salaryValue)){
                    //Empty salary field
                    Toast salaryToast = Toast.makeText(UpdateActivity.this, "Salary must not be empty!", Toast.LENGTH_SHORT);
                    salaryToast.show();
                } else if(empCode.charAt(0) != 'E'){
                    //Wrong employee code format
                    empCodeText.setText("");
                    Toast eCodeToast = Toast.makeText(UpdateActivity.this, "Employee Code must be of the format E1024.", Toast.LENGTH_SHORT);
                    eCodeToast.show();
                } else if(empName.matches(".*\\d.*")){
                    //Employee name contains numeric characters
                    nameText.setText("");
                    Toast eNameToast = Toast.makeText(UpdateActivity.this, "Employee Name must contain only alphabets.", Toast.LENGTH_SHORT);
                    eNameToast.show();
                } else if(selectedRadioButtonID < 0){
                    //Gender was not selected
                    Toast genderToast = Toast.makeText(UpdateActivity.this, "Please identify your gender.", Toast.LENGTH_SHORT);
                    genderToast.show();
                } else{
                    //All information valid, proceed to update table record

                    RadioButton selectedRadioButton = genderRadioGroup.findViewById(selectedRadioButtonID);
                    String gender = selectedRadioButton.getText().toString();

                    double salary = Double.parseDouble(salaryValue);

                    try{
                        //Check if an employee with the given Employee Code exists
                        String results[] = employeeDatabaseHelper.retrieveRecord(empCode);

                        if(results == null){
                            //Employee doesn't exist under the specified employee code
                            Toast empNotFoundToast = Toast.makeText(UpdateActivity.this, "Employee with code: " + empCode + " doesn't exist!", Toast.LENGTH_SHORT);
                            empNotFoundToast.show();
                        } else{
                            //Employee exists, update the record
                            employeeDatabaseHelper.updateRecord(empCode, empName, gender, department, salary);
                            Toast updateSuccessToast = Toast.makeText(UpdateActivity.this, empCode + "'s record was updated!", Toast.LENGTH_SHORT);
                            updateSuccessToast.show();
                        }

                    } catch (SQLiteException e){
                        Toast updateFailureToast = Toast.makeText(UpdateActivity.this, e.getMessage() , Toast.LENGTH_SHORT);
                        updateFailureToast.show();
                    }
                }

            }
        });
    }
}