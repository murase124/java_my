package calen01;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Calen extends JFrame {
	
	private JButton nextButton;
	private JLabel nowLabel;
	private JButton backButton;
	private Day dayGet;
	private DayPanel dayPanel;
	
	public static void main(String[] args) {
		Calen calen = new Calen();
		calen.setSize(500,500);
		calen.setVisible(true);
	}
	
	public Calen() {
		dayGet = new Day();
		dayPanel = dayGet.getDayPanel();
		//タイトルパネル
		JPanel titlePanel = new JPanel();
		nextButton = new JButton(dayGet.getNextMonth() + "月");
		nowLabel =  new JLabel(dayGet.getYear() + "年" + dayGet.getMonth() + "月");
		backButton =  new JButton(dayGet.getBackMonth() + "月");
		nextButton.addActionListener( e -> { dayGet.nextMonth(); updateMonth(); });
		backButton.addActionListener( e -> { dayGet.backMonth(); updateMonth(); });
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
		JPanel weekPanel = new JPanel();
		weekPanel.setLayout(new GridLayout(1, 7, 2, 2));
		for(int i=0;i < dayPanel.WEEKTEXT.length;i++) {
			JLabel label = new JLabel(dayPanel.WEEKTEXT[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			if(i == 0) label.setForeground(Color.RED);//日曜日
			if(i == 6) label.setForeground(Color.BLUE);//土曜日
			weekPanel.add(label);
		}
		
		JPanel calenPanel = new JPanel();
		calenPanel.setLayout(new BoxLayout(calenPanel , BoxLayout.Y_AXIS));
		calenPanel.add(weekPanel);
		calenPanel.add(dayPanel.getDayPanel());
		
		//予定パネル
		JPanel schedule = new JPanel();
		JPanel operation = new JPanel();
		JButton listSchedule = new JButton("リスト");
		JButton addSchedule = new JButton("登録");
		JButton changeSchedule = new JButton("編集");
		JButton deleteSchedule = new JButton("削除");
		CardLayout cardlayout = new CardLayout();
		JPanel switching = new JPanel();
		
		JTextArea scheduleList = new JTextArea();
		scheduleList.setPreferredSize(new Dimension(500, 150));
		operation.setLayout(new BoxLayout(operation , BoxLayout.X_AXIS));
		operation.add(listSchedule);	
		operation.add(Box.createRigidArea(new Dimension(10,10)));
		operation.add(addSchedule);	
		operation.add(Box.createRigidArea(new Dimension(10,10)));
		operation.add(changeSchedule);	
		operation.add(Box.createRigidArea(new Dimension(10,10)));
		operation.add(deleteSchedule);	

		switching.setLayout(cardlayout);
		switching.add(scheduleList, "scheduleList");

		schedule.setLayout(new BoxLayout(schedule , BoxLayout.Y_AXIS));
		schedule.add(Box.createRigidArea(new Dimension(500,20)));
		schedule.add(operation);
		schedule.add(Box.createRigidArea(new Dimension(500,10)));
		schedule.add(switching);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, schedule);
	}
	
	public void updateMonth() {
		nextButton.setText(dayGet.getNextMonth() + "月");
		nowLabel.setText(dayGet.getYear() + "年" + dayGet.getMonth() + "月");
		backButton.setText(dayGet.getBackMonth() + "月");
	}
}

