package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
        Button manage=(Button) findViewById(R.id.butta1);
        Button report=(Button) findViewById(R.id.butta2);
        Button scan = (Button)findViewById(R.id.scanbutton);
        
        manage.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent();
				in.setClass(getBaseContext(),ManageActivity.class);
				startActivity(in);
			}
			
        	
        });
        report.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent();
				in.setClass(getBaseContext(),ViewreportActivity.class);
				startActivity(in);
			}
			
        	
        });
        scan.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "PRODUCT_MODE");
		        startActivityForResult(intent,0);
			}
			
			
        	
        });
        
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	    	
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            //System.out.println(contents);
	           // Log.i("message","hello scan");
	            //Log.i("childmessage", contents);
	            TextView mtext1 = (TextView)findViewById(R.id.scanresult);
	            mtext1.setText(contents);			            
	            //TextView mtext2 = (TextView)findViewById(R.id.scanresult);
	            //mtext2.setText(format);
	            // Handle successful scan
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        	TextView mtext3 = (TextView)findViewById(R.id.scanresult);
	            mtext3.setText("helooo child");
	        }
	    }
	}
    @Override
    public void onConfigurationChanged(Configuration newConfig){        
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_adminlogin, menu);
        return true;
    }
}
