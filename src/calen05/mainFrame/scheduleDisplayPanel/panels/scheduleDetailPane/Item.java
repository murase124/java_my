package calen05.mainFrame.scheduleDisplayPanel.panels.scheduleDetailPane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Item extends JPanel {

	private int scheduleID;
	public int getScheduleID() {
		return scheduleID;
	}
	protected void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	/* ラベル */
	private JLabel startDateTime;
	public JLabel getStartDateTime() {
		return startDateTime;
	}
	
	private JLabel endDateTime;
	public JLabel getEndDateTime() {
		return endDateTime;
	}
	
	/* テキストフィールド */
	private JTextField startDate;
	public JTextField getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		startDate.setText(date.format(dtf).toString());
	}
	
	private JTextField startTime;
	public JTextField getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH時mm分");
		startTime.setText(date.format(dtf).toString());
		
	}
	
	private JTextField endDate;
	public JTextField getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		endDate.setText(date.format(dtf).toString());
	}

	private JTextField endTime;
	public JTextField getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH時mm分");
		endTime.setText(date.format(dtf).toString());
		
	}
	
	private JTextField title;
	public JTextField getTitle() {
		return title;
	}
	public void setTitle(String titleText) {
		title.setText(titleText);
	}
	
	private JTextArea text;
	public JTextArea getText() {
		return text;
	}
	public void setText(String texts) {
		text.setText(texts);
	}
	
	/*フィールドのフォーマットを消す？ */
	public Item clearFormat() {
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
	
	/*フィールドの内容を消す？ */
	public Item clear() {
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
	
	/* 初期化 */
	public Item(){
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
