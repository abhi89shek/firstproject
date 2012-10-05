package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
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
	ArrayList books = null;
	Iterator it = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);
        Bundle extras = getIntent().getExtras();
        //String sessionid = extras.getString("sessionid");

       
     
       Button search = (Button)findViewById(R.id.butts1);
       search.setOnClickListener(new OnClickListener(){
       	public void onClick(View v1) {
       		src=(EditText) findViewById (R.id.et1);
       		rb1 =  (RadioButton)findViewById(R.id.radiobutts1);
       		rb2 = (RadioButton)findViewById(R.id.radiobutts2);
       		rb3 = (RadioButton)findViewById(R.id.radiobutts3);
       		if(rb1.isChecked())
       		{
       			ano = "4";
       			String keyword = src.getText().toString();       			 
       			SearchBooktask sbtask = new SearchBooktask();
       			parameters1[0] = ano;
       			parameters1[1] = keyword;
       			sbtask.execute(parameters1); 
       			books = new ArrayList();
       			try {
					books = sbtask.get();
					Intent intent = new Intent(v1.getContext(),SearchresultActivity.class);
					Bundle b = new Bundle();
					b.putStringArrayList("books", books);
					intent.putExtras(b);
					//intent.putExtra("message", str1);
					//intent.putExtra("sesionid", uid);
					startActivityForResult(intent,0);
					it = books.iterator();
					while(it.hasNext())
					{
						System.out.println(it.next());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       			
       			
       		}
       		if(rb2.isChecked())
       		{
       			ano = "5";
       			String keyword = src.getText().toString();       			 
       			SearchBooktask sbtask = new SearchBooktask();
       			parameters2[0] = ano;
       			parameters2[1] = keyword;
       			sbtask.execute(parameters2);
       			books = new ArrayList();
       			try {
					books = sbtask.get();
					Intent intent = new Intent(v1.getContext(),SearchresultActivity.class);
					Bundle b = new Bundle();
					b.putStringArrayList("books", books);
					intent.putExtras(b);
					//intent.putExtra("message", str1);
					//intent.putExtra("sesionid", uid);
					startActivityForResult(intent,0);
					it = books.iterator();
					while(it.hasNext())
					{
						System.out.println(it.next());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       		}
       		if(rb3.isChecked())
       		{
       			ano = "6";
       			String keyword = src.getText().toString();       			 
       			SearchBooktask sbtask = new SearchBooktask();
       			parameters3[0] = ano;
       			parameters3[1] = keyword;
       			sbtask.execute(parameters3);
       			books = new ArrayList();
       			try {
					books = sbtask.get();
					Intent intent = new Intent(v1.getContext(),SearchresultActivity.class);
					Bundle b = new Bundle();
					b.putStringArrayList("books", books);
					intent.putExtras(b);
					//intent.putExtra("message", str1);
					//intent.putExtra("sesionid", uid);
					startActivityForResult(intent,0);
					it = books.iterator();
					while(it.hasNext())
					{
						System.out.println(it.next());
					}
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
