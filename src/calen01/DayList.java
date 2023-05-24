package calen01;

import javax.swing.JButton;

public class DayList {
	
	private int day;	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	private int month;
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	private int year;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	private JButton dayButton = new JButton();
	public JButton getDayButton() {
		return dayButton;
	}
	public JButton setDayButton(JButton values) {
		this.dayButton = values;
		return values;
	}
	public JButton setDayButton(String values) {
		this.dayButton.setText(values);
		return dayButton;
	}
	public JButton setDayButton(int timeDay) {
		setDayButton(String.valueOf(timeDay));
		return dayButton;
	}

	public void setDate(int year, int month, int day, String text) {
		setYear(year);
		setMonth(month);
		setDay(day);
		setDayButton(text);
	}	
	
	DayList(JButton values){
		this.dayButton = values;
	}
}
