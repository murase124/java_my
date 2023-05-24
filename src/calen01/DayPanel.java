package calen01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DayPanel {
	//曜日
	public final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};
	
	private final int DAYBUTTONMAX = 42;
	private ArrayList<DayList> dayList = new ArrayList<>();
	public ArrayList<DayList> getDayList() {
		return dayList;
	}
	private JButton setDayListButton(JButton values) {
		dayList.add(new DayList(values));
		return values;
	}
	
	public void setDayListDate(int key, int year, int month, int day, String text) {
		if(0 <= key && key < DAYBUTTONMAX) {
			dayList.get(key).setDate(year, month, day, text);			
		}
	}
	
	private ArrayList<Schedule> schedule = new ArrayList<>();
	public ArrayList<Schedule> getSchedule() {
		return schedule;
	}
		
	private JPanel dayPanel;//各日のJButtonを表示
	public JPanel getDayPanel() {
		return dayPanel;
	}
	
	DayPanel(){
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(6, 7, 2, 3));

		for(int i=0;i < DAYBUTTONMAX;i++) {
			JButton jbutton = setDayListButton(new JButton());
			//フォント 
			jbutton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			jbutton.setPreferredSize(new Dimension(40, 20));
			if((i % 7) == 0) jbutton.setForeground(Color.RED);
			if((i % 7) == 6) jbutton.setForeground(Color.BLUE);
			//パネルにボタンを追加
			dayPanel.add(jbutton);
		}
	}
}
