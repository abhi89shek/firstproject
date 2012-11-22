package com.example.textscheduler;



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
import android.widget.PopupWindow;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class LoginpageActivity extends Activity {
	private Button signIn, signUp;
	private EditText username,usname,password,fullname , email , npassword,con_password; 
	private String userId = null;
	private String pass = null;
	private String tno = null;	
	private String fullName = null;
	private String emailid = null;	
	private String newpass = null;
	private String confirmPass = null;	
	private String username1 = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
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
        
        username=(EditText) findViewById (R.id.editText1);           
	       password=(EditText) findViewById  (R.id.editText2);			
	        signIn=(Button) findViewById (R.id.button1);  
	          
	         
	        
	      
	              //event handler for click on the signin button
	        signIn.setOnClickListener(new OnClickListener(){         
						public void onClick(View v1) {
						// TODO Auto-generated method stub
							//Toast.makeText(this, "hello", 1000).show();
							userId=username.getText().toString();
					         pass=password.getText().toString();
							System.out.println("button");
							tno = "1";   // a unique identifier to differentiate the tabs on the main screen
							//check if the user has entered the credentials.display error if not.
						if(userId.equals("") || pass.equals(""))
						{
							Toast.makeText(getBaseContext(), "oops!you have not provided credentials", 10).show();
						}
						else 
						{
							
							String [] params = new String[20] ;
							params[0] = tno;
							params[1] = userId;
							params[2] = pass;
							Httpuser httpuser = new Httpuser(LoginpageActivity.this); // a new thread is created for 
																		// making the http connection to server
							//StringBuffer responseString = new StringBuffer("");
							httpuser.execute(params);
							  try {
								StringBuffer str = httpuser.get();
								String str1 = str.toString();
								//check if the user has provided the valid credentials
								if(str1.equals("N"))
								{
									Toast.makeText(getBaseContext(), "Invalid username/password", 1000000000).show();
								}
								else
								{
								//if credentials are correct a new activity with a welcome page is created
								Intent intent = new Intent(v1.getContext(), SelectionActivity.class);
								Bundle b = new Bundle();
								b.putString("message",str1);
								b.putString("sessionid", userId);
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
	        
	        fullname = (EditText) findViewById(R.id.editText3);	       	        
	        usname =  (EditText) findViewById(R.id.username);
	        npassword = (EditText) findViewById(R.id.editText6);
	        con_password = (EditText) findViewById(R.id.editText8);
	        signUp = (Button) findViewById(R.id.button3);
	        PopupWindow popup;
	        TextView tv;
	        Button bt;
	        
	        
	        //event handler for the sign up button
	        signUp.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		
	        	 fullName = fullname.getText().toString();
	        	 	        	 
	        	 userId = usname.getText().toString();	        	 
	        	 newpass = npassword.getText().toString();
	        	 confirmPass = con_password.getText().toString();
	        	 
	        	  tno = "2";
	        	  
	        	  if (!(newpass.equals(confirmPass)))  // check if password and confirm password matches
	        		  
	        	  {
	        		  Toast.makeText(getBaseContext(), "password and confirm password does not match", 10).show();
	        	  }
	        	  //check if all the fields are entered
	        	  if(fullName.equals("") || newpass.equals("")||confirmPass.equals(""))
					{
						Toast.makeText(getBaseContext(), "You have not entered a mandatory value", 10).show();
					}
	        	  else{
	        		  String [] params2 = new String[20] ;
	        		    params2[0] = tno;
						params2[1] = fullName;
						params2[2] = userId;					
						params2[3] = newpass;
						//params2[7] = cpass;
						
	        	  
	        		  
	        	  //call async task to make http connection to the server
	        	 HttpNewUser netclass = new HttpNewUser(LoginpageActivity.this);  
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
        getMenuInflater().inflate(R.menu.activity_loginpage, menu);
        return true;
    }
}
