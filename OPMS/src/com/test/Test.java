package com.test;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.dao.ResproDAO;
import com.dao.impl.ResproDAOImpl;
import com.domain.Respro;

public class Test {

	public static void main(String[] args)
	{
		Date now = new Date();
	     
	    
	      DateFormat d2 = DateFormat.getDateTimeInstance();
	      String str2 = d2.format(now);
	      System.out.print(str2);




	}
}
