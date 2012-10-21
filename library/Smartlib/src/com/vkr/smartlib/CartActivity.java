package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CartActivity extends Activity {
	TableLayout table;
	TableRow row;
	ImageView image;
	TextView name;
	CheckBox chk;
	int img;
	String bname;
	
	// Adding books to the cart

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        table=(TableLayout) findViewById (R.id.table);
        row=(TableRow) findViewById (R.id.tvr);
        image=(ImageView) findViewById(R.id.imgcart);
        chk=(CheckBox) findViewById (R.id.chkbox1);
        name=(TextView) findViewById (R.id.tvbname);
        Bundle b=new Bundle();
        b=getIntent().getExtras();
        img=b.getInt("img");
        bname=b.getString("name");   
        name.setText(bname);
      //  image.setBackgroundResource(img);
        chk.setClickable(true);
      //  image.setVisibility(2);
        
        	chk.setOnClickListener(new OnClickListener()
        	{
        	 public void onClick(View arg0) {
				// TODO Auto-generated method stub
             //	image.setVisibility(0);
             	image.setBackgroundResource(img);

			}
        	}  );
        }
        

        
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cart, menu);
        return true;
    }
}
