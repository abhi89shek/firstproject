package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost.TabSpec;
import android.widget.*;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Userlogin extends Activity {
	
	    Button signin,signinadmin;
		EditText username,password,adminId,adminpass;
		String uid = null;
		String pass = null;
		String tno = null;
		String aid = null;
		String apass = null;
	   
	      

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_userlogin);
	                TabHost th= (TabHost) findViewById (R.id.tabhost);
	                th.setup();
	                TabSpec spec1=th.newTabSpec("t1");
	                spec1.setContent(R.id.tab1);
	                
	                spec1.setIndicator("Sign in");
	                th.addTab(spec1);
	              
	                TabSpec spec2=th.newTabSpec("t2");
	                spec2.setContent(R.id.tab2);
	                spec2.setIndicator("sign up");
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
							//StringBuffer responseString = new StringBuffer("");
							  netclass.execute(params);
							  try {
								StringBuffer str = netclass.get();
								String str1 = str.toString();
								System.out.println(str);
								Intent intent = new Intent(v1.getContext(), MenuActivity.class);
								Bundle b = new Bundle();
								b.putString("message",str1);
								b.putString("sessionid", uid);
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
							}catch (Exception e){
								e.printStackTrace();
							}
							
                             
							
						}
							
					}
	        		
	                                 	            }
	            );
	           
	           adminId=(EditText) findViewById (R.id.editText10);
		       adminpass=(EditText) findViewById  (R.id.editText11);
		        signinadmin=(Button) findViewById (R.id.button4); 
            
	           
		        
		        signinadmin.setOnClickListener(new OnClickListener(){
		        	public void onClick(View v1) {
		        		
		        	 aid = adminId.getText().toString();
		        	 apass = adminpass.getText().toString();
		        	  tno = "2";
		        	  
		        	  if(aid.equals("") || apass.equals(""))
						{
							System.out.println("You have not provided credentials");
						}
		        	  else{
		        		  String [] params1 = new String[20] ;
							params1[0] = tno;
							params1[1] = aid;
							params1[2] = apass;
		        	  
		        		  
		        	  
		        	 Networkclass netclass = new Networkclass();  
		        	 netclass.execute(params1);
		               
		        	  try{
		        		  
		        		  StringBuffer str = netclass.get();
							String str1 = str.toString();
							System.out.println(str);
							Intent intent = new Intent(v1.getContext(), AdminloginActivity.class);
							Bundle b = new Bundle();
							b.putString("message",str1);
							b.putString("sessionid", aid);
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
						}catch (Exception e){
							e.printStackTrace();
						}
						
                         
						
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