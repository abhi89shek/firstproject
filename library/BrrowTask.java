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

public class BrrowTask extends AsyncTask<String[], Void, ArrayList > {

	@Override
	protected ArrayList doInBackground(String[]... params) {
		// TODO Auto-generated method stub
		
		
		String temp[] = new String[20];
		ArrayList<String> list =  new ArrayList<String>();
		 temp = params[0];
		 
		 ArrayList<NameValuePair> alist = new ArrayList<NameValuePair>();
		 alist.add(new BasicNameValuePair("bname",temp[0]));
		 alist.add(new BasicNameValuePair("mid", temp[1]));
		 alist.add(new BasicNameValuePair("isbn", temp[3]));
		 alist.add(new BasicNameValuePair("tno", temp[4]));
		
		 
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
		        //StringBuffer responseString = new StringBuffer("");
		        //String line = reader.toString();
		        //System.out.println(line);
	            String line;
	            while ((line = reader.readLine()) != null) { // store the list of books in an arraylist
	                list.add(line);
	                //System.out.println(line);
		       // System.out.println(responseString);
	            }
		        
		        //retureturn rn responseString;
	            return list;
	            	
			 
		 } catch(Exception e)
		 {
			 e.printStackTrace();
		    	return null;
		 }
		 
		 
		 
		 
		
	}
	 protected void onPostExecute(ArrayList result) {
	    	
    	 returnValue(result);
    	 
    }
		
    protected ArrayList returnValue(ArrayList strb) {
    	
    	return strb;
	}

	

}



