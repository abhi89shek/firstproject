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

public class Alerttask extends  AsyncTask<String[], Void, StringBuffer >{
	
	
	
    protected StringBuffer doInBackground(String[]... params) {
    	
    	String [] temp = new String[20] ;
    	temp = params[0];              // get the parameters from the main thread
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("tno", temp[0])); //parameters are added to an arraylist
		nameValuePairs.add(new BasicNameValuePair("sessionid", temp[1]));
		 
    	try {
	        HttpClient httpClient = new DefaultHttpClient();	        
	        HttpPost httpPost = new HttpPost("http://10.0.2.2:8080/webapp/Controllerservlet");//the webserver address goes in here	        
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
    	
    	 returnValue(result);     //return the result to the main thread
    	 
    }
		
    protected StringBuffer returnValue(StringBuffer strb) {
    	
    	return strb;
	}

	

}



