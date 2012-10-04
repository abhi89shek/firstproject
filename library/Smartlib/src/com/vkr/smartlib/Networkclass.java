package com.vkr.smartlib;

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



public class Networkclass extends  AsyncTask<String[], Void, StringBuffer >{
	
	
	
    protected StringBuffer doInBackground(String[]... params) {
    	
    	String [] temp = new String[20] ;
    	temp = params[0];
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("tno", temp[0]));
		nameValuePairs.add(new BasicNameValuePair("id", temp[1]));
		nameValuePairs.add(new BasicNameValuePair("pass", temp[2])); 
    	try {
	        HttpClient httpClient = new DefaultHttpClient();
	        System.out.println("button3");
	        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/webapp/Controllerservlet");
	        System.out.println("button4");//the webserver address goes in here
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        System.out.println("button5");
	        HttpResponse response = httpClient.execute(httpPost);
	        System.out.println("button6");
	        HttpEntity entity = response.getEntity();
	        System.out.println("button7");
	        InputStream is = entity.getContent();  
	        System.out.println("button8");
	        BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
	        StringBuffer responseString = new StringBuffer("");
            String line;
            while ((line = reader.readLine()) != null) {
                responseString.append(line);
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
    	
    	 returnValue(result);
    	 
    }
		
    protected StringBuffer returnValue(StringBuffer strb) {
    	
    	return strb;
	}

	

}
