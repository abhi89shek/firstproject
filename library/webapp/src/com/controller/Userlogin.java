package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost.TabSpec;
import android.widget.*;

import java.io.InputStream;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Userlogin extends Activity {
	
	    Button signin;
		EditText username,password;
		String uid = null;
		String pass = null;
		String tno = null;
	   
	      

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_userlogin);
	                TabHost th= (TabHost) findViewById (R.id.tabhost);
	                th.setup();
	                TabSpec spec1=th.newTabSpec("t1");
	                spec1.setContent(R.id.tab1);
	                
	                spec1.setIndicator("Existing User");
	                th.addTab(spec1);
	              
	                TabSpec spec2=th.newTabSpec("t2");
	                spec2.setContent(R.id.tab2);
	                spec2.setIndicator("New user");
	                th.addTab(spec2);
	                
	                TabSpec spec3=th.newTabSpec("t3");
	                spec3.setContent(R.id.tab3);
	                spec3.setIndicator("Admin");
	                th.addTab(spec3);

	        username=(EditText) findViewById (R.id.editText1);
	       password=(EditText) findViewById  (R.id.editText2);
	        signin=(Button) findViewById (R.id.button1);  
	          
	         
	        
	      
	              
	           signin.setOnClickListener(new OnClickListener(){
						public void onClick(View v1) {
						// TODO Auto-generated method stub
							//Toast.makeText(this, "hello", 1000).show();
							uid=username.getText().toString();
					         pass=password.getText().toString();
							System.out.println("button");
							tno = "1";
						if(uid.equals("") || pass.equals(""))
						{
							System.out.println("You have not provided credentials");
						}
						else 
						{
							System.out.println("button1");
							String [] params = new String[20] ;
							params[0] = tno;
							params[1] = uid;
							params[2] = pass;
							Networkclass netclass = new Networkclass();
							 netclass.execute(params);
                             
							
						}
							
					}
	        		
	                                 	            }
	            );
            
}

	   /* public void loginHandler(View v)
	    {
	    	System.out.println("button");
			tno = "1";
		if(uid== null || pass== null)
		{
			System.out.println("INVALID USERNAME/PASSWORD");
			Toast.makeText(this,"invalid",1000).show();
		}
		else 
		{
			System.out.println("button1");
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("tno", tno));
			nameValuePairs.add(new BasicNameValuePair("id", uid));
			nameValuePairs.add(new BasicNameValuePair("p1", pass)); 

			try {
			        HttpClient httpClient = new DefaultHttpClient();
			        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/webapp/Controllerservlet/");//the webserver address goes in here
			        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			        HttpResponse response = httpClient.execute(httpPost);
			        HttpEntity entity = response.getEntity();
			        InputStream is = entity.getContent();   
			        Toast.makeText(this,is.toString(),1000).show();
			    }catch (Exception e) {
			        // TODO: handle exception
			                	    }
		}
	    }*/


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_userlogin, menu);
    return true;
}
}