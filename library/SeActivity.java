package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se);
        Button popup=(Button) findViewById (R.id.btnse1);
        
        
        popup.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					
				spop();
				
	        
				
			}
        	
        	
        });
    }
        
        public void spop(){
        	AlertDialog.Builder borrow= new AlertDialog.Builder(this);
			borrow.setTitle("Success!");
			borrow.setMessage("Do you want to add more books?");
			borrow.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent in=new Intent();
					in.setClass(getBaseContext(),SearchbookActivity.class);
					startActivity(in);
					
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_se, menu);
        return true;
    }
}
