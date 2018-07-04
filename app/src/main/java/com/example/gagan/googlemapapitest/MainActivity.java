package com.example.gagan.googlemapapitest;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final int ERROR_DIALOG=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isPlayServices())
        {
            init();
        }
    }
    private void init()
    {
        Button b1=findViewById(R.id.map);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Maps.class));
            }
        });
    }
    public boolean isPlayServices()
    {
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available== ConnectionResult.SUCCESS)
        {
            Log.i("GAGAN","Service Available");
            return true;
        }

        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            Log.i("GAGAN","ERROR CAN BE SOLVED");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG);
            dialog.show();
        }
        else {
            Toast.makeText(this, "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
