package calen05.mainFrame.scheduleDisplayPanel.panels.scheduleDetailPane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextField;

import calen05.scheduleManagement.Schedule;

public class ItemManagement extends Item {
	/* テキストフィールドの配列 */
	public JTextField[] getJTextField() {
		JTextField[] jTextField = new JTextField[5];
		jTextField[0] = getStartDate();
		jTextField[1] = getStartTime();
		jTextField[2] = getEndDate();
		jTextField[3] = getEndTime();
		jTextField[4] = getTitle();
		return jTextField;
	}
	
	/* 各フィールドに値を挿入 */
	public void setSchedule(Schedule schedule) {
		setScheduleID(schedule.getID());
		setStartDate(schedule.getStartDateTime());
		setStartTime(schedule.getStartDateTime());
		setEndDate(schedule.getStartDateTime());
		setEndTime(schedule.getStartDateTime());
		setTitle(schedule.getTitle());
		ArrayList<String> texts = schedule.getTexts();
		String text = "";
		for(int i =0;i< texts.size();i++) {
			text += texts.get(i);
		}
		setText(text);
		System.out.println(getStartDate().getText());
	}
	
	/* 各フィールドの値をSchedule型で取得 */
	public Schedule getSchedule() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH時mm分");

		LocalDateTime start = LocalDateTime.parse(getStartDate().getText()+getStartTime().getText(),dtf);
		LocalDateTime end = LocalDateTime.parse(getEndDate().getText()+getEndTime().getText(),dtf);
		ArrayList<String> texts = new ArrayList<>();
		String str = getText().getText();
		String[] strs = str.split("\n");

		for(int i=0; i< strs.length;i++) {
			texts.add(strs[i]);
		}
		
		Schedule schedule = new Schedule(
				-1
				, start
				, end
				, getTitle().getText()
				, texts);
		return schedule;
	}
}
