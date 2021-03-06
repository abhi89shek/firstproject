package com.example.textscheduler;

import java.util.concurrent.ExecutionException;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

public class ShareWith extends Activity
{
	private String emailAddress = null;
	private String contactId = null;
	private int res=0;
	private String []params = new String[7];
	private String sendTo = null;
	private String sender = null;
	private String occasion = null;
	private String sdate = null;
			
	
	protected static final int PICK_CONTACT = 1;
	private EditText usernameText;
	private AlertDialog.Builder moreShareDialog= null;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	Bundle share = getIntent().getExtras();
    	sendTo = share.getString("sendTo");
		occasion = share.getString("occasion");
		sdate = share.getString("date");
		sender = share.getString("sender");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with);
        
        EditText usernameText = (EditText)findViewById(R.id.addemailid);
        usernameText.setOnClickListener(new OnClickListener()
                                        {        								  

										  public void onClick(View arg0) 
										  {
											 
										  	   Intent intent_callContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
      									       startActivityForResult(intent_callContact, PICK_CONTACT);
										     
      								      }

										});

    }
   
    
    @SuppressWarnings("deprecation")
	public void onActivityResult(int reqCode, int resultCode, Intent data) 
    {     
    	//do{ 
    	System.out.println("hello1");
    	super.onActivityResult(reqCode, resultCode, data);
         
    	 
    	  if(reqCode == PICK_CONTACT)
    	  {
    		  System.out.println("hello2");
    	      if (resultCode == Activity.RESULT_OK) 
    	      {
    	    	  System.out.println("hello3");
    	    	Uri contactData = data.getData();
    	        Cursor cursor =  managedQuery(contactData, null, null, null, null);
    	        if (cursor.moveToFirst()) 
    	        {
    	        	System.out.println("hello4");
    	          contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
    	          String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    	        }
    	        
    	        Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,null, null);
    	        while (emails.moveToNext()) 
    	        {
    	        	System.out.println("hello5");
    	          emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
    	        }    	        
    	        System.out.println(emailAddress);
    	        emails.close();
    	      }
    	      
    	  }
    	  
    	  AlertDialog.Builder shareDialog= new AlertDialog.Builder(this);
    	  shareDialog.setTitle("Confirm");
    	  shareDialog.setMessage("you have chosen to share an event with "+emailAddress+" Click confirm to share");
    	  shareDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() 
    	  {
    	     public void onClick(DialogInterface dialog, int which) 
    	     {
    	    	 String activityNo = "5";
    	    	 Httpsharewith shareWithUser = new Httpsharewith(ShareWith.this);
    	    	 params[0] = activityNo;
    	    	 params[1] = sender;
    	    	 params[2] = sendTo;
    	    	 params[3] = emailAddress;
    	    	 params[4] = sdate;
    	    	 params[5] = occasion;
    	    	 shareWithUser.execute(params);
				  Log.i("tag","i should be here 3rd");
				  
					StringBuffer str = null;
					try {
						str = shareWithUser.get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 Log.i("tag","i should be here 4th");
					String str1 = str.toString();
					 Log.i("tag","i should be here 5th");
    	    	 
    	        moreShareDialog= new AlertDialog.Builder(ShareWith.this);
	    	    moreShareDialog.setTitle("shared");
	    	    moreShareDialog.setMessage("Event shared. Do you want to share with more friends?");
	    	    moreShareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
	    	    {
	    	      public void onClick(DialogInterface dialog, int which) 
	    	      {
    	    	   res = 1; 
    	    	   Intent intent_callContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				   startActivityForResult(intent_callContact, PICK_CONTACT);
    	    	  } 
	    	    });
    	        moreShareDialog.setNegativeButton("No", new DialogInterface.OnClickListener() 
    	        {
    	          public void onClick(DialogInterface dialog, int which) 
    	          {
                    res = 0;	            
    	          }  
    	        });
    	     
                AlertDialog helpDialog = moreShareDialog.create();
    	    	helpDialog.show();
    	    	  
    	      }  
    	  });
    	  
    	  shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
    	  {
    	      public void onClick(DialogInterface dialog, int which) 
    	      {
    	    	  res = 0;
    	      } 
    	  });
    	  
    	  AlertDialog shareDialog1 = shareDialog.create();
    	  shareDialog1.show();
         // }while(res == 1);
    	 
    	}
      
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_share_with, menu);
        return true;
    }
}
