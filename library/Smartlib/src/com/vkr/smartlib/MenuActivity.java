package com.vkr.smartlib;

import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        String tno = "7";
        Bundle extras = getIntent().getExtras();   
            String [] params= new String[3]; 
            String message = extras.getString("message"); //get the message from the calling activity
            //store the userid in a session variable
            final String sessionid = extras.getString("sessionid");  
            System.out.println(sessionid);
            TextView mtext = (TextView)findViewById(R.id.tv3);
            mtext.setText(message);
            Alerttask alert = new Alerttask();
            params[0] = tno;
            params[1] = sessionid;
            alert.execute(params);
            try {
				StringBuffer alertmessage = alert.get();
				String str = alertmessage.toString();
				if(str != "")
				{
				AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
				alertbox.setMessage(str);

		        // add a neutral button to the alert box and assign a click listener
		        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

		            // click listener on the alert box
		            public void onClick(DialogInterface arg0, int arg1) {
		                // the button was clicked
		            	
		            	

		            }
		        });

		        // show it
		        alertbox.show();
				}
				else{}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Button searchbutton = (Button)findViewById(R.id.butt1);
            // event handler for search button
            searchbutton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		// onclick pass the intent to the next activity
	        		Intent intent = new Intent(v1.getContext(),SearchbookActivity.class);
	        		Bundle b = new Bundle();
					b.putString("sessionid",sessionid);					
					intent.putExtras(b);
	        		startActivityForResult(intent,0);
	        		
	        		
	        		
	        	}
        
    }
            
            );
            Button studyButton = (Button)findViewById(R.id.butt2);
            // event handler for search button
            studyButton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		// onclick pass the intent to the next activity
	        		Intent intent = new Intent(v1.getContext(),Studyroom.class);
	        		Bundle b = new Bundle();
					b.putString("sessionid",sessionid);					
					intent.putExtras(b);
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
