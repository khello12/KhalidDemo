package com.khalid.util;

import java.util.ArrayList;

public class KhalidUtil {

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//"  delta,air france,united   "
	//ArrayList<String> parseCSV(str)
	
	public static ArrayList<String> parseCSV(String str) {
		if (!isNullOrEmpty(str)){
			str = str.trim();
			String[] carriers = str.split(",");
			ArrayList<String> result = new ArrayList<String>(carriers.length);
			
			for (int i = 0; i < carriers.length; i++) {
				result.add(carriers[i].trim());
			}
						
			return result;
		}
		return null;
	}
}