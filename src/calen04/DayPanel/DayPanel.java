package calen04.DayPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import calen04.DayButtonDTO.DayButtons;

public class DayPanel {

	public final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};	
	
	//日付パネル作成
	public JPanel createDayPanel(DayButtons dayButtons) {
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(6, 7, 2, 3));
		for(int i=0;i < dayButtons.length();i++) {
			dayPanel.add(dayButtons.getDayButtons(i));
		}
		return dayPanel;
	}
	//曜日パネル作成
	public JPanel createWeekPane() {
		JPanel weekPane = new JPanel();
		weekPane.setLayout(new GridLayout(1, 7, 2, 2));
		for(int i=0;i < WEEKTEXT.length;i++) {
			JLabel label = new JLabel(WEEKTEXT[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			if(i == 0) label.setForeground(Color.RED);//日曜日
			if(i == 6) label.setForeground(Color.BLUE);//土曜日
			weekPane.add(label);
		}
		return weekPane;
	}
}
