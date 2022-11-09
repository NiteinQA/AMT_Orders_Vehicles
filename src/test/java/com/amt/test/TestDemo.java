package com.amt.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amt.testBase.TestBase;

public class TestDemo extends TestBase{
	
	@Test	
	public void test1() {
		
		Assert.assertEquals(false, false);		
	}

	
	@Test	
	public void test2() {
		
		Assert.assertEquals(false, true);		
	}
}
