package calen05.mainFrame.dayPanel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JPanel;

import calen05.dateTimeManagement.DateTimeMmanagement;
import calen05.mainFrame.dayPanel.dayButtonDTO.DayButtons;

public class DayPanel extends JPanel{

	
	private DayButtons dayButtons;
	
	//日付パネル作成
	public DayPanel(int daynum) {
		dayButtons = new DayButtons(daynum);
		
		setLayout(new GridLayout(6, 7, 2, 3));
		for(int i=0;i < dayButtons.length();i++) {
			add(dayButtons.getDayButtons(i));	
		}
		
		updetaDay(DateTimeMmanagement.getNow());
	}
	
	public void updetaDay(LocalDateTime dateTime) {
		dayButtons.updetaDay(dateTime);
	}
	
	
	public void addDayButtonsActionListener(ActionListener actionListener) {
		//日付ボタン　クリック　ー＞　日付渡す
		for(int i=0; i < dayButtons.length(); i++) {
			dayButtons.getDayButtons(i).addActionListener(actionListener);
		}
	}
	
	
}
