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



public class SearchBooktask extends  AsyncTask<String[], Void, ArrayList >{
	
	
	
    protected ArrayList doInBackground(String[]... params) {
    	
    	String [] temp = new String[20] ;
    	ArrayList<String> list =  new ArrayList<String>();
    	temp = params[0];
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("tno", temp[0]));
		nameValuePairs.add(new BasicNameValuePair("keyword", temp[1]));
		
				
    	try {
	        HttpClient httpClient = new DefaultHttpClient();
	       
	        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/webapp/Controllerservlet");
	        //the webserver address goes in here
	        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	       
	        HttpResponse response = httpClient.execute(httpPost);
	        
	        HttpEntity entity = response.getEntity();
	        
	        InputStream is = entity.getContent();  
	        
	        BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
	        //StringBuffer responseString = new StringBuffer("");
	        //String line = reader.toString();
	        //System.out.println(line);
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                //System.out.println(line);
	       // System.out.println(responseString);
            }
	        
	        //retureturn rn responseString;
            return list;
            	
	        
	    }catch (Exception e) {
	        // TODO: handle exception
	    	e.printStackTrace();
	    	return null;
	    	//return null;
	                	    }
    	
    	   		
    		
    	}
    protected void onPostExecute(ArrayList result) {
    	
    	 returnValue(result);
    	 
    }
		
    protected ArrayList returnValue(ArrayList strb) {
    	
    	return strb;
	}

	

}
