package calen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Calen01 extends JFrame{
	private static Map<String, JButton> dayButton = new HashMap<>();//
	static Calendar calendar;

	JLabel label;
	JTextArea area;
	static JPanel panel;


	public static void main(String[] args) {
		
		Calen01 calen = new Calen01();
		
		calen.setSize(650,500);
		calen.setVisible(true);
	}
	
	public Calen01() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 7, 5, 5));
		label = new JLabel();
		label.setText("::スケジュール表::");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
		area = new JTextArea();
		area.setPreferredSize(new Dimension(650, 100));
		
		setDay();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, label);
		getContentPane().add(BorderLayout.CENTER, panel);
		getContentPane().add(BorderLayout.SOUTH, area);
		
		
		
	}
	
	public static void setDay() {
		calendar = Calendar.getInstance();		
		calendar.set(Calendar.DATE, 1 );
		createDayButton();
	}
	public void setDay(int year,int month,int day) {
		calendar.set(year, month, day);
		createDayButton();
	}
	public static void nextDay(Calendar nextcalendar) {
		nextcalendar.add(Calendar.MONTH, 1);
	}
	public static void backDay(Calendar backcalendar) {
		backcalendar.add(Calendar.MONTH, -1);
	}

	public static void createDayButton() {
		dayButton.clear();
		
		Calendar nextcalendar = (Calendar) calendar.clone();
		nextDay(nextcalendar);
		Calendar backcalendar = (Calendar) calendar.clone();
		backDay(backcalendar);
		
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		int nextlastDay = nextcalendar.getActualMaximum(Calendar.DATE);
		int backlastDay = backcalendar.getActualMaximum(Calendar.DATE);
		System.out.println(lastDay);
		System.out.println(nextlastDay);
		System.out.println(backlastDay);

		int firstDate = calendar.get( Calendar.DAY_OF_WEEK );
		int nextfirstDate = nextcalendar.get( Calendar.DAY_OF_WEEK );
		System.out.println(nextfirstDate);

		
		for(int i =--firstDate; i > 0;i--) {
			dayButton.put(backcalendar.get(Calendar.DATE)+"/" +(nextlastDay-i), new JButton((nextlastDay-i)+""));
			panel.add(dayButton.get(backcalendar.get(Calendar.DATE)+"/" +(nextlastDay-i)));
		}
		for(int i =0; i < lastDay;i++) {
			dayButton.put(calendar.get(Calendar.DATE)+"/" +(i+1), new JButton(String.valueOf(i+1)));
			panel.add(dayButton.get(calendar.get(Calendar.DATE)+"/" +(i+1)));
		}
		for(int i =nextfirstDate; i < 15;i++) {
			System.out.println(i-nextfirstDate+1);

			dayButton.put(nextcalendar.get(Calendar.DATE)+"/" +(i-nextfirstDate+1), new JButton((i-nextfirstDate+1)+""));
			panel.add(dayButton.get(nextcalendar.get(Calendar.DATE)+"/" +(i-nextfirstDate+1)));
		}
	}

}
