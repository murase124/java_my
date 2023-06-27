package calen03;

import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

public class DayList {
	
	LocalDate date;
	public LocalDate getDate() {
		return (LocalDate) date;
	}
	
	private JButton dayButton = new JButton();
	public JButton getDayButton() {
		return dayButton;
	}
	public void addActionListener(ActionListener e) {
		dayButton.addActionListener(e);;
	}

	public void setDate(int year, int month, int day, String text) {
		date = LocalDate.of(year,month,day);
		this.dayButton.setText(text);
	}	
	
	public DayList(JButton values){
		this.dayButton = values;
	}
}
