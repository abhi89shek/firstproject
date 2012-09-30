package com.oee;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.oee.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.View;


public class EnterDetails extends Activity {
	private EditText txtShiftID;
	private EditText txtTotalPieces;
	private EditText txtRejectedPieces;
	private EditText txtMealBreak;
	private EditText txtShortBreak;
	private EditText txtDownTime;
	private EditText txtDate;


	private Button submit;
	private Button EnterEmpDetails;
	private Button Reset;
	private TextView txtResponse;


	private String shiftID;
	private String totalPieces;
	private String rejectedPieces;
	private String mealBreak;
	private String shortBreak;
	private String downTime;
	private String date;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.i ("info", "inside ocreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main5);
		initControls();
		submit = (Button)findViewById(R.id.submit);
		submit.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ enterDetails(); }});
		EnterEmpDetails = (Button)findViewById(R.id.EnterEmpDetails);
		EnterEmpDetails.setOnClickListener(new Button.OnClickListener() { public void onClick (View v)
		{
			Intent myIntent = new Intent(v.getContext(), EnterEmpDetails.class);
			startActivityForResult(myIntent, 0);
		}});
		Reset=(Button)findViewById(R.id.reset);
		Reset.setOnClickListener(new Button.OnClickListener() { public void onClick (View v){ reset(); }});

	}
	private void initControls()
	{
		Log.i ("info", "inside init");
		txtShiftID=(EditText)findViewById(R.id.txtShiftID);
		txtTotalPieces=(EditText)findViewById(R.id.txtTotalPieces);;
		txtRejectedPieces= (EditText)findViewById(R.id.txtRejectedPieces);;
		txtMealBreak= (EditText)findViewById(R.id.txtMealBreak);;
		txtShortBreak= (EditText)findViewById(R.id.txtShortBreak);;
		txtDownTime=(EditText)findViewById(R.id.txtDownTime);;
		txtDate=(EditText)findViewById(R.id.txtDate);
		txtResponse=(TextView)findViewById(R.id.resposemessage);

	}
	private void enterDetails()
	{
		Log.i ("info", "inside eter details");
		shiftID = txtShiftID.getText().toString();
		Log.i ("info", shiftID);
		mealBreak = txtMealBreak.getText().toString();
		Log.i ("info", mealBreak);
		shortBreak = txtShortBreak.getText().toString();
		Log.i ("info", shortBreak);
		totalPieces = txtTotalPieces.getText().toString();
		Log.i ("info", totalPieces);
		rejectedPieces = txtRejectedPieces.getText().toString();
		Log.i ("info", rejectedPieces);
		downTime = txtDownTime.getText().toString();
		Log.i ("info", downTime);
		date = txtDate.getText().toString();
		Log.i ("info", date);

		HttpClient httpclient = new DefaultHttpClient();
		String responseBody=null;

		HttpGet httpget = new HttpGet("http://10.0.2.2:8080/WebApplication6/NewServlet1?date="+date+"&shiftid="+shiftID+"&mealbreak="+mealBreak+"&shortBreak="+shortBreak+"&totalPieces="+totalPieces+"&rejectedPieces="+rejectedPieces+"&downTime="+downTime);
		// Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();       
		try {			
			responseBody=httpclient.execute(httpget,responseHandler);
			responseBody.trim();
			txtResponse.setText(responseBody);
			


		} catch (ClientProtocolException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();
	}

	private void reset()
	{

		txtShiftID.setText("");
		txtTotalPieces.setText("");
		txtRejectedPieces.setText("");
		txtMealBreak.setText("");
		txtShortBreak.setText("");
		txtDownTime.setText("");
		txtDate.setText("");
		txtResponse.setText("");
	}
}
