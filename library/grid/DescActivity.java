package com.vkr.smartlib;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class DescActivity extends Activity {

	Button popup,addtocart;
	RatingBar rb;
	TextView tv,tvbn,tvd,tvisbn;
	int img;
	String name,desc,isbn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
      Bundle b=new Bundle();
      b=getIntent().getExtras();
      img=b.getInt("img");
      desc=b.getString("desc");
      name=b.getString("name");
      isbn=b.getString("isbn");
      //pass the integer value from previous activity
        ImageView imgv= (ImageView) findViewById (R.id.imde1);
        imgv.setBackgroundResource(img);//need to store in  an array
        
        //Setting the description for the book
        tvd=(TextView) findViewById(R.id.tvde1);
        tvd.setText(desc);
        tvbn=(TextView) findViewById(R.id.tvbndesc);
        tvbn.setText(name);
        tvisbn=(TextView) findViewById(R.id.tvisbndesc);
        tvisbn.setText(isbn);
        popup=(Button) findViewById (R.id.btnde1);
        rb=(RatingBar) findViewById (R.id.rbde);
        addtocart=(Button) findViewById(R.id.btnde2);
        
        popup.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
		       spop();
			 
				
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
				
		       scart();
			 
				
			}
        });
        
        
    }
    
        
        public void spop(){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("Success!");
			borrow.setMessage("Do you want to add more books?");
			borrow.setIcon(R.drawable.arrow);
			borrow.setPositiveButton("No", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
					
				}
			});
			borrow.setNegativeButton("YES",new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent in=new Intent();
					in.setClass(getBaseContext(),SearchbookActivity.class);
					startActivity(in);
				}
			});
			borrow.setNeutralButton("Search the Web", new DialogInterface.OnClickListener()	{
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					sweb(); 
							}
			});
			  AlertDialog borrower = borrow.create();
			  borrower.show();
			  
        
    }
        public void scart(){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("Successfully Added To cart");
			borrow.setMessage("There are a lot more books out there!!");
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
public void sweb()
{
	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
	
	String[] websites=new String[]{"Ebay","Amazon","Google"};
    borrow.setTitle("Please choose one of the following:");
    borrow.setItems(websites, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
if(which==0){
	            try {
					String src=URLEncoder.encode(name,"utf-8");
				
			    String ebay="http://www.ebay.com/search?q="+src;
			    Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse(ebay));
			    startActivity(in);
	            } catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
}
if(which==1){
	            try {
					String src=URLEncoder.encode(name,"utf-8");
				

			    String amazon="http://www.amazon.com/search?q="+src;
			    Intent in=new Intent(Intent.ACTION_VIEW,Uri.parse(amazon));
			    startActivity(in);
	            } catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
if(which==2)
{
	Intent in=new Intent(Intent.ACTION_WEB_SEARCH);
    in.putExtra(SearchManager.QUERY, name+"book");
    startActivity(in);
}
			
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

