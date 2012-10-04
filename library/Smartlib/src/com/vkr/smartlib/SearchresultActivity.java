package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchresultActivity extends Activity {
	
	String[] listitems=new String[20];
	ArrayList<String> bookarray = new ArrayList<String>();
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        try{
        Bundle extras = getIntent().getExtras();
        bookarray = extras.getStringArrayList("books");
        Iterator it = bookarray.iterator();
       /* int i =0;
       do{
    	   listitems[i] =(String)it.next();
    	   i++;
       }while(it.hasNext());*/
        ListView lst=(ListView) findViewById (R.id.listview1);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookarray);
        lst.setAdapter(adp);
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
     
        
    }

    	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_searchresult, menu);
        return true;
    }
}
