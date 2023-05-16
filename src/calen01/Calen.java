package calen01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Calen extends JFrame {
	
	Calendar calendar;

	private JPanel weekPanel;
	private JPanel calenPanel;
	private JPanel titlePanel;
	private JPanel schedule;
	private JTextArea scheduleList;
	private JButton nextButton;
	private JLabel nowLabel;
	private JButton backButton;
	private DayList dayList;
	private DayPanel dayPanel;
	
	public static void main(String[] args) {
		Calen calen = new Calen();
		calen.setSize(500,500);
		calen.setVisible(true);
	}
	
	public Calen() {
		dayList = new DayList();
		dayPanel = new DayPanel();
		dayPanel.setDayButton(dayList.getTimeDay());
		//タイトルパネル
		titlePanel = new JPanel();
		nextButton = new JButton(dayList.getNextMonth() + "月");
		nowLabel =  new JLabel(dayList.getYear() + "年" + dayList.getMonth() + "月");
		backButton =  new JButton(dayList.getBoackMonth() + "月");
		nextButton.addActionListener( e -> { dayList.nextMonth(); updateMonth(); });
		backButton.addActionListener( e -> { dayList.backMonth(); updateMonth(); });
		//レインアウト
		GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 3, 10, 20);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(backButton, gbc);
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 3, 10, 0);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(nowLabel, gbc);
	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    gbc.insets = new Insets(10, 20, 10, 3);
	    gbc.fill = GridBagConstraints.VERTICAL;
	    layout.setConstraints(nextButton, gbc);
	    titlePanel.setLayout(layout);
		nowLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		nextButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
		backButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));

		titlePanel.add(backButton);
		nowLabel.setBackground(Color.red);
		titlePanel.add(nowLabel);
		titlePanel.add(nextButton);
		
		//日付パネル
		weekPanel = new JPanel();
		weekPanel.setLayout(new GridLayout(1, 7, 2, 2));
		for(int i=0;i < dayPanel.WEEKTEXT.length;i++) {
			JLabel label = new JLabel(dayPanel.WEEKTEXT[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			if(i == 0) label.setForeground(Color.RED);//日曜日
			if(i == 6) label.setForeground(Color.BLUE);//土曜日
			weekPanel.add(label);
		}
		
		calenPanel = new JPanel();
		calenPanel.setLayout(new BoxLayout(calenPanel , BoxLayout.Y_AXIS));
		calenPanel.add(weekPanel);
		calenPanel.add(dayPanel.getDayPanel());
		
		//操作パネル
		schedule = new JPanel();
		JPanel addSchedulea = new JPanel();
		JTextField input = new JTextField();
		JButton addtext = new JButton("登録");
		scheduleList = new JTextArea();
		scheduleList.setPreferredSize(new Dimension(500, 150));
		addSchedulea.setLayout(new BoxLayout(addSchedulea , BoxLayout.X_AXIS));
		addSchedulea.add(Box.createRigidArea(new Dimension(10,10)));
		addSchedulea.add(input);
		addSchedulea.add(Box.createRigidArea(new Dimension(10,10)));
		addSchedulea.add(addtext);	
		addSchedulea.add(Box.createRigidArea(new Dimension(10,10)));
		schedule.setLayout(new BoxLayout(schedule , BoxLayout.Y_AXIS));
		schedule.add(Box.createRigidArea(new Dimension(500,20)));
		schedule.add(addSchedulea);
		schedule.add(Box.createRigidArea(new Dimension(500,10)));
		schedule.add(scheduleList);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, schedule);
	}
	
	public void updateMonth() {
		nextButton.setText(dayList.getNextMonth() + "月");
		nowLabel.setText(dayList.getYear() + "年" + dayList.getMonth() + "月");
		backButton.setText(dayList.getBoackMonth() + "月");
		dayPanel.setDayButton(dayList.getTimeDay());
	}
}

