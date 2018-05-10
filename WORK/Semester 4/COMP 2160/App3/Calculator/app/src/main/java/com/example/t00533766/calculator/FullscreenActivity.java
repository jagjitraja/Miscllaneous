package com.example.t00533766.calculator;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FullscreenActivity extends AppCompatActivity {

    private String displayedText;
    private String currentOperand;
    private CHARS previousInput;
    private double MEMORY_VALUE = 0;

    private EditText guiDisplayText;
    private TextView resultValue;

    private enum CHARS {NUMBER, ADD, SUBTRACT, DIVIDE, MULTIPLY}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        resultValue = (TextView) findViewById(R.id.resultValue);
        guiDisplayText = (EditText) findViewById(R.id.numbersText);
        guiDisplayText.setOnClickListener(null);
        guiDisplayText.setFocusable(false);

        displayedText = "";
        currentOperand = "";

        loadButtonsAndLayouts();

    }

    private void loadButtonsAndLayouts() {
        GridLayout functionButtonGrid = (GridLayout) findViewById(R.id.function_button_layout);
        for (int i = 0; i < functionButtonGrid.getChildCount(); i++) {
            Button button = (Button) functionButtonGrid.getChildAt(i);
            button.setOnClickListener(functionButtonClickListener);
        }

        GridLayout numberGrid = (GridLayout) findViewById(R.id.number_layout);
        for (int i = 0; i < numberGrid.getChildCount(); i++) {
            Button number = (Button) (numberGrid.getChildAt(i));
            number.setOnClickListener(numberButtonClickListener);
        }

        LinearLayout memoryButtonlayout = (LinearLayout) findViewById(R.id.memory_buttons);
        for (int i = 0; i < memoryButtonlayout.getChildCount(); i++) {
            Button button = (Button) (memoryButtonlayout.getChildAt(i));
            button.setOnClickListener(memoryButtonClickListener);
        }
    }


    private View.OnClickListener numberButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Button pressed = (Button) view;
            if(pressed.getId()==R.id.button_dot && currentOperand.contains(".")){
                toast(getString(R.string.only_one_dp));
                return;
            }

            if (checkInput()) {

                for(int i = 0;i<displayedText.length();i++){
                    if(displayedText.charAt(i)!='0'){
                        displayedText = displayedText.substring(i,displayedText.length());
                        break;
                    }
                }


                displayedText+=(String.valueOf(pressed.getText()));
                currentOperand+=(String.valueOf(pressed.getText()));
                guiDisplayText.setText(displayedText);
                previousInput = CHARS.NUMBER;
            }
        }
    };

    private boolean checkInput() {
        String decimalPart;
        if (currentOperand.contains(".")) {
            decimalPart = currentOperand.substring(currentOperand.indexOf(".") + 1);
            if (decimalPart.length() >= 4) {
                toast(getString(R.string.maximum_decimals_error));
                return false;
            }
        }
        if (currentOperand.length()>=10){
            toast(getString(R.string.max_reached));
            return false;
        }
        return true;
    }

    private View.OnClickListener memoryButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(displayedText.length()==0){
                return;
            }
            switch (view.getId()){

                case R.id.memory_recall:
                    try {
                        MEMORY_VALUE = Double.parseDouble(resultValue.getText().toString());
                    }catch (Exception e){
                        log(e.getMessage());
                        MEMORY_VALUE = 0;
                    }
                    toast(MEMORY_VALUE+getString(R.string.memory_saved));
                    break;
                case R.id.memory_add:

                    displayedText = displayedText+"+"+MEMORY_VALUE;
                    guiDisplayText.setText(displayedText);
                    evaluate();
                    break;
                case R.id.memory_subtract:
                    displayedText = displayedText+"-"+MEMORY_VALUE;
                    guiDisplayText.setText(displayedText);
                    evaluate();
                    break;
                case R.id.memory_clear:
                    MEMORY_VALUE = 0;
                    toast(getString(R.string.memory_cleared));
                    break;

            }

        }
    };

    private View.OnClickListener functionButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.button_add:
                    previousInput = CHARS.ADD;
                    evaluate();
                    currentOperand = "";
                    displayedText += getString(R.string.add);
                    break;

                case R.id.button_subtract:
                    previousInput = CHARS.SUBTRACT;
                    evaluate();
                    displayedText += getString(R.string.subtract);
                    currentOperand = "";
                    break;

                case R.id.button_multiply:
                    if (previousInput ==CHARS.NUMBER) {
                        previousInput = CHARS.MULTIPLY;
                        evaluate();
                        displayedText += getString(R.string.multiply);
                        currentOperand = "";
                    }
                    break;

                case R.id.button_divide:
                    if (previousInput == CHARS.NUMBER) {
                        previousInput = CHARS.DIVIDE;
                        evaluate();
                        displayedText += getString(R.string.divide);
                        currentOperand = "";
                    }
                    break;

                case R.id.delete_button:
                    if (displayedText.length() >= 1) {
                        //update screen text and assign operand one the new value
                        displayedText = displayedText.substring(0, displayedText.length() - 1);
                    }
                    break;

                case R.id.clear_button:
                    displayedText = "";
                    currentOperand = "";
                    previousInput = null;
                    resultValue.setText(R.string._0);
                    toast(getString(R.string.cleared));
                    log(displayedText+"   "+currentOperand+"    "+ previousInput+"    "+resultValue.getText());
                    break;

                case R.id.button_equal:
                    if(previousInput!=CHARS.NUMBER&&displayedText.length()>0){
                        displayedText = displayedText.substring(0,displayedText.length()-1);
                    }
                    if(displayedText.length()>0)
                        evaluate();
                    break;

                default:
                    break;
            }
            guiDisplayText.setText(displayedText);
        }
    };

    private void evaluate() {
        Evaluator e = new Evaluator();
        double r = e.calculate(displayedText);
        String result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DecimalFormat decimalFormat = new DecimalFormat(getString(R.string.decimal_places));
            result = decimalFormat.format(r);
        }
        else{
           result =String.valueOf(r);
        }
        log(result +"   of    "+ displayedText);

        resultValue.setText(result);
    }


    public static void log(String text){
        Log.d("CALCULATOR",text);
    }
    public void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
