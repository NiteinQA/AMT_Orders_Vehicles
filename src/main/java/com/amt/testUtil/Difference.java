package com.amt.testUtil;

public class Difference  {
	
	public static double of_two_Double_Values(double value1, double value2)
	{
		double result=(value1>=value2)?(value1-value2):(value2-value1);
		
		return result;		
	}

}
