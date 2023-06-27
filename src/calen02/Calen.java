package calen02;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import calen02.schedule.SchedulePane;

public class Calen extends JFrame {
	
	private JButton nextButton;
	private JLabel nowLabel;
	private JButton backButton;
	private DayPanel dayGet;
	private SchedulePane schedulePane;
	private LocalDateTime dateTime;
	private LocalDateTime plusMonths() {
		return dateTime.plusMonths(1);
	}
	private LocalDateTime minusMonths() {
		return dateTime.minusMonths(1);
	}
	
	
	public static void main(String[] args) {
		Calen calen = new Calen();
		calen.setSize(500,500);
		calen.setVisible(true);
	}
	
	public Calen() {
		dateTime = LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),1,0,0);

		dayGet = new DayPanel(dateTime);
	
		//タイトルパネル
		JPanel titlePanel = new JPanel();
		nowLabel =  new JLabel();
		nextButton = new JButton();
		backButton =  new JButton();
		updateMonth();
		nextButton.addActionListener( e -> { nextMonthUpdate(); });
		backButton.addActionListener( e -> { backMonthUpdate(); });
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
		calenPanel.add(dayGet.getWeekPane());//曜日ラベル
		calenPanel.add(dayGet.getDayPanel());//日付ボタン
		
		//予定パネル
		schedulePane = new SchedulePane(dateTime);
		JPanel switchingPane = schedulePane.getPanel();
	
		//日付ボタン　クリック　ー＞　日付渡す
		for(int i=0; i < dayGet.DAYBUTTONMAX; i++) {
			int id = i;
			dayGet.getDayList(i).addActionListener(e -> {
				schedulePane.dayButtonAction(dayGet.getDayList(id).getDate().atStartOfDay());
			});
		}
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, switchingPane);
	}
	
	public void updateMonth() {
		nextButton.setText(plusMonths().getMonthValue() + "月");
		nowLabel.setText(dateTime.getYear() + "年" + dateTime.getMonthValue() + "月");
		backButton.setText(minusMonths().getMonthValue() + "月");
	}
	public void backMonthUpdate() {
		dateTime = minusMonths();
		dayGet.updetaDayButton(dateTime);;
		schedulePane.backSchedule(dateTime);
		updateMonth();
	}
	public void nextMonthUpdate() {
		dateTime = plusMonths();
		dayGet.updetaDayButton(dateTime);;
		schedulePane.nextSchedule(dateTime);
		updateMonth();
	}
}

