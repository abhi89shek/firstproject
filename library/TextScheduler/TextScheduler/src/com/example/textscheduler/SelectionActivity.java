package com.example.textscheduler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectionActivity extends Activity implements OnClickListener{

	Button buttonNewSchedule;
	Button buttonViewNotifications;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        
        buttonNewSchedule = (Button) findViewById(R.id.button_new_schedule);
        buttonViewNotifications = (Button) findViewById(R.id.button_view_notifications);
        
        buttonNewSchedule.setOnClickListener(this);
        buttonViewNotifications.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_selection, menu);
        return true;
    }  
    
    public void onClick(View v) {
    		switch (v.getId()) {
    			case R.id.button_new_schedule:
    				
    				Intent myIntent = new Intent(SelectionActivity.this,
							NewScheduleActivity.class);
					SelectionActivity.this.startActivity(myIntent);
					finish();   
					
    			case R.id.button_view_notifications:
    				
    	}
    }
}