package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class AdminloginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        Bundle extras = getIntent().getExtras();
        
        String message = extras.getString("message");
        String sessionid = extras.getString("sessionid");
        
        TextView mtext = (TextView)findViewById(R.id.tva1);
        mtext.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_adminlogin, menu);
        return true;
    }
}
