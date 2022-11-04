package com.amt.testUtil;

import org.testng.Reporter;

import com.amt.testBase.TestBase;

public class Logger extends TestBase {
	
	static String lineSeparator = System.getProperty("line.separator");
	final static String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	
	public static void print(String message) {
		System.setProperty(ESCAPE_PROPERTY, "false");
		Reporter.log("<b>"+message + "</b> <br />");
	}
}


