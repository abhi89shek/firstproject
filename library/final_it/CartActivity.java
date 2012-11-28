package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends Activity {

	ImageView image;
	TextView name;
	ListView ls;
	CheckBox chk;
	int img;
	String bname[] = new String[] { "hp", "hp2", "hp24" };
	String chkitems[] = new String[50];
	String delitems[] = new String[50];
	String tempbname[] = new String[50];
	int flag;
    int k=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		img = b.getInt("img");
		// bname=b.getString("name");

		try {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getBaseContext(),
					android.R.layout.simple_list_item_multiple_choice, bname);

			ls = (ListView) findViewById(R.id.ListView1);
			ls.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			ls.setAdapter(adapter);
            
			/*
			 * Button borrow = (Button) findViewById(R.id.blist);
			 * borrow.setOnClickListener(new OnClickListener() {
			 * 
			 * public void onClick(View v) { // TODO Auto-generated method stub
			 * SparseBooleanArray sp = ls.getCheckedItemPositions(); for (int i
			 * = 0; i < sp.size(); i++) { chkitems[i] =
			 * ls.getItemAtPosition(sp.keyAt(i)).toString();
			 * System.out.println(chkitems[i]); }
			 * 
			 * }
			 * 
			 * });
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_cart, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			
			SparseBooleanArray sp = ls.getCheckedItemPositions();
			for (int i = 0; i < sp.size(); i++) {
				chkitems[i] = ls.getItemAtPosition(sp.keyAt(i)).toString();
				System.out.println(chkitems[i]);
			}
			break;
		case R.id.menu_add:
			Intent in = new Intent(getBaseContext(), SearchbookActivity.class);
			startActivity(in);
			break;
		case R.id.menu_delete:
			SparseBooleanArray del = ls.getCheckedItemPositions();
			System.out.println("del:"+del.keyAt(0));System.out.println("size:"+del.size());
			
			for (int i = 0; i <del.size(); i++) {
				delitems[i] = ls.getItemAtPosition(del.keyAt(i)).toString();
						}
	/*	for(int i=0;i<bname.length;i++)	
		{
			flag=0;
			for(int j=0;j<delitems.length;j++)
			{
				if(bname[i].equals(delitems[j]))
				{
				 flag=1;
					System.out.println("bname"+bname[i]);

				}
			}
				if(flag!=1)
				{
				 tempbname[k]=bname[i];
					System.out.println("temp:"+tempbname[k]);

				 k++;
				 
				}
			}
		
for(int i=0;i<tempbname.length && tempbname[i]!=null;i++)
{
	System.out.println(tempbname.length);
	System.out.println(tempbname[i]);
}*/


		
	}
		return true;
}
}
