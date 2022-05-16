package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        display.setText(String.format("%s%s%s", strLeft, Integer.toString(value),strRight));
        display.setSelection(cursorPosition+1);
    }

    public void onClickDel(View view) {
        int cursorPosition = display.getSelectionStart();
        String strPrev = display.getText().toString();
        if (strPrev.length() != 0) {
            String strLeft = strPrev.substring(0, cursorPosition-1);
            String strRight = strPrev.substring(cursorPosition);
            display.setText(String.format("%s%s", strLeft, strRight));
            display.setSelection(cursorPosition-1);
        }
    }
}