package com.embedded.dgu.crimenotifier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button _notify, _cancel;

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.notify:
                startActivity(new Intent(MainActivity.this, CollectedActivity.class));
                break;
            case R.id.cancel:
                startActivity(new Intent(MainActivity.this, AlertActivity.class));
                finish();
                break;
            default: break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _notify = (Button)findViewById(R.id.notify);
        _cancel = (Button)findViewById(R.id.cancel);
        _notify.setOnClickListener(this);
        _cancel.setOnClickListener(this);
    }
}
