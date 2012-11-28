package com.example.textscheduler;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
 


public class Textservicenotify extends Service {
 
    
    public class ServiceBinder extends Binder {
    	Textservicenotify getService() {
            return Textservicenotify.this;
        }
    }
 
    
    private static final int NOTIFICATION = 123;
     
    public static final String INTENT_NOTIFY = "com.example.textscheduler.INTENT_NOTIFY";
    
    private NotificationManager mNM;
 
    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
         
        
        if(intent.getBooleanExtra(INTENT_NOTIFY, false))
        {
        	System.out.println("inside text notify");
        	Bundle bundle = intent.getExtras();
        	String message = bundle.getString("message");
        	String occasion = bundle.getString("occasion");
        	String name = bundle.getString("name");
            showNotification(message,occasion,name);
        }
         
        
        return START_NOT_STICKY;
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
 
    
    private final IBinder mBinder = new ServiceBinder();
 
    
    private void showNotification(String message,String occasion,String name) {
        
    	System.out.println("inside text notifyuser");
        CharSequence title = "Alarm!!";
        
        int icon = R.drawable.ic_dialog_alert;
        
        CharSequence text = "Hey! This is a reminder for today's event: "+occasion+ "for "+name+" .your message :"+message ;       
        
        long time = System.currentTimeMillis();
         
        Notification notification = new Notification(icon, text, time);
 
        
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, LoginpageActivity.class), 0);
 
        
        notification.setLatestEventInfo(this, title, text, contentIntent);
 
       
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
         
        
        mNM.notify(NOTIFICATION, notification);
         
        
        stopSelf();
    }
}
