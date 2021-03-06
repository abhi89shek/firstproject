package com.vkr.smartlib;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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



public class Networkclass extends  AsyncTask<String[], Void, StringBuffer >{
	
	private Context context;
	private ProgressDialog dialog;
	
	public Networkclass(Context c)
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
		nameValuePairs.add(new BasicNameValuePair("tno", temp[0])); //parameters are added to an arraylist
		nameValuePairs.add(new BasicNameValuePair("id", temp[1]));
		nameValuePairs.add(new BasicNameValuePair("pass", temp[2])); 
    	try {
	        HttpClient httpClient = new DefaultHttpClient();	        
	        HttpPost httpPost = new HttpPost("http://192.168.50.1:8080/webapp/Controllerservlet");//the webserver address goes in here	
	        Log.i("tag","i should be here1");
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        Log.i("tag","i should be here2");
	        HttpResponse response = httpClient.execute(httpPost);
	        Log.i("tag","i should be here3");//connect to the server via httppost       
	        HttpEntity entity = response.getEntity();
	        Log.i("tag","i should be here4");//get the response from the server        
	        InputStream is = entity.getContent();  
	        Log.i("tag","i should be here5");// store it as an inputstream      
	        BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
	        Log.i("tag","i should be here6");
	        StringBuffer responseString = new StringBuffer("");
	        Log.i("tag","i should be here7");
            String line;
            Log.i("tag","i should be here8");
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
    	Log.i("tag","i should be here9");
    	dialog.dismiss();
    	 returnValue(result);     //return the result to the main thread
    	 
    }
		
    protected StringBuffer returnValue(StringBuffer strb) {
    	Log.i("tag","i should be here10");
    	return strb;
	}

	

}
