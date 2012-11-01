package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class Studyroom extends Activity {
	 
	 public int myYear, myMonth, myDay, myHour, myMinute;
	 public String myDate, myTime;
	 static final int ID_DATEPICKER = 0;
	 static final int ID_TIMEPICKER = 1;
	 public Button submit = null;
	 public String tno = null;
	 public String sessionid = null;
	 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_studyroom);
	        
	        Bundle extras = getIntent().getExtras(); //get the list of books from the calling activity
	        sessionid = extras.getString("sessionid");
	        
	        Button datePickerButton = (Button)findViewById(R.id.datepickerbutton);
	        Button timePickerButton = (Button)findViewById(R.id.timepickerbutton);
	        datePickerButton.setOnClickListener(datePickerButtonOnClickListener);
	        timePickerButton.setOnClickListener(timePickerButtonOnClickListener);
	        
	        submit = (Button)findViewById(R.id.b1);
	 	   submit.setOnClickListener(new OnClickListener(){         
	 			public void onClick(View v1) {
	 			// TODO Auto-generated method stub
	 				//Toast.makeText(this, "hello", 1000).show();
	 				System.out.println("button");
	 				tno = "15";   // a unique identifier to differentiate the tabs on the main screen
	 				//check if the user has entered the credentials.display error if not.				
	 				String [] params = new String[20] ;
	 				params[0] = tno;
	 				params[1] = sessionid;
	 				params[2] = myDate;
	 				params[3] = myTime;
	 				Networkclass_studyroom studyroom = new Networkclass_studyroom(Studyroom.this); // a new thread is created for 
	 															// making the http connection to server
	 				//StringBuffer responseString = new StringBuffer("");
	 				  studyroom.execute(params);
	 				  try {
	 					StringBuffer str = studyroom.get();
	 					String message = str.toString();
	 					//check if the user has provided the valid credentials
	 					if(message.equals("N"))
	 					{
	 						Toast.makeText(getBaseContext(), "This time slot is busy.Please choose a different one", 1000000000).show();
	 					}
	 					else
	 					{
	 					//if credentials are correct a new activity with a welcome page is created
	 						AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
							alertbox.setMessage(message);

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
	 				} catch (InterruptedException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} catch (ExecutionException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}catch (Exception e){
	 					e.printStackTrace();
	 				}
	 				  
	                 
	 				
	 			}
	 				
	 		
	 		
	                         	            
	 	 });
	    }
	    
	    
	    private Button.OnClickListener datePickerButtonOnClickListener
	     = new Button.OnClickListener(){

	   public void onClick(View v) {
	    // TODO Auto-generated method stub
	    final Calendar c = Calendar.getInstance();
	    myYear = c.get(Calendar.YEAR);
	    myMonth = c.get(Calendar.MONTH);
	    myDay = c.get(Calendar.DAY_OF_MONTH);
	    showDialog(ID_DATEPICKER);
	   }
	    };
	    
	    private Button.OnClickListener timePickerButtonOnClickListener
	  = new Button.OnClickListener(){

	   public void onClick(View v) {
	    // TODO Auto-generated method stub
	    final Calendar c = Calendar.getInstance();
	    myHour = c.get(Calendar.HOUR_OF_DAY);
	    myMinute = c.get(Calendar.MINUTE);
	    showDialog(ID_TIMEPICKER);
	   }
	    };

	 @Override
	 protected Dialog onCreateDialog(int id) {
	  // TODO Auto-generated method stub
	  switch(id){
	   case ID_DATEPICKER:
	    return new DatePickerDialog(this,
	      myDateSetListener,
	      myYear, myMonth, myDay);
	   case ID_TIMEPICKER:
	    return new TimePickerDialog(this,
	      myTimeSetListener,
	      myHour, myMinute, false);
	   default:
	    return null;
	    
	  }
	 }
	    
	 private DatePickerDialog.OnDateSetListener myDateSetListener
	  = new DatePickerDialog.OnDateSetListener(){

	   public void onDateSet(DatePicker view, int year, 
	     int monthOfYear, int dayOfMonth) {
	    // TODO Auto-generated method stub
	    String date = "Year: " + String.valueOf(year) + "\n"
	     + "Month: " + String.valueOf(monthOfYear+1) + "\n"
	     + "Day: " + String.valueOf(dayOfMonth);
	    Toast.makeText(Studyroom.this, date, 
	      Toast.LENGTH_LONG).show();
	    myDate=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);
	   }
	 };
	 
	 private TimePickerDialog.OnTimeSetListener myTimeSetListener
	  = new TimePickerDialog.OnTimeSetListener(){

	   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	    // TODO Auto-generated method stub
	    String time = "Hour: " + String.valueOf(hourOfDay) + "\n"
	     + "Minute: " + String.valueOf(minute);
	    Toast.makeText(Studyroom.this, time, 
	      Toast.LENGTH_LONG).show();
	    myTime=String.valueOf(hourOfDay)+":"+String.valueOf(minute)+":00";
	   }
	 };
	   
	   
	   }

	 

	    


