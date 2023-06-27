package calen01;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GetDayMax {
	private Map<Integer, Map<Integer, Integer>> monthLastDay = new HashMap<>();
	private Map<Integer, Map<Integer, Integer>> monthLastWeek = new HashMap<>();
	private Calendar calendar;
	
	
	
	public int getMonthLastDay(int year, int month) {
		if(!judgement(year, month)) {
			return 0;
		}
		
		if(!monthLastDay.containsKey(year) || !monthLastDay.get(year).containsKey(month)) {
			setValues(year, month);
		}
		return monthLastDay.get(year).get(month);
	}
	public int getMonthLastWeek(int year, int month) {
		if(!judgement(year, month)) {
			return 0;
		}
		
		if(!monthLastWeek.containsKey(year) || !monthLastWeek.get(year).containsKey(month)) {
			setValues(year, month);
		}
		return monthLastWeek.get(year).get(month);
	}
	
	private boolean setValues(int year, int month) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		monthLastDay.get(year).put(month, calendar.get(Calendar.DATE));
		monthLastWeek.get(year).put(month, calendar.get(Calendar.DAY_OF_WEEK));
		
		return true;
	}
	
	private boolean judgement(int year, int month){
			if(year < 1900  || 2100 < year ) {
				return false;
			}
			if(month < 0  || 12 < month ) {
				return false;
			}
		return true;
	}
	
	GetDayMax(){
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
	}
}
