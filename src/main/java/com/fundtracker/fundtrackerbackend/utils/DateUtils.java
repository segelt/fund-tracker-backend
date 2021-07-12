package com.fundtracker.fundtrackerbackend.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class DateUtils {

	
	public static LocalDate priorDateFetcher(int mode){
		Calendar targetDate = Calendar.getInstance();
		switch(mode){
		case 0:
			targetDate.add(Calendar.WEEK_OF_YEAR, -1);
			break;
		case 1:
			targetDate.add(Calendar.MONTH, -1);
			break;
		case 2:
			targetDate.add(Calendar.MONTH, -3);
			break;
		case 3:
			targetDate.add(Calendar.YEAR, -1);
			break;
		case 4:
			targetDate.add(Calendar.YEAR, -3);
			break;
		default:
			targetDate.add(Calendar.YEAR, -100);
			break;
		}
		return LocalDateTime.ofInstant(targetDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
	
}
