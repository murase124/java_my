package calen02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayPanel {

	public final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};
	public final int DAYBUTTONMAX = 42;
	//曜日と日付のパネル
	private JPanel dayPanel;
	private JPanel weekPane;
	public JPanel getDayPanel() {
		return dayPanel;
	}
	public JPanel getWeekPane() {
		return weekPane;
	}
	//日付の管理
	private ArrayList<DayList> dayLists = new ArrayList<>();
	public DayList getDayList(int key) {
		return dayLists.get(key);
	}
	
	//日付データを挿入
	public void setDayListDate(int key, int year, int month, int day) {
		if(0 <= key && key < DAYBUTTONMAX) {
			getDayList(key).setDate(year, month, day, String.valueOf(day));
		}
	}
	//日付を更新
	public void updetaDayButton(LocalDateTime dateTime) {
		int updateKey = 0;
		
		LocalDateTime back = dateTime.minusMonths(1);
		LocalDateTime next = dateTime.plusMonths(1);
		
		int backlastDay = back.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		int lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		int firstWeek = dateTime.getDayOfWeek().getValue() %7;
		
		//今月の最初の曜日までの穴埋め
		for(int i =--firstWeek; i >= 0 ;i--) {
			setDayListDate(updateKey, back.getYear(), back.getMonthValue(), backlastDay-i);
			updateKey++;
		}
		//今月
		for(int i =1; i <= lastDay;i++) {
			setDayListDate(updateKey, dateTime.getYear(), dateTime.getMonthValue(), i);
			updateKey++;
		}
		//余りを来月で穴埋め
		for(int i =1; updateKey < DAYBUTTONMAX;i++) {
			setDayListDate(updateKey, next.getYear(), next.getMonthValue(), i);
			updateKey++;
		}
	}
	
	//日付パネル作成
	private void createDayPanel() {
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(6, 7, 2, 3));

		for(int i=0;i < DAYBUTTONMAX;i++) {
			JButton jbutton = new JButton();
			dayLists.add(new DayList(jbutton));
			//フォント 
			jbutton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 19));
			jbutton.setPreferredSize(new Dimension(40, 22));
			if((i % 7) == 0) jbutton.setForeground(Color.RED);
			if((i % 7) == 6) jbutton.setForeground(Color.BLUE);
			//パネルにボタンを追加
			dayPanel.add(jbutton);
		}
	}
	//曜日パネル作成
	private void createWeekPane() {
		weekPane = new JPanel();
		weekPane.setLayout(new GridLayout(1, 7, 2, 2));
		for(int i=0;i < WEEKTEXT.length;i++) {
			JLabel label = new JLabel(WEEKTEXT[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			if(i == 0) label.setForeground(Color.RED);//日曜日
			if(i == 6) label.setForeground(Color.BLUE);//土曜日
			weekPane.add(label);
		}
	}
	
	DayPanel(LocalDateTime dateTime){
		createWeekPane();
		createDayPanel();
		updetaDayButton(dateTime);

	}
}
