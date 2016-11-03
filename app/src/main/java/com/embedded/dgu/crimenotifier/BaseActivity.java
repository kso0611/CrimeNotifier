package com.embedded.dgu.crimenotifier;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View v){
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setContentView(int layoutResID) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(layoutResID);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}