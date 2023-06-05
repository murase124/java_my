package calen01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayPanel {
	//曜日
	public final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};
	
	public final int DAYBUTTONMAX = 42;
	private ArrayList<DayList> dayList = new ArrayList<>();
	public ArrayList<DayList> getDayList() {
		return dayList;
	}
	public DayList getDayList(int key) {
		return dayList.get(key);
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
	public void dayButtonAddActionListener(ActionListener e) {
		for(int i=0; i < DAYBUTTONMAX; i++) {
			getDayList().get(i).getDayButton().addActionListener(e);
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
	
	
	public JPanel getWeekPane() {
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
