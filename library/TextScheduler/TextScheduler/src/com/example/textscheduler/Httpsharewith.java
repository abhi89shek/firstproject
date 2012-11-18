package com.example.textscheduler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



public class Httpsharewith extends  AsyncTask<String[], Void, StringBuffer >{
	
	private Context context;
	private ProgressDialog dialog;
	
	public Httpsharewith(Context c)
	{
		super();
		context = c;
	}
	
	
	protected void onPreExecute()
	{
		dialog = new ProgressDialog(context); // App - your main activity class
        dialog.setMessage("validating ...Please, wait...");
        dialog.show();
	}
	
    protected StringBuffer doInBackground(String[]... params) {
    	
    	String [] temp = new String[20] ;
    	temp = params[0];              // get the parameters from the main thread
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("activityNo", temp[0])); //parameters are added to an arraylist
		nameValuePairs.add(new BasicNameValuePair("myUserName", temp[1]));
		nameValuePairs.add(new BasicNameValuePair("contactName", temp[2]));
		nameValuePairs.add(new BasicNameValuePair("Sharewith", temp[3]));
		nameValuePairs.add(new BasicNameValuePair("date", temp[4]));
		nameValuePairs.add(new BasicNameValuePair("seenFlag", temp[5]));

		
		
    	try {
	        HttpClient httpClient = new DefaultHttpClient();	        
	        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/WebappText/Controller");//the webserver address goes in here	        
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));	        
	        HttpResponse response = httpClient.execute(httpPost);	 //connect to the server via httppost       
	        HttpEntity entity = response.getEntity();	//get the response from the server        
	        InputStream is = entity.getContent();  	   // store it as an inputstream      
	        BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
	        StringBuffer responseString = new StringBuffer("");
            String line;
            while ((line = reader.readLine()) != null) {
                responseString.append(line);              //append the result to string buffer
	       // System.out.println(responseString);
            }
	        
	        //retureturn rn responseString;
            return responseString;
            	
	        
	    }catch (Exception e) {
	        // TODO: handle exception
	    	e.printStackTrace();
	    	return null;
	    	//return null;
	                	    }
    	
    	   		
    		
    	}
    protected void onPostExecute(StringBuffer result) {
    	
    	dialog.dismiss();
    	 returnValue(result);     //return the result to the main thread
    	 
    }
		
    protected StringBuffer returnValue(StringBuffer strb) {
    	
    	return strb;
	}

	

}

