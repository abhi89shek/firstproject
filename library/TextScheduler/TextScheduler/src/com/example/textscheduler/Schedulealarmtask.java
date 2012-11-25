package com.example.textscheduler;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
 

public class Schedulealarmtask implements Runnable{
    
    private  Calendar date;
    
    private final AlarmManager am;
   
    private final Context context;
    private String msg = null;
    private String occasion = null;
    private String name = null;
    private int year ;
    private int month ;
    private int day ;
    private int hour ;
    private int minute ;
 
    public Schedulealarmtask(Context context, int year,int month,int day,int hour,int minute,String typedMsg,String occasion,String name) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = null;
        this.msg = typedMsg;
        this.occasion = occasion;
        this.name = name;
        this.year= year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
     
    public void run() {
        
    	System.out.println("inside schedule alarm");
    	Calendar cal = Calendar.getInstance();
    	cal.set(year, month, day);
    	cal.set(Calendar.HOUR_OF_DAY, hour);
    	cal.set(Calendar.MINUTE, minute);
    	cal.set(Calendar.SECOND, 0);
    	this.date = cal;
        Intent intent = new Intent(context, Textservicenotify.class);
        intent.putExtra(Textservicenotify.INTENT_NOTIFY, true);
        Bundle textBundle = new Bundle();        
        textBundle.putString("message", msg);
        textBundle.putString("occasion", occasion);
        textBundle.putString("name", name);
        intent.putExtras(textBundle);
        System.out.println("inside schedule alarm1");
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        System.out.println("inside schedule alarm2");
        
        am.set(AlarmManager.RTC, date.getTimeInMillis(), pendingIntent);
        System.out.println("inside schedule alarm3");
    }
}