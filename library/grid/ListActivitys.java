package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class ListActivitys extends Activity implements OnItemClickListener  {

    String[] books1=new String[]{
    		"se","davinci","computernetworks1","computersecurity","database1","harrypotter",
    		"javacompletereference","softwaretesting"
    		} ; 
      
              String[] bookcat = new String[]{
                "Mystery",
                "Study Material",
                "saadsda",
                "asdasdasd",
                "asdasdad ",
                "asdasd ",
                "asdasd",
                " sdsdssdfsfdsfd",
                "ndfasfas",
                "Japanese Yenasfafaffa"
            };
              String[] bookdesc=new String[]{
            		 "aaaa","sdsdsd","sdsdssadaddaadsdadadadassssss" +
            		 		"asdasddassdasdadasdasdasdasdasdasdaddaaddadadadasssssssssssssssssssssssssssssssssssssss" +
            		 		"asdddddddddddddddddddddddddddddddddddddd" +
            		 		"asddddddddddddddddddddddddddddddddddddd" +
            		 		"asdddddddddddddddddddddddddddddddddddddddddddddd","sddssdds","tttttt","tttttrrr","uuuuuu","ggggggggg"
              };
         int arrowi=R.drawable.arrow1;
         int quantity=2;
         String isbn="b1234";
         ArrayList<HashMap<String,String>> aList1 = new ArrayList<HashMap<String,String>>();
         int[] hmres=new int[100];
         String[] hmdesc=new String[100];
     	

                    @Override
            public void onCreate (Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_list);
         
                ArrayList<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
         
          for(int t=0;t<books1.length;t++)
          {
        	  hmres[t]=getResources().getIdentifier(books1[t],"drawable", "com.vkr.smartlib");
        	  hmdesc[t]=bookdesc[t];
          }
         try{
                for(int i=0;i<books1.length;i++){
                	                	
                    HashMap<String, String> hm = new HashMap<String,String>();
                    
                    hm.put("flag", Integer.toString(hmres[i]));
                    hm.put("tvbn",books1[i]);
                    hm.put("isbn", isbn);
                    aList.add(hm);
                	}
              String[] from = {"flag","tvbn","isbn"};
         
              
               int[] to = { R.id.imageView1,R.id.tvbnshelf,R.id.tvisbnshelf};
         
              
               SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.shelf, from, to);
         
              GridView gs=(GridView) findViewById (R.id.gridview)   ; 
              gs.setAdapter(adapter);
              gs.setOnItemClickListener(this);
         }
         catch(Exception e)
         {
        	 e.printStackTrace();
         }
            }
               
            
     public void onItemClick(AdapterView<?> p, View v, int position, long id) {
                Intent intent;
                int pos=position;
             
                TextView uni=(TextView)v.findViewById(R.id.tvbnshelf);
                String pass=uni.getText().toString();
                TextView isbn=(TextView)v.findViewById(R.id.tvisbnshelf);
                String pass1=isbn.getText().toString();

             	
                int imageres = 0;
                for(int i=0;i<books1.length;i++)
                {
                	if(pass.equalsIgnoreCase(books1[i]))
                	{
                		
                 imageres=getResources().getIdentifier(books1[i], "drawable", "com.vkr.smartlib");
                	}
                	
                }
                
                intent=new Intent(this,DescActivity.class);
                Bundle b=new Bundle();
                b.putInt("img", imageres);
                b.putString("desc", hmdesc[pos]);
                b.putString("name",pass);
                b.putString("isbn",pass1);
                intent.putExtras(b);
                startActivity(intent);

              
                }
            
            
     
        
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list, menu);
        return true;
    }







	
}

