package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CartActivity extends Activity implements OnItemClickListener{
	TableLayout table;
	ListView ls = null;
	TableRow row;
	ImageView image;
	TextView name;
	CheckBox chk;
	int img;
	String cartitems;
	String []finalcart;
	ArrayList<HashMap<String,String>> clist = new ArrayList<HashMap<String,String>>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        /*table=(TableLayout) findViewById (R.id.table);
        row=(TableRow) findViewById (R.id.tvr);
        image=(ImageView) findViewById(R.id.imgcart);
        chk=(CheckBox) findViewById (R.id.chkbox1);
        name=(TextView) findViewById (R.id.tvbname);*/
        Bundle b=new Bundle();
        b=getIntent().getExtras();
        img=b.getInt("img");
        cartitems =b.getString("cartitems");   
        finalcart = cartitems.split(":");
        
        Integer checkbox = R.id.checkBox1;
        
        for(int i = 0;i<finalcart.length;i++)
        {
        	HashMap<String,String> cartmap = new HashMap<String,String>();
        	cartmap.put("checkbox", "");
        	cartmap.put("Bookname", finalcart[i]);
        	clist.add(cartmap);
        }
        String[] keys = { "checkbox" , "Bookname"};
        
        
        int[] ids = { R.id.checkBox1,R.id.cartitems};
  
       
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), clist, R.layout.cartlist, keys, ids);
  
        
        ls=(ListView) findViewById (R.id.listViewcart)   ; 
        System.out.println("button");
        ls.setAdapter(adapter);
        ls.setOnItemClickListener(this);
    }
        
        public void onItemClick(AdapterView<?> p, View v, int position, long id) {
        int firstPosition = ls.getFirstVisiblePosition();
        System.out.println(firstPosition);
        for(int i = firstPosition;i<ls.getCount();i++)
        {
        	System.out.println(ls.getCount());
        	 v = ls.getChildAt(i);
        	if(v != null){
        	System.out.println("inside checked sdfdsdsfsdfsdf");
        	TextView bname = (TextView)v.findViewById(R.id.cartitems);
        	System.out.println(bname.toString());
        	
        	CheckBox c = (CheckBox)v.findViewById(R.id.checkBox1);
        	if(c.isChecked())
        	{
        		System.out.println("checked");
        	}
        	}
        	
        }
       
        //name.setText(bname);
      //  image.setBackgroundResource(img);
        
        }
    
        

        
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cart, menu);
        return true;
    }
}
