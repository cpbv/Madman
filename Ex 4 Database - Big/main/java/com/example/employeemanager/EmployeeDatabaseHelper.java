package com.example.employeemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class EmployeeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "employees";
    private static final int DB_VERSION = 1;

    public EmployeeDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS EMPLOYEES (" +
                "ECODE TEXT PRIMARY KEY, " +
                "NAME TEXT, "+
                "GENDER TEXT, " +
                "DEPARTMENT TEXT, " +
                "SALARY NUMERIC );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS EMPLOYEES;");
        onCreate(db);
    }

    public void addRecord(String eCode, String name, String gender, String department, double salary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues record = new ContentValues();

        record.put("ECODE", eCode);
        record.put("NAME", name);
        record.put("GENDER", gender);
        record.put("DEPARTMENT", department);
        record.put("SALARY", salary);


        //throws SQLiteException if the insertion was unsuccessful
        db.insertOrThrow("EMPLOYEES", null, record);

        db.close();
    }

    public String[] retrieveRecord(String eCode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("EMPLOYEES", new String[] {"NAME", "GENDER", "DEPARTMENT", "SALARY"}, "ECODE = ?", new String[] {eCode}, null, null, null);
        String results[] = new String[4];

        if(cursor.moveToNext()){
            //If record exists, retrieve it into results array and return
            results[0] = cursor.getString(0);
            results[1] = cursor.getString(1);
            results[2] = cursor.getString(2);
            results[3] = Double.toString(cursor.getDouble(3));

            return results;
        } else{
            //Record doesn't exist, return null
            return null;
        }
    }

    public void updateRecord(String eCode, String name, String gender, String department, double salary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedRecord = new ContentValues();

        updatedRecord.put("NAME", name);
        updatedRecord.put("GENDER", gender);
        updatedRecord.put("DEPARTMENT", department);
        updatedRecord.put("SALARY", salary);

        db.update("EMPLOYEES", updatedRecord, "ECODE = ?", new String[] {eCode});
    }

    public int deleteRecord(String eCode){
        SQLiteDatabase db = this.getWritableDatabase();

        //Returns the number of rows affected
        return db.delete("EMPLOYEES", "ECODE = ?", new String[] {eCode});
    }

    public Cursor retrieveByDeptRecords(String department){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("EMPLOYEES", new String[] {"ECODE", "NAME", "GENDER", "SALARY"}, "DEPARTMENT = ?", new String[] {department}, null, null, null);

        if(cursor.getCount() == 0){
            //No records were retrieved
            return null;
        }

        return cursor;
    }
}