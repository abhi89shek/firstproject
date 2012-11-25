package com.example.textscheduler;

import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
 

 
public class Scheduletextservice extends Service {
 
   
    public class ServiceBinder extends Binder {
    	Scheduletextservice getService() {
            return Scheduletextservice.this;
        }
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Scheduletextservice", "Received start id " + startId + ": " + intent);
        System.out.println("inside schedule task1");
        
        return START_STICKY;
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
 
    
    private final IBinder mBinder = new ServiceBinder();
 
    
    public void setAlarm(int year,int month,int day,int hour,int minute,String typedMsg,String occasion,String name) {
        
    	System.out.println("inside schedule task");
        new Schedulealarmtask(this,year,month,day,hour,minute, typedMsg,occasion,name).run();
    }
}
