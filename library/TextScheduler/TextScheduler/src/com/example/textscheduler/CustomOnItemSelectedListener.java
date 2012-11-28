/*
 * The following method implements the dropdown to select the                                                                                                                * occassion.in the dropdown we have Birthday, Anniversary and 
* Meeting being popped up.
*/

package com.example.textscheduler;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
 
public class CustomOnItemSelectedListener implements OnItemSelectedListener {
 
  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
	  	/*
	  Toast.makeText(parent.getContext(), 
		"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
		Toast.LENGTH_SHORT).show();
		*/
  }
 
  public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
  } 
}
