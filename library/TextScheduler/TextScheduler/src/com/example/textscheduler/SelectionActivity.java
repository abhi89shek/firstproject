package com.example.textscheduler;



import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectionActivity extends Activity implements OnClickListener{

	private Button buttonNewSchedule;
	private Button buttonViewNotifications;
	private String userid = null;
	private String activityNo = null;
	private String params1[] = new String[2];
	private String params2[] = new String[2];
	private Activity myActivity = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        
        
        Bundle extras = getIntent().getExtras();
        String message = extras.getString("message");
        userid = extras.getString("sessionid");
        
        activityNo = "3";
        Notificationtask notificationtask = new Notificationtask(SelectionActivity.this);
        params1[0] = activityNo;
        params1[1] = userid;
        notificationtask.execute(params1);
        try
        {
        	StringBuffer notificationResult = notificationtask.get();
        	String notifications = notificationResult.toString();
        	System.out.println("outside noti");
        	System.out.println(notifications);
        	if(notifications.equals("Y"))
        	{
        		System.out.println("inside noti not working");
        		AlertDialog.Builder notificationsDialog = new AlertDialog.Builder(SelectionActivity.this);
        		notificationsDialog.setTitle("Notification(s) pending!");
        		notificationsDialog.setMessage("Hello!you have new notification(s) pending. click view to see them");
        		notificationsDialog.setPositiveButton("View", new DialogInterface.OnClickListener() 
	    	    {
	    	      public void onClick(DialogInterface dialog, int which) 
	    	      {
	    	    	  activityNo = null;	    	    	  
	    	    	  activityNo = "4";
	    	    	  Notificationtask viewNotificationtask = new Notificationtask(SelectionActivity.this);
	    	    	  params2[0] = activityNo;
	    	          params2[1] = userid;
	    	          viewNotificationtask.execute(params2);	    	          
	    	          StringBuffer viewNotificationResult = null;
						try {
							viewNotificationResult = viewNotificationtask.get();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    	          	String viewNotifications = viewNotificationResult.toString();
	    	          	System.out.println(viewNotifications);
	    	          	Intent notificationIntent = new Intent();
	    	          	notificationIntent.setClass(getBaseContext(),Notificationscreen.class);
	    	          	Bundle newNotifications = new Bundle();
	    	          	newNotifications.putString("notifications",viewNotifications);
	    	          	notificationIntent.putExtras(newNotifications);
	    	          	startActivityForResult(notificationIntent,0);
    	    	   
    	    	  } 
	    	    });
        		notificationsDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() 
    	        {
    	          public void onClick(DialogInterface dialog, int which) 
    	          {
                    	            
    	          }  
    	        });
    	     
                AlertDialog notificationAlert = notificationsDialog.create();
                notificationAlert.show();
        	}
        	else{}
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        buttonNewSchedule = (Button) findViewById(R.id.button_new_schedule);
        buttonViewNotifications = (Button) findViewById(R.id.button_view_notifications);
        
        buttonNewSchedule.setOnClickListener(this);
        buttonViewNotifications.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_selection, menu);
        return true;
    }  
    
    public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.button_new_schedule:
    				
    				Intent myIntent = new Intent(SelectionActivity.this,
							NewScheduleActivity.class);
    				Bundle bunSess = new Bundle();
    				bunSess.putString("sessionid", userid);
    				myIntent.putExtras(bunSess);    				
					SelectionActivity.this.startActivity(myIntent);
					finish();   
					
    			case R.id.button_view_notifications:
    				
    	}
    }
}