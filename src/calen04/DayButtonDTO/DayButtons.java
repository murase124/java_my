package calen04.DayButtonDTO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DayButtons  {
	
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
	//日付を更新
	public void updetaDayButton(LocalDateTime dateTime) {
		
		LocalDateTime back = dateTime.minusMonths(1);
		LocalDateTime next = dateTime.plusMonths(1);
		
		int backlastDay = back.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		int lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		int firstWeek = dateTime.getDayOfWeek().getValue() %7;
		LocalDateTime backdatetime = dateTime.minusDays(firstWeek);
		
		for(int i =0; i < dayButtons.length;i++) {
			setDayButtonDate(i, backdatetime.plusDays(i));
		}
	}
	
	public DayButtons(int dayMax){
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
}
