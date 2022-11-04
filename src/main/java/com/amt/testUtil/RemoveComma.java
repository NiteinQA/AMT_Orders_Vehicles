package com.amt.testUtil;

public class RemoveComma {
	
	public static String of(String value) {
		String temp= "";
		for (int x = 0; x < value.length(); x++) {char newch = value.charAt(x);
			if (newch == ',') {} else {	temp = temp + newch;}
		}
		return temp;
	}

}
