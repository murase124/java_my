package calen05.DayButtonDTO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DayButtons  {
	LocalDateTime dateTime;
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public LocalDateTime getNextMonths() {
		return dateTime.plusMonths(1);
	}
	public LocalDateTime getBackMonths() {
		return dateTime.minusMonths(1);
	}
	
	private DayButton[] dayButtons;
	public DayButton getDayButtons(int num) {
		return dayButtons[num];
	}
	public int length() {
		return dayButtons.length;
	};

	//日付データを挿入
	public void setDayButtonDate(int key, LocalDateTime dateTime) {
		dayButtons[key].setDate(LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()), String.valueOf(dateTime.getDayOfMonth()));
	}
	//日付を設定
	public void updetaDayButton(LocalDateTime dateTime) {
		this.dateTime = dateTime;
		updetaDay(dateTime);
	}
	//更新処理
	private void updetaDay(LocalDateTime dateTime) {
		int firstWeek = dateTime.getDayOfWeek().getValue() %7;
		LocalDateTime backdatetime = dateTime.minusDays(firstWeek);
		
		for(int i =0; i < dayButtons.length;i++) {
			setDayButtonDate(i, backdatetime.plusDays(i));
		}
	}
	//次の月
	public void nextMonths() {
		dateTime = dateTime.plusMonths(1);
		updetaDay(dateTime);
	}
	//前の月
	public void backMonths() {
		dateTime = dateTime.minusMonths(1);
		updetaDay(dateTime);
	}
	
	public void setDayNum(int dayMax){
		dayButtons = new DayButton[dayMax];
		for(int i=0;i < dayButtons.length;i++) {
			dayButtons[i] = new DayButton();
			//フォント 
			dayButtons[i].setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 19));
			dayButtons[i].setPreferredSize(new Dimension(40, 22));
			if((i % 7) == 0) dayButtons[i].setForeground(Color.RED);
			if((i % 7) == 6) dayButtons[i].setForeground(Color.BLUE);
		}
	}
	public DayButtons(int dayMax){
		setDayNum(dayMax);
	}
	public DayButtons(int dayMax, LocalDateTime dateTime){
		setDayNum(dayMax);
		updetaDayButton(dateTime);
	}
}
