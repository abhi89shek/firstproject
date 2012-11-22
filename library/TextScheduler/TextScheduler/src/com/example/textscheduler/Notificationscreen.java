package com.example.textscheduler;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Notificationscreen extends Activity {

	private String notificationValues[] = null;
	private ArrayAdapter<String> notificationAdapter ; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationscreen);
        
        
        
        Bundle notificationBundle = getIntent().getExtras();
        String notifications = notificationBundle.getString("notifications");
        System.out.println(notifications);
        notificationValues = notifications.split(":");
        //System.out.println(notificationValues);
        //setListAdapter(new ArrayAdapter<String>(this, R.layout.notificationlist));        
        ListView listitems=(ListView)findViewById(R.id.listView1);
        ArrayList<String> newNotifications = new ArrayList<String>();  
        newNotifications.addAll( Arrays.asList(notificationValues) ); 
        notificationAdapter = new ArrayAdapter<String>(this, R.layout.notificationlist,R.id.notification, newNotifications);  
        listitems.setAdapter(notificationAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notificationscreen, menu);
        return true;
    }
}
