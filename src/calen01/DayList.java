package calen01;

import java.util.ArrayList;
import java.util.Calendar;

public class DayList {
	private final int DAYBUTTONMAX = 42;

	private Calendar calendar;
	/*
	 * get○○Month OR get○○Year
	 * 年又は月を返す
	 */
	public int getBoackMonth() {
		Calendar back = ((Calendar) calendar.clone());
		back.add(Calendar.MONTH, -1);
		return back.get(Calendar.MONTH) +1;
	}
	public int getBoackYear() {
		Calendar back = ((Calendar) calendar.clone());
		back.add(Calendar.MONTH, -1);
		return back.get(Calendar.YEAR);
	}
	public int getMonth() {
		return calendar.get(Calendar.MONTH) +1;
	}
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}
	public int getNextMonth() {
		Calendar next = ((Calendar) calendar.clone());
		next.add(Calendar.MONTH, 1);
		return next.get(Calendar.MONTH) +1;
	}
	public int getNextYear() {
		Calendar next = ((Calendar) calendar.clone());
		next.add(Calendar.MONTH, 1);
		return next.get(Calendar.YEAR);
	}
	
	/*
	 * get○○Month OR get○○Year　ここまで
	 */

	//年4桁、月2桁、日2桁を区切文字りなし
	private ArrayList<Integer> timeDay = new ArrayList<>();
	public ArrayList<Integer> getTimeDay() {
		return timeDay;
	}
	public Integer getTimeDay(int key) {
		return timeDay.get(key);
	}
	private void setTimeDay(int key, Integer values) {
		timeDay.add(key, values);
	}
	
	/* 
	 * createDay
	 * Calendar型変数作成
	 * 今月 calendar
	 */
	public void createDay() {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		updetaDayButton();
	}
	public void setDay(int month) {
		calendar.set(Calendar.MONTH, month);
		updetaDayButton();
	}
	
	public void updetaDayButton() {
		/*
		 *  WEEKADJUSTMENT
		 *  Calendar.get(Calendar.DAY_OF_WEEK) SUNDAY(1)
		 *  previous month AND SUNDAY(1) => SUNDAY(0)
		 */
		final int WEEKADJUSTMENT = 2;
		int updateKey = 0;
		calendar.add(Calendar.MONTH, -1);
		int backlastDay = calendar.getActualMaximum(Calendar.DATE);
		
		calendar.add(Calendar.MONTH, 1);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		int firstWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		
		//今月の最初の曜日までの穴埋め
		for(int i =firstWeek-WEEKADJUSTMENT; i >= 0;i--) {
			setTimeDay(updateKey, backlastDay-i);
			updateKey++;
		}
		//今月
		for(int i =1; i <= lastDay;i++) {
			setTimeDay(updateKey, i);
			updateKey++;
		}
		//余りを来月で穴埋め
		for(int i =1; updateKey < DAYBUTTONMAX;i++) {
			setTimeDay(updateKey, i);
			updateKey++;
		}
	}
	
	/*
	 * DayList
	 * JBuutonをDAYBUTTONMAX個作成
	 * フォント設定
	 * フォントカラー　日曜　赤色、　土曜　青色
	 */
	DayList(){
		createDay();
	}
	
	public void nextMonth() {
		calendar.add(Calendar.MONTH, 1);
		updetaDayButton();
	}
	public void backMonth() {
		calendar.add(Calendar.MONTH, -1);
		updetaDayButton();
	}
}
