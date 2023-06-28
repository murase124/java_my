package calen04.DayButtonDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JButton;

public class DayButton extends JButton {
	
	LocalDate date;
	public LocalDate getDate() {
		return date;
	}
	public LocalDateTime getDateTime() {
		return  date.atStartOfDay();
	}
	
	public void setDate(LocalDate date, String text) {
		this.date = date;
		this.setText(text);
	}	
}
