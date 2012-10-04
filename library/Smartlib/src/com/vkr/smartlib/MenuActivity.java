package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        Bundle extras = getIntent().getExtras();
       
            String message = extras.getString("message");
            String sessionid = extras.getString("sessionid");
            
            TextView mtext = (TextView)findViewById(R.id.tv3);
            mtext.setText(message);
            
            Button searchbutton = (Button)findViewById(R.id.butt1);
            
            searchbutton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		
	        		//Intent intent = new Intent(v1.getContext(),SearchBookActivity.class);
	        		startActivityForResult(intent,0);
	        		
	        		
	        		
	        	}
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
}
