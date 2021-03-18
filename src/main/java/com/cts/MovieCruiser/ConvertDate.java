package com.cts.MovieCruiser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {

	public static Date convertToDate(String date) throws ParseException
	{
		SimpleDateFormat df= new SimpleDateFormat("dd/mm/yyyy");
		Date newDate= df.parse(date);
		return newDate;
	}
}
