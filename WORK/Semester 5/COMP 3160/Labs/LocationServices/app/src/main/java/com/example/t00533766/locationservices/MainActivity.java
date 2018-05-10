package com.example.t00533766.locationservices;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    /*
    * To work with location call backl override location result
    * */

    private final String TAG = "_+_+_++_+_+_+_+_+";
    private final int LOCATION_REQUEST_CODE = 3;


    private TextView textView;
    private Button button;
    private Observer observer;

    private LocationViewModel locationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.text);
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel.class);

        if(hasLocationPermission()){
            useGPSToGetLocation();
        }else {
            requestLocationPermission();
        }

        observer =  new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                textView.setText(location.toString());
                Log.d(TAG, "onChanged: "+location.toString());
            }
        };
    }

    public void getLocation(View view) {
        if(hasLocationPermission()){
            useGPSToGetLocation();
        }else{
            requestLocationPermission();
        }
    }


    private void useGPSToGetLocation() {

        locationViewModel.getLocation().observe(this,observer );
        Log.d(TAG, "useGPSToGetLocation: "+locationViewModel.getLocation().hasActiveObservers());
    }

    private void requestLocationPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }
    }

    private boolean hasLocationPermission() {
        return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_REQUEST_CODE){

            if(grantResults.length>0&&(grantResults[0]==PackageManager.PERMISSION_GRANTED||
                    grantResults[1]==PackageManager.PERMISSION_GRANTED)){

                useGPSToGetLocation();

            }
        }
    }
}
