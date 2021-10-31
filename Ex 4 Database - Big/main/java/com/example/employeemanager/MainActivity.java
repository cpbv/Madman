package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button createButton = findViewById(R.id.createButton);
        final Button insertButton = findViewById(R.id.insertButton);
        final Button updateButton = findViewById(R.id.updateButton);
        final Button deleteButton = findViewById(R.id.deleteButton);
        final Button retrieveButton = findViewById(R.id.retrieveButton);
        final Button retrieveByDeptButton = findViewById(R.id.retrieveByDeptButton);

        final SQLiteOpenHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SQLiteDatabase db = employeeDatabaseHelper.getReadableDatabase();
                    Toast toast = Toast.makeText(MainActivity.this, "Database created!", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (SQLiteException e){
                    Toast toast = Toast.makeText(MainActivity.this, "Database unavailable!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertIntent = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(insertIntent);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(updateIntent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteIntent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(deleteIntent);
            }
        });

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retrieveIntent = new Intent(getApplicationContext(), RetrieveActivity.class);
                startActivity(retrieveIntent);
            }
        });

        retrieveByDeptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retrieveByDeptIntent = new Intent(getApplicationContext(), RetrieveManyActivity.class);
                startActivity(retrieveByDeptIntent);
            }
        });
    }
}