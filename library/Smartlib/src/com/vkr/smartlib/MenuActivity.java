package com.vkr.smartlib;

import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends Activity implements SensorEventListener {

	private String ano = null;
	private String []params1 = new String[5];
	private String []params3 = new String[5];
	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private final float NOISE = (float) 2.0;
    private String books = null;
    private String sessionid = null;
	 
    /** Called when the activity is first created. */
   
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mInitialized = false;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
     // get the values from the calling activity via an intent
        String tno = "7";
        Log.i("tag","i should be here15");
        
        Bundle extras = getIntent().getExtras();   
            String [] params= new String[3]; 
            String message = extras.getString("message"); //get the message from the calling activity
            //store the userid in a session variable
             sessionid = extras.getString("sessionid");  
            //System.out.println(sessionid);
            TextView mtext = (TextView)findViewById(R.id.tv3);
            mtext.setText(message);
            Alerttask alert = new Alerttask();
            params[0] = tno;
            params[1] = sessionid;
            alert.execute(params);
            
            try {
            	
				StringBuffer alertmessage = alert.get();
				
				String str = alertmessage.toString();
				
				if(str != "")
				{
				AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
				alertbox.setMessage(str);

		        // add a neutral button to the alert box and assign a click listener
		        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

		            // click listener on the alert box
		            public void onClick(DialogInterface arg0, int arg1) {
		                // the button was clicked
		            	
		            	

		            }
		        });

		        // show it
		        alertbox.show();
				}
				else{}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Button searchbutton = (Button)findViewById(R.id.butt1);
            // event handler for search button
            searchbutton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		// onclick pass the intent to the next activity
	        		Intent intent = new Intent(v1.getContext(),SearchbookActivity.class);
	        		Bundle b = new Bundle();
					b.putString("sessionid",sessionid);					
					intent.putExtras(b);
	        		startActivity(intent);
	        		
	        		
	        		
	        	}
        
    }
            
            );
            Button studyButton = (Button)findViewById(R.id.butt2);
            
            studyButton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		// onclick pass the intent to the next activity
	        		Intent intent = new Intent(v1.getContext(),Studyroom.class);
	        		Bundle b = new Bundle();
					b.putString("sessionid",sessionid);					
					intent.putExtras(b);
	        		startActivity(intent);
	        		
	        		
	        		
	        	}
        
    }
            
            );
            Button cartButton = (Button)findViewById(R.id.cartbutton);
            cartButton.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v1) {
	        		// onclick pass the intent to the next activity
	        		CartReadTask cartRead = new CartReadTask(MenuActivity.this);
					ano = "14";
					params1[0] = ano;
					params1[1] = sessionid;
					cartRead.execute(params1);
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
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	String contents = null;
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode == 0) {
    	if (resultCode == RESULT_OK) {
    	contents = data.getStringExtra("SCAN_RESULT");
    	Httpscanbook scanbook = new Httpscanbook(MenuActivity.this);
    	ano = "17";
		params3[0] = ano;
		params3[1] = contents;
		scanbook.execute(params3);
		try
		{
			String books = scanbook.get();
		
		Intent in=new Intent();
		in.setClass(getBaseContext(),SearchresultActivity.class);
		Bundle b=new Bundle();
		b.putString("books", books);
		b.putString("sessionid", sessionid);
		//b.putInt("img", img);
		in.putExtras(b);
		startActivity(in);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    	String format = data.getStringExtra("SCAN_RESULT_FORMAT");
        TextView myView = (TextView) findViewById(R.id.scanbook);
        myView.setText(contents);
    	// Handle successful scan
    	} else if (resultCode == RESULT_CANCELED) {
    	// Handle cancel
    	}
    	}

    	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
    
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	//code to detect the shake event
     public void onSensorChanged(SensorEvent event) {   
        /* TextView tvX= (TextView)findViewById(R.id.x_axis);
         TextView tvY= (TextView)findViewById(R.id.y_axis);
         TextView tvZ= (TextView)findViewById(R.id.z_axis);
          ImageView iv = (ImageView)findViewById(R.id.image);*/
         float x = event.values[0];
         float y = event.values[1];
         float z = event.values[2];
         if (!mInitialized) {
              mLastX = x;
             mLastY = y;
             mLastZ = z;
             /*tvX.setText("0.0");
             tvY.setText("0.0");
             tvZ.setText("0.0");*/
              mInitialized = true;
         } else {
             float deltaX = Math.abs(mLastX - x);
             float deltaY = Math.abs(mLastY - y);
             float deltaZ = Math.abs(mLastZ - z);
              if (deltaX < NOISE) deltaX = (float)0.0;
             if (deltaY < NOISE) deltaY = (float)0.0;
             if (deltaZ < NOISE) deltaZ = (float)0.0;
             mLastX = x;
             mLastY = y;
              mLastZ = z;
             /*tvX.setText(Float.toString(deltaX));
             tvY.setText(Float.toString(deltaY));
             tvZ.setText(Float.toString(deltaZ));
             iv.setVisibility(View.VISIBLE);*/
              //if the phone is shaked towards right ,call the Zxing barcode app to scan the barcode
              if (deltaX > deltaY) {
                 
                 Intent data = new Intent("com.google.zxing.client.android.SCAN");
                 data.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
                  startActivityForResult(data, 0);
                 
                 
             }
                 
                 //iv.setImageResource(R.drawable.horizontal);
              /*else if (deltaY > deltaX) {
                  iv.setImageResource(R.drawable.vertical);
             } else {
                 iv.setVisibility(View.INVISIBLE);
             }*/
             
             
             
          }
         
         
     }
}
