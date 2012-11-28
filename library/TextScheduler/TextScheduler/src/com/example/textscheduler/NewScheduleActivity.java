package com.example.textscheduler;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

@TargetApi(11)
public class NewScheduleActivity extends Activity implements OnClickListener{
	
	EditText composeMsg;
	Button buttonSelectContact;
	Button buttonSaveSchedule;
	Button buttonCancelSchedule;
	Button buttonSelectDate;
	Button buttonSelectTime;
	TextView tvDate;
	TextView tvTime;
	TextView tvContact;
	Spinner spnOccasion;
	
	String newdate = null;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private StringBuilder date = new StringBuilder();
	private StringBuilder time = new StringBuilder();
	private String fullSchedule = new String();
	private String name = new String();
	private String occasion = new String();
	private String typedMsg = null;
	private String userid = null;
	int PICK_CONTACT;
	
	 private Setscheduletext scheduletext = null;
	
	Calendar cal = Calendar.getInstance();
	
	static final int DATE_DIALOG_ID = 999;
	static final int TIME_DIALOG_ID = 998;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_schedule);
		
		Bundle extras = getIntent().getExtras();
        String message = extras.getString("message");
        userid = extras.getString("sessionid");
        
		scheduletext = new Setscheduletext(this);
		scheduletext.doBindService();
		
		composeMsg = (EditText) findViewById (R.id.composeMessage);
		buttonSelectContact = (Button)findViewById (R.id.buttonPickContact);
		buttonSaveSchedule = (Button) findViewById (R.id.buttonSave);
		buttonCancelSchedule = (Button) findViewById (R.id.buttonCancel);
		buttonSelectDate = (Button) findViewById (R.id.buttonPickDate);
		buttonSelectTime = (Button) findViewById (R.id.buttonPickTime);
		tvDate = (TextView) findViewById (R.id.tvSelectedDate);
		tvTime = (TextView) findViewById (R.id.tvSelectedTime);
		tvContact = (TextView) findViewById (R.id.tvSelectedContact);
		
		buttonSelectContact.setOnClickListener(this);
		buttonSaveSchedule.setOnClickListener(this);
		buttonCancelSchedule.setOnClickListener(this);
		buttonSelectDate.setOnClickListener(this);
		buttonSelectTime.setOnClickListener(this);
		
		addListenerOnSpinnerItemSelection();
	}	
	
	public void addListenerOnSpinnerItemSelection() {
		spnOccasion = (Spinner) findViewById(R.id.spinnerOccasion);
		spnOccasion.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonPickContact:
				Intent intent = new Intent(Intent.ACTION_PICK);
	            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
	            startActivityForResult(intent, PICK_CONTACT);
				break;		
				
			case R.id.buttonSave:
				 typedMsg = composeMsg.getText().toString();				
				
				occasion = String.valueOf(spnOccasion.getSelectedItem());
				System.out.println(typedMsg);
				System.out.println(occasion);
				System.out.println(name);
				System.out.println(year);
				System.out.println(month+1);
				System.out.println(day);
				System.out.println(hour);
				System.out.println(minute);
				try{
				scheduletext.setAlarmForNotification(year,month+1,day,hour,minute,typedMsg,occasion,name);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				fullSchedule += ("Contact: " + name + "\n");
				fullSchedule += ("Occasion: " + occasion + "\n");
				fullSchedule += (("Date: ")+(month + 1)+("-")+(day)+("-")+(year)+("\n"));
				fullSchedule += (("Time: ")+(pad(hour))+(":")+(pad(minute))+("\n"));
				fullSchedule += ("Message: " + typedMsg);
								
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 		alertDialogBuilder.setTitle("Share With Others");
		 		
		 		alertDialogBuilder
				.setMessage(fullSchedule)
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						//NewScheduleActivity.this.finish();
						//String date1 = date.toString();
						//System.out.println(date1);
						
						Intent shareIntent = new Intent(getBaseContext(), ShareWith.class);
						
						Bundle share = new Bundle();
						share.putString("sendTo", name);
						share.putString("occasion", occasion);
						share.putString("date", newdate);
						share.putString("sender", userid);
						shareIntent.putExtras(share);						
						startActivityForResult(shareIntent,0);
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
		 		
		 		AlertDialog shareAlertDialog = alertDialogBuilder.create();	 		 
				shareAlertDialog.show();
				
				break;
				
			case R.id.buttonCancel:
				Intent myIntent = new Intent(NewScheduleActivity.this,
						SelectionActivity.class);
				NewScheduleActivity.this.startActivity(myIntent);
				finish();
		
				break;
				
			case R.id.buttonPickDate:
				showDialog(DATE_DIALOG_ID);
				break;
				
			case R.id.buttonPickTime:
				showDialog(TIME_DIALOG_ID);				
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_CONTACT)
	      {         
	          Cursor cursor =  managedQuery(data.getData(), null, null, null, null);
	          cursor.moveToNext();
	          String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
	          name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
	          
	          tvContact.setText("Contact Name: " + name);	           
	      }			
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		final Calendar c = Calendar.getInstance();
		switch (id) {
			case DATE_DIALOG_ID:
				year = c.get(Calendar.YEAR);
				month = c.get(Calendar.MONTH);
				day = c.get(Calendar.DAY_OF_MONTH);
				return new DatePickerDialog(this, datePickerListener, year, month,day);				
				
			case TIME_DIALOG_ID:
				hour = c.get(Calendar.HOUR_OF_DAY);
				minute = c.get(Calendar.MINUTE);
				return new TimePickerDialog(this, timePickerListener, hour, minute,false);				
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			date.append(year).append("-").append(month+1).append("-").append(day);
			newdate = year+"-"+(month+1)+"-"+day+"";
			
			tvDate.setText(date);
			
			date.setLength(0);
			
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			time.append(pad(hour)).append(":").append(pad(minute));
			
			tvTime.setText(time);
			
			time.setLength(0);
		}
	};
	
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
	@Override
    protected void onStop() {
        
        if(scheduletext != null)
        	scheduletext.doUnbindService();
        super.onStop();
    }
}