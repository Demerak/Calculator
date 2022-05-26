package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engine = new ScriptEngineManager().getEngineByName("rhino");

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    public void onClickValueBTN(View view) {
        Button b = (Button)view;
        int value = Integer.parseInt(b.getText().toString());
        int cursorPosition = display.getSelectionStart();
        String strPrev = display.getText().toString();
        String strLeft = strPrev.substring(0, cursorPosition);
        String strRight = strPrev.substring(cursorPosition);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(Integer.toString(value));
        } else {
            display.setText(String.format("%s%s%s", strLeft, Integer.toString(value),strRight));
        }
        display.setSelection(cursorPosition+1);
    }

    public void onClickDel(View view) {
        int cursorPosition = display.getSelectionStart();
        String strPrev = display.getText().toString();
        if (strPrev.length() != 0 & cursorPosition != 0) {
            String strLeft = strPrev.substring(0, cursorPosition-1);
            String strRight = strPrev.substring(cursorPosition);
            display.setText(String.format("%s%s", strLeft, strRight));
            display.setSelection(cursorPosition-1);
        }
    }

    public void onClickAC(View view) {
        display.setText("");
    }

    public void onClickOperation(View view) {
        Button b = (Button)view;
        String op = b.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String strPrev = display.getText().toString();
        String strLeft = strPrev.substring(0, cursorPosition);
        String strRight = strPrev.substring(cursorPosition);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(op);
        } else {
            display.setText(String.format("%s%s%s", strLeft,op,strRight));
        }
        display.setSelection(cursorPosition+1);
    }

    public void onClickEval(View view) {
        try {
            String onScreen = display.getText().toString();
            onScreen = onScreen.replace("×", "*");
            onScreen = onScreen.replace("÷", "/");
            BigDecimal decimal = new BigDecimal(engine.eval(onScreen).toString());
            String result = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
            display.setText(result);
        } catch (ScriptException e) {
            display.setText("Error");
        }

    }

}