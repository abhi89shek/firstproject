package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.Iterator;
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
import android.widget.EditText;
import android.widget.RadioButton;

public class SearchbookActivity extends Activity {
	Button search;
	RadioButton rb1,rb2,rb3;
	EditText src;
	String ano = null;
	String []parameters1 = new String[3];
	String []parameters2 = new String[3];
	String []parameters3 = new String[3];
	String sessionid = null;
	
	String books = null;
	Iterator it = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
        	 sessionid = extras.getString("sessionid");
        }
        else{}
       
     
       Button search = (Button)findViewById(R.id.butts1);
       search.setOnClickListener(new OnClickListener(){
       	public void onClick(View v1) {
       		//radio buttons to search books based on 3 criterias
       		src=(EditText) findViewById (R.id.et1);
       		rb1 =  (RadioButton)findViewById(R.id.radiobutts1);
       		rb2 = (RadioButton)findViewById(R.id.radiobutts2);
       		rb3 = (RadioButton)findViewById(R.id.radiobutts3);
       		if(rb1.isChecked())  // search book by name
       		{
       			ano = "4";
       			String keyword = src.getText().toString();   
       			//async task to make http connection
       			SearchBooktask sbtask = new SearchBooktask(SearchbookActivity.this);
       			parameters1[0] = ano;
       			parameters1[1] = keyword;
       			sbtask.execute(parameters1); 
       			
       			try {
					books = sbtask.get();
					if(books == "N")
					{
						AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
						alertbox.setMessage("Sorry. Book not available");

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
					//if books are found matching the name ,a new page is played with natching book names
					Intent intent = new Intent(v1.getContext(),SearchresultActivity.class);
					Bundle b = new Bundle();
					b.putString("books", books);
					b.putString("sessionid", sessionid);
					intent.putExtras(b);
					//intent.putExtra("message", str1);
					//intent.putExtra("sesionid", uid);
					startActivityForResult(intent,0);					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       			
       			
       		}
       		if(rb2.isChecked())  //search by book ISBN
       		{
       			ano = "5";
       			String keyword = src.getText().toString();       			 
       			SearchBooktask sbtask = new SearchBooktask(SearchbookActivity.this);
       			parameters2[0] = ano;
       			parameters2[1] = keyword;
       			sbtask.execute(parameters2);
       			
       			try {
					books = sbtask.get();
					if(books == "N")
					{
						AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
						alertbox.setMessage("Sorry. Book not available");

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
					Intent intent = new Intent(v1.getContext(),SearchresultActivity.class);
					Bundle b = new Bundle();
					b.putString("books", books);
					b.putString("sessionid", sessionid);
					intent.putExtras(b);
					//intent.putExtra("message", str1);
					//intent.putExtra("sesionid", uid);
					startActivityForResult(intent,0);
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       		}
       		if(rb3.isChecked())		//search by author name
       		{
       			ano = "6";
       			String keyword = src.getText().toString();       			 
       			SearchBooktask sbtask = new SearchBooktask(SearchbookActivity.this);
       			parameters3[0] = ano;
       			parameters3[1] = keyword;
       			sbtask.execute(parameters3);
       			
       			try {
					books = sbtask.get();
					if(books == "N")
					{
						AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
						alertbox.setMessage("Sorry. Book not available");

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
					Intent intent = new Intent(v1.getContext(),SearchresultActivity.class);
					Bundle b = new Bundle();
					b.putString("books", books);
					b.putString("sessionid", sessionid);
					intent.putExtras(b);
					//intent.putExtra("message", str1);
					//intent.putExtra("sesionid", uid);
					startActivityForResult(intent,0);
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       		}
       		
       		//Intent intent = new Intent(v1.getContext(),SearchbookActivity.class);
       		//startActivityForResult(intent,0);
       		
       		
       	}
       }
       	);       
       	
       
    }
       

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_searchbook, menu);
        return true;
    }
}
