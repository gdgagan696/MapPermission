package com.example.gagan.googlemapapitest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class Maps extends AppCompatActivity implements OnMapReadyCallback{
    private static final String FINE_LOCATION= Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION=Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_REQ=1000;
    private boolean locationPermission=false;
    private  GoogleMap mgoogleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getLocationPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("GAGAN","ON REQUEST CALLED");
        locationPermission=false;
        switch (requestCode){
        case LOCATION_REQ:
        {
            if(grantResults.length > 0)
            {
                for(int i=0;i<grantResults.length;i++)
                {
                    if(grantResults[i]!=PackageManager.PERMISSION_GRANTED)
                    {
                        locationPermission=false;
                        return;
                    }
                }
                Log.d("GAGAN","PERMISSION GRANTED");
                locationPermission=true;
                //init map
                initMap();
            }


            }
        }
    }
    private void initMap()
    {
        Log.d("GAGAN","INITIALISING MAP");
        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void getLocationPermission()
    {
        Log.d("GAGAN","GETTING PERMISSION");
        String permission[]={Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                Log.d("GAGAN","PERMISSION GRANTD");
                locationPermission=true;
                initMap();
            }
            else {
                ActivityCompat.requestPermissions(this,permission,LOCATION_REQ);
            }
        }
        else {
            ActivityCompat.requestPermissions(this,permission,LOCATION_REQ);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(Maps.this, "READY TO MAP", Toast.LENGTH_SHORT).show();
        mgoogleMap=googleMap;


    }
}
