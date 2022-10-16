package com.rortegag.funwithflags.util;

public class CodeGameGenerator 
{

	public static String generateCode() 
	{
	   int code = (int) ((Math.random() * (99999 - 10000)) + 10000);
	   return Integer.toString(code);
	}
	
}
