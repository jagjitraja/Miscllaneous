package com.example.t00533766.apptwot00533766;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText enteredTextField;
    private Button convertButton;
    private TextView resultLabel;
    private String enteredValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fromSpinner = (Spinner) findViewById(R.id.fromConversionTypeSpinner);
        toSpinner = (Spinner) findViewById(R.id.toConversionSpinner);
        enteredTextField = (EditText) findViewById(R.id.input);
        enteredTextField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        resultLabel = (TextView) findViewById(R.id.resultLabel);
        convertButton = (Button)findViewById(R.id.convertButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredValue = String.valueOf(enteredTextField.getText().toString());
                if(checkConversionNeeded()){

                    if(enteredValue.length()<1){
                        Toast.makeText(getApplicationContext(), R.string.noValueEntered,Toast.LENGTH_SHORT).show();
                        resultLabel.setText("Null!");
                    }
                    else {
                        convert();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),(R.string.noConversion),Toast.LENGTH_SHORT).show();
                    resultLabel.setText(String.valueOf(enteredValue)+".0 "+getString(R.string.deg));
                    return;
                }
            }
        });
    }

    private void convert() {

        double result = 0;
        double input = 0;
        if(fromSpinner.getSelectedItem().toString()== "Celcius" && toSpinner.getSelectedItem().toString()=="Farenheit"){

            if(enteredValue!=null){
                input = Integer.parseInt(enteredValue);
                result = (input-32)*5/9;
            }
        } else{
            if(enteredValue!=null){
                input = Integer.parseInt(enteredValue);
                result = (input*9/5)+32;
            }
        }
        resultLabel.setText(String.valueOf(result)+getString(R.string.deg));
    }

    private boolean checkConversionNeeded() {
        return !fromSpinner.getSelectedItem().equals(toSpinner.getSelectedItem());
    }
}
