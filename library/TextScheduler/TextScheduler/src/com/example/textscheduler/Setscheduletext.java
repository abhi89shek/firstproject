package com.example.textscheduler;

import java.util.Calendar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
 

public class Setscheduletext {
 
    
    private Scheduletextservice mBoundService;
    
    private Context mContext;
    
    private boolean mIsBound;
 
    public Setscheduletext(Context context) {
        mContext = context;
    }
     
    
    public void doBindService() {
        
    	System.out.println("inside dobind()");
        mContext.bindService(new Intent(mContext, Scheduletextservice.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
     
    
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            
        	System.out.println("inside service connection");
            mBoundService = ((Scheduletextservice.ServiceBinder) service).getService();
        }
 
        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
        }
    };
 
    
    public void setAlarmForNotification(int year,int month,int day,int hour,int minute,String typedMsg,String occasion,String name){
    	System.out.println("inside set schedule task");
    	mBoundService.setAlarm(year,month,day,hour,minute,typedMsg,occasion,name);
    }
     
    
    public void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            mContext.unbindService(mConnection);
            mIsBound = false;
        }
    }
}
