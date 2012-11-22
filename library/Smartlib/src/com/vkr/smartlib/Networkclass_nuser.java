package com.vkr.smartlib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class Networkclass_nuser extends  AsyncTask<String[], Void, StringBuffer > {

	
	private Context context;
	private ProgressDialog dialog;
	
	public Networkclass_nuser(Context c)
	{
		super();
		context = c;
	}
	
	
	protected void onPreExecute()
	{
		dialog = new ProgressDialog(context); // App - your main activity class
        dialog.setMessage("Updating details ...Please, wait...");
        dialog.show();
	}
	 protected StringBuffer doInBackground(String[]... params) {
	    	
	    	String [] temp = new String[20] ;
	    	temp = params[0];			// get the parameters from the main thread
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    	nameValuePairs.add(new BasicNameValuePair("tno", temp[0]));	//parameters are added to an arraylist
			nameValuePairs.add(new BasicNameValuePair("fullname", temp[1]));
			nameValuePairs.add(new BasicNameValuePair("email", temp[2]));
			nameValuePairs.add(new BasicNameValuePair("address", temp[3]));
			nameValuePairs.add(new BasicNameValuePair("phno", temp[4]));
			nameValuePairs.add(new BasicNameValuePair("username", temp[5]));
			nameValuePairs.add(new BasicNameValuePair("password", temp[6]));
	    	try {
		        HttpClient httpClient = new DefaultHttpClient();
		        System.out.println("button3");
		        HttpPost httpPost = new HttpPost("http://192.168.50.1:8080/webapp/Controllerservlet"); //the webserver address goes in here
		        System.out.println("button4");//the webserver address goes in here
		        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        System.out.println("button5");
		        HttpResponse response = httpClient.execute(httpPost);	//connect to the server via httppost
		        System.out.println("button6");
		        HttpEntity entity = response.getEntity();				//get the response from the server 
		        System.out.println("button7");
		        InputStream is = entity.getContent();  					// store it as an inputstream 
		        System.out.println("button8");
		        BufferedReader reader = new BufferedReader(
	                    new InputStreamReader(is, "UTF-8"));
		        StringBuffer responseString = new StringBuffer("");
	            String line;
	            while ((line = reader.readLine()) != null) {
	                responseString.append(line);						//append the result to string buffer
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
	    	 returnValue(result);
	    	 
	    }
			
	    protected StringBuffer returnValue(StringBuffer strb) {
	    	
	    	return strb;
		}

		

	}	
	
	

