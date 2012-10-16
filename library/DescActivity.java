package com.vkr.smartlib;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;

public class DescActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
      Bundle b=new Bundle();
      b=getIntent().getExtras();
      int dav=b.getInt("davinci");
      System.out.println(+dav);
      //pass the integer value from previous activity
        ImageView img= (ImageView) findViewById (R.id.imde1);
        img.setBackgroundResource(dav);//need to store in a an array
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_desc, menu);
        return true;
    }
}
