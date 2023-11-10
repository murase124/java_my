package calen05.mainFrame.dayPanel.dayButtonDTO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	
	//更新処理
	public void updetaDay(LocalDateTime dateTime) {
		int firstWeek = dateTime.getDayOfWeek().getValue() %7;
		LocalDateTime prevdatetime = dateTime.minusDays(firstWeek);
		for(int i =0; i < dayButtons.length;i++) {
			setDayButtonDate(i, prevdatetime.plusDays(i));
		}
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
}
