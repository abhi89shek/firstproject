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
     // get the values from the calling activity via an intent
        Bundle extras = getIntent().getExtras();  
      //get the message from the calling activity
        String message = extras.getString("message");
        //store the adminid in the session variable
        String sessionid = extras.getString("sessionid");
        //set the message in the textview of the current activity
        TextView mtext = (TextView)findViewById(R.id.tva1);
        mtext.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_adminlogin, menu);
        return true;
    }
}
