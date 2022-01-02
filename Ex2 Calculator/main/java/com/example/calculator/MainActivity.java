package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;
    String workings="";
    String formula = "";
    String tempFormula = "";
    int brackets_flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();

    }

    private void initTextViews() {
        workingsTV=findViewById(R.id.textView);
        resultsTV=findViewById(R.id.textView1);
    }

    public void clearOnClick(View view) {
        resultsTV.setText("");
        workingsTV.setText("");
        workings="";
        brackets_flag=1;
    }

    public void onclickNumber0(View view) {
        setWorkings("0");
    }

    public void onclickDot(View view) {
        setWorkings(".");
    }

    public void onclickBracket(View view) {
    if(brackets_flag==1)setWorkings("(");
    else setWorkings(")");
    brackets_flag*=-1;
    }

    private void setWorkings(String s) {
        workings = workings + s;
        workingsTV.setText(workings);
    }

    public void onclickPower(View view) {
        setWorkings("^");
    }

    public void onclickDiv(View view) {
        setWorkings("/");
    }

    public void onclickMul(View view) {
        setWorkings("*");
    }

    public void onclickSub(View view) {
        setWorkings("-");
    }

    public void onclickAdd(View view) {
        setWorkings("+");
    }

    public void onclickEqual(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();
        try {
            result = (double) engine.eval(formula);

        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if(result != null) {
            resultsTV.setText(String.format("%.2f", result));
            workingsTV.setText(String.format("%.2f", result));
            workings = String.valueOf(result.doubleValue());
        }
    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++)
        {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }

    public void onclickNumber2(View view) {
        setWorkings("2");
    }

    public void onclickNumber3(View view) {
        setWorkings("3");
    }

    public void onclickNumber1(View view) {
        setWorkings("1");
    }

    public void onclickNumber4(View view) {
        setWorkings("4");
    }

    public void onclickNumber7(View view) {
        setWorkings("7");
    }

    public void onclickNumber8(View view) {
        setWorkings("8");
    }

    public void onclickNumber9(View view) {
        setWorkings("9");
    }

    public void onclickNumber5(View view) {
        setWorkings("5");
    }

    public void onclickNumber6(View view) {
        setWorkings("6");
    }
}
