package com.vkr.smartlib;

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
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class ManageActivity extends Activity {
	Button add,delete,update;
	EditText bname,bisbn,bauthor,bnoofcopies;
	String bookname=null;
	String bookisbn=null;
	String bookauthor=null;
	String bookcopies=null;
	String tno=null;
	
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
       
          bname=(EditText) findViewById (R.id.editText400);         
	      bisbn =(EditText) findViewById  (R.id.editText100);
	      bauthor =(EditText) findViewById (R.id.editText200);        
	      bnoofcopies =(EditText) findViewById  (R.id.editText300);	
	      add =(Button) findViewById (R.id.button100);  
	      
	      add.setOnClickListener(new OnClickListener(){

			public void onClick(View v1) {
				System.out.println("inside abhi xx");
				bookisbn=bisbn.getText().toString();
				bookauthor=bauthor.getText().toString();
				bookcopies=bnoofcopies.getText().toString();
				bookname=bname.getText().toString();
				tno="10";
				
				//System.out.println("book isbn is" + bookisbn);
            if(bookisbn.equals("") || bookauthor.equals("") || bookcopies.equals("") || bookname.equals(""))
            {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(),"You have not entered mandatory fields", 10);
			}
            else
            {
            	System.out.println("inside abhi yy");
            	String[] params = new String[20];
            	
            	params[0] = tno;
            	params[1] = bookisbn;
            	params[2] = bookauthor;
            	params[3] = bookcopies;
            	params[4] = bookname;
            	
            	/*for(int i =0; i<params.length;i++)
            	{
            		System.out.println("the contents are \n"+ params[i]);
            	}*/
            	
            	
            	//Networkclass_adminmag netclass = new Networkclass_adminmag();
            	TestClass ne = new TestClass();
            	System.out.println("IN BETWEEN");
            	try{
            		
            	ne.execute(params);
	        	//netclass.execute(params);
            	}catch(Exception e)
            	{
            		e.printStackTrace();
            	}
	        	System.out.println("OUTSIDE");
	        	
	        	  
	        	  try{
	        		  System.out.println("inside abhi zz");
	        		  
	        		  StringBuffer str = ne.get();
					  String str1 = str.toString();
					
						
						
					  AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
					  alertbox.setMessage(str1);
					  
					  alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
						  
						  public void onClick(DialogInterface arg0, int arg1) {
						     	
				            	

				            }
				        });

				       
				        alertbox.show();
												
										
					} catch (Exception e){
						e.printStackTrace();
					}
	        	 }
			  }
  		   }
        );
	       
	      
	      
	      delete =(Button) findViewById (R.id.button200);  
	      delete.setOnClickListener(new OnClickListener(){

			public void onClick(View v1) {
				bookisbn=bisbn.getText().toString();
				//bookauthor=bauthor.getText().toString();
				//bookcopies=bnoofcopies.getText().toString();
			//	bookname=bname.getText().toString();
				tno="11";
            if(bookisbn.equals(""))
            {
				// TODO Auto-generated method stub
				//Toast.makeText(getBaseContext(),"You have not entered mandatory fields", 10);
			}
            else
            {
            	
            	String[] params1 = new String[20];
            	
            	params1[0] = tno;
            	params1[1] = bookisbn;
            	//params1[2] = bookauthor;
            //	params1[3] = bookcopies;
            	//params1[4] = bookname;
            	
            	Networkclass_adminmag_del ne1 = new Networkclass_adminmag_del();
            	ne1.execute(params1);
           
	        	
	        	
	        	  
	        	  try{
	        		  
	        		  StringBuffer str = ne1.get();
					 
						
						
					  AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
					  alertbox.setMessage(str);
					  
					  alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
						  
						  public void onClick(DialogInterface arg0, int arg1) {
						     	
				            	

				            }
				        });

				       
				        alertbox.show();
												
										
					} catch (Exception e){
						e.printStackTrace();
					}
	        	 }
			  }
  		   }
        );
	      
	      update =(Button) findViewById (R.id.button300);  
	      update.setOnClickListener(new OnClickListener(){

			public void onClick(View v1) {
				bookisbn=bisbn.getText().toString();
				System.out.println(bookisbn);
			//	bookauthor=bauthor.getText().toString();
				bookcopies=bnoofcopies.getText().toString();
				System.out.println(bookcopies);
			//	bookname=bname.getText().toString();
				tno="12";
            if(bookisbn.equals("") || bookcopies.equals(""))
            {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(),"You have not entered mandatory fields", 10);
			}
            else
            {
            	String[] params2 = new String[20];
            	
            	params2[0] = tno;
            	params2[1] = bookisbn;
            	//params2[2] = bookauthor;
            	params2[2] = bookcopies;
            //	params2[4] = bookname;

            	Networkclass_adminmag_update ne2 = new Networkclass_adminmag_update ();
            	ne2.execute(params2);
            	
            	
	        	
	        	
	        	  
	        	  try{
	        		  
	        		  StringBuffer str = ne2.get();
					  String str1 = str.toString();
					 
						
						
					  AlertDialog.Builder alertbox = new AlertDialog.Builder(v1.getContext());
					  alertbox.setMessage(str1);
					  
					  alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
						  
						  public void onClick(DialogInterface arg0, int arg1) {
						     	
				            	

				            }
				        });

				       
				        alertbox.show();
												
										
					} catch (Exception e){
						e.printStackTrace();
					}
	        	 }
			  }
  		   }
        );
	        
  }
 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage, menu);
        return true;
    }
}
