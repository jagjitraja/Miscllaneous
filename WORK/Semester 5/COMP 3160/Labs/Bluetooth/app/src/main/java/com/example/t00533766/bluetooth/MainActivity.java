package com.example.t00533766.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    final int REQUEST_DISCOVERABLE_CODE = 12346;
    Button button;
    TextView textView;
    BluetoothAdapter bluetoothAdapter;
    UUID uuid = UUID.fromString("e2870c62-27f2-11e8-b467-0ed5f89f718b");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DISCOVERABLE_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Please Enable Bluetooth", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "ENABLED", Toast.LENGTH_SHORT).show();
                button.setText("ENABLED");
            }
        }
    }

    private void startCommunicating(final BluetoothDevice o) {

        if (o==null){
            return;
        }
        Log.d(TAG, "startCommunicating: "+ o.getName());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    BluetoothSocket socket = o.createRfcommSocketToServiceRecord(uuid);
                    socket.connect();
                    bluetoothAdapter.cancelDiscovery();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                        printWriter.println("Hi");
                        printWriter.flush();

                        String input = bufferedReader.readLine();
                        Log.d(TAG, "run/**********************************------: " + input);

                    socket.close();
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
    }

    public void enableBluetooth(View view) {


        if (bluetoothAdapter.isEnabled()) {
            String deviceName = bluetoothAdapter.getName();
            String macAddress = bluetoothAdapter.getAddress();

            Intent beDiscoverable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            beDiscoverable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
            startActivityForResult(beDiscoverable, REQUEST_DISCOVERABLE_CODE);

            Log.d(TAG, "onCreate: " + deviceName + "    " + macAddress);

        } else {
            Toast.makeText(getApplicationContext(), "Please Enable Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    public void connect(View view) {

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        BluetoothDevice deviceSelected=null;
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d(TAG, "onActivityResult: "+deviceName+"   "+deviceHardwareAddress);

                startCommunicating(device);

//                for(int i = 0;i<device.getUuids().length;i++){
//                    if (device.getUuids()[i].equals(uuid)){
//                        deviceSelected = device;
//                        Log.d(TAG, "connect: ");
//                        break;
//                    }
//                }
            }
        }
        if (deviceSelected!=null)
            startCommunicating(deviceSelected);
    }
}
