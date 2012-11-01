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

public class CartReadTask extends AsyncTask<String[], Void, String >{
	
	private Context context;
	private ProgressDialog dialog;
	
	public CartReadTask(Context c)
	{
		super();
		context = c;
	}
	
	
	protected void onPreExecute()
	{
		dialog = new ProgressDialog(context); // App - your main activity class
        dialog.setMessage("retrieving cart ...Please, wait...");
        dialog.show();
	}
	
	protected String doInBackground(String[]... params) {
		// TODO Auto-generated method stub
		
		
		String temp[] = new String[20];
		ArrayList<String> list =  new ArrayList<String>();
		 temp = params[0];
		 
		 ArrayList<NameValuePair> alist = new ArrayList<NameValuePair>();
		 alist.add(new BasicNameValuePair("tno",temp[0]));
		 alist.add(new BasicNameValuePair("sessionid",temp[1]));
		 
		 
		 try
		 {
			 HttpClient httpClient = new DefaultHttpClient();
			 
			 HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/webapp/Controllerservlet");
			 
			 httpPost.setEntity(new UrlEncodedFormEntity(alist));
		        //execute a http call to the server
		     HttpResponse response = httpClient.execute(httpPost);
		        //store the response
		     HttpEntity entity = response.getEntity();
		     
		     InputStream is = entity.getContent();  
		        //convert the input stream to UTF-8 format
		        BufferedReader reader = new BufferedReader(
	                    new InputStreamReader(is, "UTF-8"));
		        StringBuffer responseString = new StringBuffer("");
	            String line;
	            while ((line = reader.readLine()) != null) {
	                responseString.append(line);              //append the result to string buffer
		       // System.out.println(responseString);
	            }
		        
		        String resp = responseString.toString();
	            return resp;
	            	
			 
		 } catch(Exception e)
		 {
			 e.printStackTrace();
		    	return null;
		 }
		 
		 
		 
		 
		
	}
	 protected void onPostExecute(String result) {
		 
		      dialog.dismiss();
		  	 returnValue(result);
		    	 
		    }	
    	 
    	 
   
		
    protected String returnValue(String strb) {
    	
    	return strb;
	}


}
