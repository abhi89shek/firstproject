package com.vkr.smartlib;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ParseException;
import android.os.AsyncTask;
import android.widget.Toast;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class SearchBooktask extends  AsyncTask<String[], Void, String >{
	
	private Context context;
	private ProgressDialog dialog;
	
	public SearchBooktask(Context c)
	{
		super();
		context = c;
	}
	
	
	protected void onPreExecute()
	{
		dialog = new ProgressDialog(context); // App - your main activity class
        dialog.setMessage("Fetching books ...Please, wait...");
        dialog.show();
	}
	
    protected String doInBackground(String[]... params) {
    	
    	String [] temp = new String[20] ;
    	String result = null;
    	String bookname = null;
    	ArrayList<String> list =  new ArrayList<String>();
    	temp = params[0];		// get the parameters from the main thread
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("tno", temp[0]));
		nameValuePairs.add(new BasicNameValuePair("keyword", temp[1]));
		
				
    	try {
	        HttpClient httpClient = new DefaultHttpClient();
	        //URL to access the server
	        HttpPost httpPost = new HttpPost("http://192.168.50.1:8080/webapp/Controllerservlet");
	        //the webserver address goes in here
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        //execute a http call to the server
	        HttpResponse response = httpClient.execute(httpPost);
	        //store the response
	        HttpEntity entity = response.getEntity();
	        
	        InputStream is = entity.getContent();  
	        //convert the input stream to UTF-8 format
	        BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
	       StringBuffer responseString = new StringBuffer("");
	        //String line = reader.toString();
	        //System.out.println(line);
            String line;
            while ((line = reader.readLine()) != null) { // store the list of books in an arraylist
                //list.add(line);
                responseString.append(line);
            }
	        
	        String books = responseString.toString();
	        return books;
            
            	
	        
	    }catch (Exception e) {
	        // TODO: handle exception
	    	e.printStackTrace();
	    	return null;
	    	//return null;
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
