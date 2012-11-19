package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
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
	
	    Button signin,signinadmin, signup;
		EditText username,usname,password,adminId,adminpass, fname , email , phone, address, npassword,con_password; 
		String uid = null;
		String pass = null;
		String tno = null;
		String aid = null;
		String apass = null;
		String fnam = null;
		String e_mail = null;
		String phone_num = null;
		String npass = null;
		String cpass = null;
		String add = null;
		String usname1 = null;
		
		
	   
	      

	    @Override
	    public void onCreate(Bundle savedInstanceState) {              //this is the first method to be loaded when the 
	        super.onCreate(savedInstanceState);                        // app runs.This shows the main screen
	        setContentView(R.layout.activity_userlogin);
	                TabHost th= (TabHost) findViewById (R.id.tabhost);
	                th.setup();
	                TabSpec spec1=th.newTabSpec("t1");					//3 tabs are created.one for new user,existing user and an admin
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
	                Log.i("tag","i should be here 1st");

	        username=(EditText) findViewById (R.id.editText1);           //getting the textbox values entered by the user on the mai page
	       password=(EditText) findViewById  (R.id.editText2);			// id attribute is defined in the xml file
	        signin=(Button) findViewById (R.id.button1);  
	          
	         
	        
	      
	              //event handler for click on the signin button
	           signin.setOnClickListener(new OnClickListener(){         
						public void onClick(View v1) {
						// TODO Auto-generated method stub
							//Toast.makeText(this, "hello", 1000).show();
							uid=username.getText().toString();
					         pass=password.getText().toString();
							//System.out.println("button");
							tno = "1";   // a unique identifier to differentiate the tabs on the main screen
							//check if the user has entered the credentials.display error if not.
							 Log.i("tag","i should be here 2nd");
						if(uid.equals("") || pass.equals(""))
						{
							Toast.makeText(getBaseContext(), "oops!you have not provided credentials", 1000000).show();
						}
						else 
						{
							
							String [] params = new String[20] ;
							params[0] = tno;
							params[1] = uid;
							params[2] = pass;
							Networkclass netclass = new Networkclass(Userlogin.this);
							 Log.i("tag","i should be here middle");// a new thread is created for 
																		// making the http connection to server
							//StringBuffer responseString = new StringBuffer("");
							  netclass.execute(params);
							  Log.i("tag","i should be here 3rd");
							  try {
								StringBuffer str = netclass.get();
								 Log.i("tag","i should be here 4th");
								String str1 = str.toString();
								 Log.i("tag","i should be here 5th");
								//check if the user has provided the valid credentials
								if(str1.equals("N"))
								{
									Toast.makeText(getBaseContext(), "Invalid username/password", 1000000000).show();
								}
								else
								{
								//if credentials are correct a new activity with a welcome page is created
								Intent intent = new Intent(v1.getContext(), MenuActivity.class);
								Bundle b = new Bundle();
								b.putString("message",str1);
								b.putString("sessionid", uid);
								intent.putExtras(b);
								//intent.putExtra("message", str1);
								//intent.putExtra("sesionid", uid);
								startActivityForResult(intent,0);
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
							
					}
	        		
	                                 	            }
	            );
	           
	           //this credentials are for the admin
	           //get the credentials entered by the admin.(from an xml file)
	           adminId=(EditText) findViewById (R.id.editText10);
		       adminpass=(EditText) findViewById  (R.id.editText11);
		        signinadmin=(Button) findViewById (R.id.button4); 
            
	           
		        //event handler for sign in button for admin
		        signinadmin.setOnClickListener(new OnClickListener(){
		        	public void onClick(View v1) {
		        		
		        	 aid = adminId.getText().toString();
		        	 apass = adminpass.getText().toString();
		        	  tno = "2"; // a unique identifier to differentiate the tabs on the main screen
		        	//check if the admin has provided the valid credentials
		        	  if(aid.equals("") || apass.equals(""))
						{
		        		  Toast.makeText(getBaseContext(), "oops!you have not provided credentials", 10).show();
						}
		        	  else{
		        		  String [] params1 = new String[20] ;
							params1[0] = tno;
							params1[1] = aid;
							params1[2] = apass;
		        	  
		        		  
		        	  
			        	 Networkclass netclass = new Networkclass(Userlogin.this);  //async task for making http connections
			        	 netclass.execute(params1);
		               
		        	  try{
		        		  
		        		  StringBuffer str = netclass.get();
							String str1 = str.toString();
							//transfer control to the next activity(welcome page for admin)
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
		        
		        // get values from the user for new registration
		      
		        fname = (EditText) findViewById(R.id.editText3);
		        email = (EditText) findViewById(R.id.editText4);
		        address = (EditText) findViewById(R.id.editText5);
		        phone =  (EditText) findViewById(R.id.editText12);
		        usname =  (EditText) findViewById(R.id.username);
		        npassword = (EditText) findViewById(R.id.editText6);
		        con_password = (EditText) findViewById(R.id.editText8);
		        signup = (Button) findViewById(R.id.button3);
		        PopupWindow popup;
		        TextView tv;
		        Button bt;
		        
		        
		        //event handler for the sign up button
		        signup.setOnClickListener(new OnClickListener(){
		        	public void onClick(View v1) {
		        		
		        	 fnam = fname.getText().toString();
		        	 e_mail = email.getText().toString();
		        	 add = address.getText().toString();
		        	 usname1 = usname.getText().toString();
		        	 phone_num = phone.getText().toString();
		        	 npass = npassword.getText().toString();
		        	 cpass = con_password.getText().toString();
		        	 
		        	  tno = "3";
		        	  
		        	  if (!(npass.equals(cpass)))  // check if password and confirm password matches
		        		  
		        	  {
		        		  Toast.makeText(getBaseContext(), "password and confirm password does not match", 10).show();
		        	  }
		        	  //check if all the fields are entered
		        	  if(fnam.equals("") || e_mail.equals("") || phone_num.equals("")|| npass.equals("")||cpass.equals(""))
						{
							Toast.makeText(getBaseContext(), "You have not entered a mandatory value", 10).show();
						}
		        	  else{
		        		  String [] params2 = new String[20] ;
		        		    params2[0] = tno;
							params2[1] = fnam;
							params2[2] = e_mail;
							params2[3] = add;
							params2[4] = phone_num;
							params2[5] = usname1;
							params2[6] = npass;
							//params2[7] = cpass;
							
		        	  
		        		  
		        	  //call async task to make http connection to the server
		        	 Networkclass_nuser netclass = new Networkclass_nuser(Userlogin.this);  
		        	 netclass.execute(params2);
		               
		        	  try{
		        		  
		        		  StringBuffer str = netclass.get();
							String str1 = str.toString();
							
							//popup message displaying the userid and an acknowledgement message
							AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
							alertbox.setMessage(str1);

					        // add a neutral button to the alert box and assign a click listener
					        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

					            // click listener on the alert box
					            public void onClick(DialogInterface arg0, int arg1) {
					                // the button was clicked
					            	
					            	

					            }
					        });

					        // show it
					        alertbox.show();
							
							
							
							
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
	   


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_userlogin, menu);
    return true;
}
}