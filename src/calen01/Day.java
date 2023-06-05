package calen01;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class Day {
	private DayPanel dayPanel;
	public DayPanel getDayPanel() {
		return dayPanel;
	}
	private Calendar calendar;
	/*
	 * get○○Month OR get○○Year
	 * 年又は月を返す
	 */
	
	private int backMonth;
	private void setBackMonth(int backMonth) {
		this.backMonth = backMonth+1;
	}
	public int getBackMonth() {
		return backMonth;
	}
	private int month;
	private void setMonth(int month) {
		this.month = month+1;
	}
	public int getMonth() {
		return month;
	}
	private int nextMonth;
	private void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth+1;
	}
	public int getNextMonth() {
		return nextMonth;
	}
	private int backYear;
	private void setBackYear(int backYear) {
		this.backYear = backYear+1;
	}
	public int getBackYear() {
		return backYear;
	}
	private int year;
	private void setYear(int year) {
		this.year = year+1;
	}
	public int getYear() {
		return year;
	}
	private int nextYear;
	private void setNextYear(int nextYear) {
		this.nextYear = nextYear+1;
	}
	public int getNextYear() {
		return nextYear;
	}
	
	/*
	 * get○○Month OR get○○Year　ここまで
	 */

	
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
	private void setDatlist(int key, int year, int month, int day) {
		getDayPanel().setDayListDate(key, year, month, day, String.valueOf(day));
	}
	
	
	public void dayButtonAddActionListener(ActionListener e) {
		getDayPanel().dayButtonAddActionListener(e);
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
		
		calendar.add(Calendar.MONTH, 1);
		setNextMonth(calendar.get(Calendar.MONTH));
		setNextYear(calendar.get(Calendar.YEAR));
		
		calendar.add(Calendar.MONTH, -2);
		setBackMonth(calendar.get(Calendar.MONTH));
		setBackYear(calendar.get(Calendar.YEAR));
		int backlastDay = calendar.getActualMaximum(Calendar.DATE);
		
		calendar.add(Calendar.MONTH, 1);
		setMonth(calendar.get(Calendar.MONTH));
		setYear(calendar.get(Calendar.YEAR));
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		int firstWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		
		//今月の最初の曜日までの穴埋め
		for(int i =firstWeek-WEEKADJUSTMENT; i >= 0;i--) {
			setTimeDay(updateKey, backlastDay-i);
			setDatlist(updateKey, getBackYear(), getBackMonth(), backlastDay-i);
			updateKey++;
		}
		//今月
		for(int i =1; i <= lastDay;i++) {
			setTimeDay(updateKey, i);
			setDatlist(updateKey, getYear(), getMonth(), i);

			updateKey++;
		}
		//余りを来月で穴埋め
		for(int i =1; updateKey < dayPanel.DAYBUTTONMAX;i++) {
			setTimeDay(updateKey, i);
			setDatlist(updateKey, getNextYear(), getNextMonth(), i);
			updateKey++;
		}
	}
	
	/*
	 * Day
	 * JBuutonをDAYBUTTONMAX個作成
	 * フォント設定
	 * フォントカラー　日曜　赤色、　土曜　青色
	 */
	Day(){
		dayPanel = new DayPanel();
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
