package com.vkr.smartlib;

import java.util.concurrent.ExecutionException;

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
	String[] params = new String[5];
	String ano = null;
	String message = null;
	int img;
	String name,desc;
	Button popup,addtocart;
	RatingBar rb;
	TextView tv,tvd;
	String subs = null;
	String sessionid = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
      Bundle b=new Bundle();
      String [] booknameAndIsbn = new String[2];       
      
      b=getIntent().getExtras();
      img=b.getInt("img");
      desc=b.getString("desc");
      name=b.getString("name");
      String bname_isbn = b.getString("isbn");
      sessionid = b.getString("sessionid");
      System.out.println(bname_isbn);
      subs = bname_isbn.substring(0, (bname_isbn.length()-1));
      System.out.println(subs);
      /*booknameAndIsbn = subs.split("\\(");
      final String bookname = booknameAndIsbn[0];
      final String isbn = booknameAndIsbn[1];*/
      
     
      
      //pass the integer value from previous activity
      ImageView imgv= (ImageView) findViewById (R.id.imde1);
      imgv.setBackgroundResource(img);
      
      tvd=(TextView) findViewById(R.id.tvde1);
      tvd.setText(desc);
      
      rb=(RatingBar) findViewById (R.id.rbde);
      addtocart=(Button) findViewById(R.id.btnde2);
        
       popup=(Button) findViewById (R.id.btnde1);
        
        
        popup.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ano = "8";
				try{
				Parameters[0] = name;
				Parameters[1] = sessionid;
				Parameters[2] = subs;
				Parameters[3] = ano;
				BrrowTask btask = new BrrowTask(DescActivity.this);
				btask.execute(Parameters);
				String result = btask.get();
				if(result.contains("Sorry"))
				{
					System.out.println("wpopup");
					wpop(result);
				}
				if(result == "")
				{
					message = "exception occurred. Please contact admin";
					npop(message);
				}
				if(!(result.contains("Sorry")) && result != "")
				{
					System.out.println("spopup");
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
        
        rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

			public void onRatingChanged(RatingBar rb1, float rating, boolean fromuser) {
				// TODO Auto-generated method stub
				tv=(TextView) findViewById (R.id.rbtv);
				String r=String.valueOf(rating);
				 tv.setText(r);
			
			}
        	
        });
        
        addtocart.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			   CartTask cart = new CartTask(DescActivity.this);
			   ano = null;
			   ano = "13";
			   params[0] = ano;
			   params[1] = sessionid;
			   params[2] = subs;
			   cart.execute(params);
		       
		       try
		       {
		    	   String result = cart.get();
		    	   if(result.equals("Y"))
		    	   {
		    		   System.out.println("inside cart");
		    		   scart();
		    	   }
		       }catch(Exception e)
		       {
		    	   e.printStackTrace();
		       }
			 
				
			}
        });
    }
        
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
        public void wpop(String message){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("waitlist");
			borrow.setIcon(R.drawable.arrow);
			borrow.setMessage(message);
			borrow.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
					System.out.println("inside popup waitlist");
					ano = null;
					ano = "9";
					params[0] = name;
					params[1] = sessionid;
					params[2] = subs;
					params[3] = ano;
					waitTask wTask= new waitTask(DescActivity.this);
					wTask.execute(params);
					try {
						String result = wTask.get();
						if (result != null)
						{
							System.out.println("inside popup waitlist if");
							spop(result);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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

        
        public void scart(){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("Successfully Added To cart");
			borrow.setMessage("What do you want to do?");
			borrow.setIcon(R.drawable.android);
			borrow.setPositiveButton("View cart", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					CartReadTask cartRead = new CartReadTask(DescActivity.this);
					ano = "14";
					params[0] = ano;
					params[1] = sessionid;
					cartRead.execute(params);
					try
					{
						String carts = cartRead.get();
					
					Intent in=new Intent();
					in.setClass(getBaseContext(),CartActivity.class);
					Bundle b=new Bundle();
					b.putString("cartitems", carts);
					//b.putInt("img", img);
					in.putExtras(b);
					startActivity(in);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
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
