package calen01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DayPanel1 {
	
	private final int DAYBUTTONMAX = 42;
	private ArrayList<JButton> dayButton = new ArrayList<>();
	public ArrayList<JButton> getDayButton() {
		return dayButton;
	}
	public JButton getDayButton(int key) {
		return dayButton.get(key);
	}
	public JButton setDayButton(int key, String values) {
		getDayButton(key).setText(values);
		return dayButton.get(key);
	}
	public JButton setDayButton(JButton values) {
		dayButton.add(values);
		return values;
	}
	public void setDayButton(ArrayList<Integer> TimeDay) {
		for(int i=0;i < DAYBUTTONMAX;i++) setDayButton(i, String.valueOf(TimeDay.get(i)));
	}

	// setDayButton(id, String.valueOf(day));

	
	
	private ArrayList<JButton> scheduleID;//各日のJButtonを表示
	
	private JPanel dayPanel;//各日のJButtonを表示
	public JPanel getDayPanel() {
		return dayPanel;
	}
	
	DayPanel1(){
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(6, 7, 2, 3));

		for(int i=0;i < DAYBUTTONMAX;i++) {
			JButton jbutton = setDayButton(new JButton());
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
