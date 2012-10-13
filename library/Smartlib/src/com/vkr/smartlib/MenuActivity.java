package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
     // get the values from the calling activity via an intent
        Bundle extras = getIntent().getExtras();   
       
            String message = extras.getString("message"); //get the message from the calling activity
            //store the userid in a session variable
            String sessionid = extras.getString("sessionid");             
            TextView mtext = (TextView)findViewById(R.id.tv3);
            mtext.setText(message);            
            Button searchbutton = (Button)findViewById(R.id.butt1);
            // event handler for search button
            searchbutton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		// onclick pass the intent to the next activity
	        		Intent intent = new Intent(v1.getContext(),SearchbookActivity.class);
	        		startActivityForResult(intent,0);
	        		
	        		
	        		
	        	}
        
    }
            
            );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
}
