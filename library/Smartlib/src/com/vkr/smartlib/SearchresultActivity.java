package com.vkr.smartlib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchresultActivity extends Activity implements OnItemClickListener {
	
	String[] listitems=new String[20];
	String bookarray = null;
	ListView lst = null;
	String sessionid = null;
	String []booknameisbn = new String[100];
	String []books1 = new String[100];
	/*String[] books1=new String[]{
    		"se","davinci","computernetworks1","computersecurity","database1","harrypotter",
    		"javacompletereference","softwaretesting"
    		} ; */
	int arrowi=R.drawable.arrow1;
   
    String isbn="b1234";
    ArrayList<HashMap<String,String>> aList1 = new ArrayList<HashMap<String,String>>();
    int[] hmres=new int[100];
    String[] hmdesc=new String[100];
    String []blist1 = new String[100];
    String []blist2 = new String[100];
    String []blist3 = new String[100];
    
    String[] bookdesc=new String[]{
   		 "While in Paris on business, Harvard symbologist Robert Langdon receives an urgent late-night phone call: the elderly curator of the Louvre" +
   		 		" has been murdered inside the museum. Near the body, police have found a baffling cipher. While working to solve the enigmatic riddle, Langdon is stunned to discover it leads to a trail of clues hidden in the works of Da Vinci -- clues visible for all to see -- yet ingeniously disguised by the painter.","Software Engineering presents a broad perspective on software systems engineering, concentrating on widely-used techniques for developing large-scale software systems. This best-selling book covers a wide spectrum of software processes from initial requirements elicitation through design and development to system evolution. It supports students taking undergraduate and graduate courses in software engineering. The sixth edition has been restructured and updated, " +
   		 	   		 "important new topics have been added and obsolete material has been cut. Reuse now focuses on component-based development and patterns; object-oriented design has a process focus and uses the UML; the chapters on requirements have been split to cover the requirements themselves and requirements engineering process; " +
   		    		 "cost estimation has been updated to include the COCOMO 2 model.","sdsds","sddssdds","tttttt","Readers beware. The brilliant, breathtaking conclusion to J.K. Rowling's spellbinding series is not for the faint of heart--such revelations, battles, and betrayals await in Harry Potter and the Deathly Hallows that no fan will make it to the end unscathed. Luckily, Rowling has prepped loyal readers for the end of her series by doling out increasingly dark and dangerous tales of magic and mystery, shot through with lessons about honor and contempt, love and loss, " +
   		 		"and right and wrong. Fear not, you will find no spoilers in our review--to tell " +
   		 		"the plot would ruin the journey, and Harry Potter and the Deathly Hallows is " +
   		 		"an odyssey the likes of which Rowling's fans have not yet seen, and are not likely to " +
   		 		"forget. But we would be remiss if we did not offer one small suggestion before you embark " +
   		 		"on your final adventure with Harry--bring plenty of tissues.","uuuuuu","The world's leading software testing experts lend you their wisdom and years of experience to help you avoid the most common mistakes in testing software. Each lesson is an assertion related to software testing, followed by an explanation or example that shows you the how, when, and why of the testing lesson. More than just tips, tricks, and pitfalls to avoid, Lessons Learned in Software Testing speeds you through the critical testing phase " +
   		 				"of the software development project without the extensive trial and error it normally takes to do so."
     };
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        
        Bundle extras = getIntent().getExtras(); //get the list of books from the calling activity
        bookarray = extras.getString("books");
        ArrayList<String[]> blist = new ArrayList<String[]>();
        System.out.println(bookarray);
        sessionid = extras.getString("sessionid");
        Iterator it = null;
        Iterator it1 = null;
        listitems = bookarray.split(":");
        for(int l =0; l<listitems.length ; l++)
        {
        	System.out.println(listitems[l]);
        }
        
        
        //String name[] = listitems.split("\\(");
       for(int i = 0;i<listitems.length;i++)
       {
    	   blist.add(listitems[i].split("\\("));
    	   //booknameisbn[i] = listitems[i].split("\\(");
    	  // System.out.println(booknameisbn[i]);
       }
       
       it = blist.iterator();
       int z = 0;
       while(it.hasNext())
       {
    	   blist1 = (String [])it.next();   
    	        
    	        blist2[z]  = blist1[0];
    	        System.out.println("blist2"+ blist2[z]);
    	        z++;
       }
       
       it1 = blist.iterator();
    		   int m = 0;
    		   while(it1.hasNext())
    		   {
    			   blist1 = (String [])it1.next();
    			   blist3[m] = blist1[1];
    			   System.out.println("blist3"+ blist3[m]);
    			   m++;
    		   }
         
       
       /*for(int j=0,k=0;j<booknameisbn.length && k<j ; j=j+2,k++)
       {
    	   books1[k] = booknameisbn[j];
    	   System.out.println(books1[k]);
       }*/
      /* for(int g = 0;g <blist2[g]; g++)
       {
    	   System.out.println("hello "+blist2[g]);
       }*/
       ArrayList<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
       
       for(int t=0;blist2[t]!=null;t++)
       {
     	  hmres[t]=getResources().getIdentifier(blist2[t],"drawable", "com.vkr.smartlib");
     	  hmdesc[t]=bookdesc[t];
       }
      try{
             for(int i=0;blist2[i]!=null;i++){
             	                	
                 HashMap<String, String> hm = new HashMap<String,String>();
                 
                 hm.put("flag", Integer.toString(hmres[i]));
                 hm.put("tvbn",blist2[i]);
                 hm.put("arrow", Integer.toString(arrowi));
                 hm.put("isbn", blist3[i]);
                 
                 aList.add(hm);
             	}
           String[] from = { "flag","tvbn","isbn" ,"arrow"};
      
           
            int[] to = { R.id.flag,R.id.tvbkn,R.id.isbn1,R.id.arrow};
      
           
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list1, from, to);
      
           ListView ls=(ListView) findViewById (R.id.listview)   ; 
           System.out.println("button");
           ls.setAdapter(adapter);
           ls.setOnItemClickListener(this);
      }
      catch(Exception e)
      {
     	 e.printStackTrace();
      }
        
    }
    
       
    
    public void onItemClick(AdapterView<?> p, View v, int position, long id) {
        Intent intent;
        int pos=position;
        
        //String selectedFromList =(String) (lst.getItemAtPosition(position));
        TextView uni=(TextView)v.findViewById(R.id.tvbkn);
        String pass=uni.getText().toString();
        TextView isbn=(TextView)v.findViewById(R.id.isbn1);
        String val=isbn.getText().toString();

        
        int imageres = 0;
        for(int i=0;blist2[i]!=null;i++)
        {
        	if(pass.equalsIgnoreCase(blist2[i]))
        	{
        		
        		imageres=getResources().getIdentifier(blist2[i], "drawable", "com.vkr.smartlib");
         
        	}
        	
        }
               intent=new Intent(this,DescActivity.class);
               Bundle b=new Bundle();
               
               b.putString("isbn", val);
               b.putInt("img", imageres);
               b.putString("desc", hmdesc[pos]);
               b.putString("name",pass);               
               b.putString("sessionid", sessionid);
               intent.putExtras(b);
               startActivity(intent);
               
       
        	
        }
     
        
    

    	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_searchresult, menu);
        return true;
    }
}
