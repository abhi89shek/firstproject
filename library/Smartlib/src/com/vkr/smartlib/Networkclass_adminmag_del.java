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

import android.os.AsyncTask;

public class Networkclass_adminmag_del extends AsyncTask<String[], Void, StringBuffer > {


	@Override
	protected StringBuffer doInBackground(String[]... params1) {
		// TODO Auto-generated method stub
		System.out.println("INSIDE TEST CLASS");
		String [] temp = new String[20] ;
		System.out.println("INSIDE TEST CLASS0");
    	temp = params1[0];
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	nameValuePairs.add(new BasicNameValuePair("tno", temp[0]));
    	System.out.println("INSIDE TEST CLASS1");
    	nameValuePairs.add(new BasicNameValuePair("bookISBN", temp[1]));
    	System.out.println("INSIDE TEST CLASS2");
		
		for(int i =0 ; i<temp.length;i++)
    	{
    		System.out.println(temp[i] + "\n");
    	}
    	

    	try {
    		
    		System.out.println("INSIDE TEST TRY");
    		
    		  HttpClient httpClient = new DefaultHttpClient();
    		  HttpPost httpPost = new HttpPost("http://192.168.50.1:8080/webapp/Controllerservlet");//the webserver address goes in here	        
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
  	        System.out.println(responseString);
              }
              return responseString;
          	
  	        
	    }catch (Exception e) {
	        // TODO: handle exception
	    	e.printStackTrace();
	    	return null;
	    	//return null;
	                	    }
	}

		
		
	
protected void onPostExecute(StringBuffer result) {
	
	 returnValue(result);     //return the result to the main thread
	 
}
	
protected StringBuffer returnValue(StringBuffer strb) {
	
	return strb;
}


	}



