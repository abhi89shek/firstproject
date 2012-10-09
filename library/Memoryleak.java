package com.memoryleaks;

import java.util.HashMap;

public class Memoryleak {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap hm = new HashMap();
		int i = 0;
		do
		{
			sampleMem(hm,i);
			
			
		}while(true);

	}
	
	public static void sampleMem(HashMap map,int i)
	{
		map.put("key", i);
		i++;
		
	}

}
