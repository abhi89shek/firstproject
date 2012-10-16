package com.vkr.smartlib;

import android.widget.*;

public class Borrow {
	
	
	Button borrow;
	//EditText bor;
	String[] Parameters = new String[5];
	String ano = "8";
	
	public void borrow(String bname, String mid , String isbn)
	{
	 
		Parameters[0] = bname;
		Parameters[1] = mid;
		Parameters[2] = isbn;
		Parameters[3] = ano;
		BrrowTask btask = new BrrowTask();
		btask.execute(Parameters);
		
		
	
	}

}
