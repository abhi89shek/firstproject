package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.R.integer;
import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ViewreportActivity extends Activity {
    ArrayList<HashMap<String,String>> vList1 = new ArrayList<HashMap<String,String>>();
    
    
    String[] reports2 = new String[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewreport);
    	String[] params = new String[1];
        String tno = "16";
        params[0] = tno;
        Networkclass_admin_viewreport report = new Networkclass_admin_viewreport();
        report.execute(params);
        StringBuffer reports = null;
        
		try {
			reports = report.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String final_report = reports.toString();
        reports2 = final_report.split(";");
        
        
        
    	try{
            for(int i=0;i<reports2.length;i++)
            {
            	                	
                HashMap<String, String> hm = new HashMap<String,String>();
                
                hm.put("string", reports2[i]);
                vList1.add(hm);
            	}
          
           int tv[]={R.id.tvr1};
           String[] st={"string"};
          
           SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), vList1, R.layout.report, st,tv); // creating list views
     
          ListView ls=(ListView) findViewById (R.id.listview1); 
          ls.setAdapter(adapter);
         // ls.setOnItemClickListener(this);
          
          
          
     }
     catch(Exception e)
     {
    	 e.printStackTrace();
     }
       
   }        
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_viewreport, menu);
        return true;
    }
}
