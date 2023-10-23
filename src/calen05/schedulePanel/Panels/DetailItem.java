package calen05.schedulePanel.Panels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailItem {

	private JLabel startDateTime;
	private JLabel endDateTime;
	public JLabel getStartDateTime() {
		return startDateTime;
	}
	public JLabel getEndDateTime() {
		return endDateTime;
	}

	private JTextField startDate;
	private JTextField startTime;
	private JTextField endDate;
	private JTextField endTime;
	private JTextField title;
	private JTextArea text;
	public JTextField getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		startDate.setText(date.format(dtf).toString());
	}

	public JTextField getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH時mm分");
		startTime.setText(date.format(dtf).toString());
		
	}

	public JTextField getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		startDate.setText(date.format(dtf).toString());
	}

	public JTextField getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH時mm分");
		startTime.setText(date.format(dtf).toString());
		
	}

	public JTextField getTitle() {
		return title;
	}
	public void setTitle(String titleText) {
		title.setText(titleText);
	}

	public JTextArea getText() {
		return text;
	}
	public void setText(String texts) {
		text.setText(texts);
	}
	
	public DetailItem clearFormat() {
		startDateTime = new JLabel("開始日時");
		endDateTime = new JLabel("終了日時");
		startDate = new JTextField(startDate.getText());
		startTime = new JTextField(startTime.getText());
		endDate = new JTextField(endDate.getText());
		endTime = new JTextField(endTime.getText());
		title = new JTextField(title.getText());
		text = new JTextArea(text.getText());
		return this;
	}
	public DetailItem clear() {
		startDateTime = new JLabel("開始日時");
		endDateTime = new JLabel("終了日時");
		startDate = new JTextField();
		startTime = new JTextField();
		endDate = new JTextField();
		endTime = new JTextField();
		title = new JTextField();
		text = new JTextArea();
		return this;
	}
	
	
	
	public DetailItem(){
		startDateTime = new JLabel("開始日時");
		endDateTime = new JLabel("終了日時");
		startDate = new JTextField();
		startTime = new JTextField();
		endDate = new JTextField();
		endTime = new JTextField();
		title = new JTextField();
		text = new JTextArea();
	}
}
