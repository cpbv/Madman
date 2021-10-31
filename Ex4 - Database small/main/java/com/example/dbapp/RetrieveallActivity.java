package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveallActivity extends AppCompatActivity {
    Button b;
    TextView t;
    SQLiteDatabase db;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieveall);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        b = findViewById(R.id.retrallbutton);
        t = findViewById(R.id.displayTV);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rs = db.rawQuery("SELECT * FROM EMPLOYEES;", null);
                rs.moveToFirst();
                String res="";
                try {
                    while(!rs.isAfterLast()) {
                        res += rs.getString(0) + " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\n";
                        rs.moveToNext();
                    }
                    } catch (Exception ex) {
                    res+="No such employee";
                    ex.printStackTrace();
                }
                t.setText(res);
            }
        });
    }
}

