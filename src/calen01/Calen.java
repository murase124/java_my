package calen01;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		nowLabel =  new JLabel(dayGet.getYear() + "年" + dayGet.getMonth() + "月");
		nextButton = new JButton(dayGet.getNextMonth() + "月");
		backButton =  new JButton(dayGet.getBackMonth() + "月");
		nextButton.addActionListener( e -> { dayGet.nextMonth(); updateMonth(); });
		backButton.addActionListener( e -> { dayGet.backMonth(); updateMonth(); });
		//レインアウト
		GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridy = 0;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    
	    gbc.gridx = 2;
	    gbc.insets = new Insets(10, 3, 10, 0);
	    layout.setConstraints(nowLabel, gbc);
	    gbc.gridx = 3;
	    gbc.insets = new Insets(10, 20, 10, 3);
	    layout.setConstraints(nextButton, gbc);
	    gbc.gridx = 0;
	    gbc.insets = new Insets(10, 3, 10, 20);
	    layout.setConstraints(backButton, gbc);
	    titlePanel.setLayout(layout);
		nowLabel.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 18));
		nextButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));
		backButton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 15));

		titlePanel.add(backButton);
		titlePanel.add(nowLabel);
		titlePanel.add(nextButton);
		
		
		//日付パネル
		JPanel calenPanel = new JPanel();
		calenPanel.setLayout(new BoxLayout(calenPanel , BoxLayout.Y_AXIS));
		calenPanel.add(dayPanel.getWeekPane());//曜日ラベル
		calenPanel.add(dayPanel.getDayPanel());//日付ボタン
		
		//予定パネル
		JPanel schedulePane = new JPanel();
		SchedulePanel switchingPanel = new SchedulePanel();
		
		schedulePane.setLayout(new BoxLayout(schedulePane , BoxLayout.Y_AXIS));
		schedulePane.add(Box.createRigidArea(new Dimension(500,20)));
		schedulePane.add(switchingPanel.getOperationPane());
		schedulePane.add(Box.createRigidArea(new Dimension(500,10)));
		schedulePane.add(switchingPanel.getSwitchingPane());
		switchingPanel.createListPanel();
		switchingPanel.createAddPane();
		switchingPanel.createEditPane();
		
		//日付ボタン　クリック　ー＞　日付渡す
		for(int i=0; i < dayPanel.DAYBUTTONMAX; i++) {
			int id = i;
			dayPanel.getDayList(i).getDayButton().addActionListener(e -> {
				int year = dayPanel.getDayList(id).getYear();
				int month = dayPanel.getDayList(id).getMonth();
				int day = dayPanel.getDayList(id).getDay();
				switchingPanel.setList(year + "/" + month + "/" + day);
				
			});
		}
		

		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, schedulePane);
	}
	
	public void updateMonth() {
		nextButton.setText(dayGet.getNextMonth() + "月");
		nowLabel.setText(dayGet.getYear() + "年" + dayGet.getMonth() + "月");
		backButton.setText(dayGet.getBackMonth() + "月");
	}
}

