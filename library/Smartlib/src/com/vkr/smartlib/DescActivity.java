package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class DescActivity extends Activity  {

	Button borrow;
	//EditText bor;
	String[] Parameters = new String[5];
	String ano = "8";
	String message = null;
	int img;
	String name,desc;
	Button popup,addtocart;
	RatingBar rb;
	TextView tv,tvd;
	String subs = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
      Bundle b=new Bundle();
      String [] booknameAndIsbn = new String[2];       
      
     
      // getting data from previous activity
      b=getIntent().getExtras();
      img=b.getInt("img");
      desc=b.getString("desc");
      name=b.getString("name");
      String bname_isbn = b.getString("isbn");
      final String sessionid = b.getString("sessionid");
      System.out.println(bname_isbn);
      subs = bname_isbn.substring(0, (bname_isbn.length()-1));
      System.out.println(subs);
     
     
      
      //pass the integer value from previous activity
      ImageView imgv= (ImageView) findViewById (R.id.imde1);
      imgv.setBackgroundResource(img);
      
      tvd=(TextView) findViewById(R.id.tvde1);
      tvd.setText(desc);
      
      rb=(RatingBar) findViewById (R.id.rbde);
      addtocart=(Button) findViewById(R.id.btnde2);
        
       popup=(Button) findViewById (R.id.btnde1);
        
        // Borrow the book
        popup.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
				Parameters[0] = name;
				Parameters[1] = sessionid;
				Parameters[2] = subs;
				Parameters[3] = ano;
				BrrowTask btask = new BrrowTask(DescActivity.this);
				btask.execute(Parameters);
				String result = btask.get();
				if(result == "")
				{
					message = "exception occurred. Please contact admin";
					npop(message);
				}
				else
				{
					message = "Thank you for borrowing.The due date for the book is "+result+". ";
					spop(message);
				}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			 
				//spop();
				
	        
				
			}
        	
        	
        });
        
        
        // Rating the books
        rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

			public void onRatingChanged(RatingBar rb1, float rating, boolean fromuser) {
				// TODO Auto-generated method stub
				tv=(TextView) findViewById (R.id.rbtv);
				String r=String.valueOf(rating);
				 tv.setText(r);
			
			}
        	
        });
        
        
        // Calling the cart function
        addtocart.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		       scart();
			 
				
			}
        });
    }
        // Alert box to add books into the cart
        public void spop(String message){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("Success!");
			borrow.setIcon(R.drawable.arrow);
			borrow.setMessage(message +" Do you want to add more books?");
			borrow.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getBaseContext(), SearchbookActivity.class);
					//Intent in=new Intent();
					//in.setClass(getBaseContext(),SearchbookActivity.class);
					startActivityForResult(intent,0);
					
				}
			});
			borrow.setNegativeButton("No",new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				}
			});
			AlertDialog helpDialog = borrow.create();
			  helpDialog.show();
        
        
    }
        
        public void npop(String message){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("error!");
			
			borrow.setMessage(message);
			borrow.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					
				}
			});
			
			AlertDialog helpDialog = borrow.create();
			  helpDialog.show();
        
        
    }

        // Adding books to the cart
        public void scart(){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("Successfully Added To cart");
			borrow.setMessage("What do you want to do?");
			borrow.setIcon(R.drawable.android);
			borrow.setPositiveButton("View cart", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent in=new Intent();
					in.setClass(getBaseContext(),CartActivity.class);
					Bundle b=new Bundle();
					b.putString("name", name);
					b.putInt("img", img);
					in.putExtras(b);
					startActivity(in);
					
				}
			});
			borrow.setNegativeButton("Add more books",new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent in=new Intent();
					in.setClass(getBaseContext(),SearchbookActivity.class);
					startActivity(in);
				}
			});
			AlertDialog borrower = borrow.create();
			  borrower.show();
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_desc, menu);
        return true;
    }
}
