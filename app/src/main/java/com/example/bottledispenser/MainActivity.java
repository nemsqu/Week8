package com.example.bottledispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Context context = null;
    BottleDispenser dispenser = null;
    SeekBar seekBar;
    TextView progressText;
    int progressValue;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        textView = (TextView) findViewById(R.id.textView);
        dispenser = BottleDispenser.getInstance();
        spinner = (Spinner) findViewById(R.id.spinner);
        seekBar();

        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(context, android.R.layout.simple_spinner_item, dispenser.listBottles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @SuppressLint("DefaultLocale")
    public void seekBar(){
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressText = (TextView) findViewById(R.id.textViewProgress);
        progressText.setText(String.format("Money to add: %.2f€", ((float)seekBar.getProgress()/100)));
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        progressText.setText(String.format("Money to add: %.2f€", ((float)progress/100)));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar){
                        progressText.setText(String.format("Money to add: %.2f€", ((float)progressValue/100)));
                    }
                }
        );
    }

    public void addMoney(View v){
        textView.setText(dispenser.addMoney(progressValue));
        seekBar.setProgress(0);
    }


    public void buyBottle(View v){
        float money = dispenser.getMoney();
        Bottle choiceBottle = (Bottle) spinner.getSelectedItem();
        textView.setText(dispenser.buyBottle(choiceBottle));
        if(money >= choiceBottle.getPrice()) {
            try {
                OutputStreamWriter streamOut = new OutputStreamWriter(context.openFileOutput("receipt.txt", Context.MODE_PRIVATE));
                String s = "RECEIPT \nProduct: " + choiceBottle.getName() + ", " + choiceBottle.getEnergy() + "\nPrice: " + choiceBottle.getPrice() + "€";
                streamOut.write(s);
                streamOut.close();

            } catch (IOException e) {
                Log.e("IoException", "Error");
            }
            System.out.println(this.getFilesDir());
        }
    }

    public void moneyOut(View v){
        textView.setText(dispenser.returnMoney());
    }
}
