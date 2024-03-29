package calen04;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import calen04.DayButtonDTO.DayButton;
import calen04.DayButtonDTO.DayButtons;
import calen04.DayPanel.DayPanel;
import calen04.ScheduleDTO.Schedules;
import calen04.schedulePanel.ScheduleProcess;
import calen04.schedulePanel.SwitchingPanel;
import calen04.schedulePanel.Panels.Detail.DetailPanel;
import calen04.schedulePanel.Panels.Edit.EditPanel;
import calen04.schedulePanel.Panels.List.ListPanel;
import calen04.schedulePanel.Panels.add.AddPanel;


public class Calen extends JFrame {
	
	public final String[] SWITCHINGPANELNAME = { "list" , "add"  , "detail" , "edit" };
	private SwitchingPanel scheduleSwitchingPane;
	
	public final int DAYBUTTONMAX = 42;
	private DayButtons dayLists;
	private Schedules schedules;

	private JButton nextButton;
	private JLabel nowLabel;
	private JButton backButton;
	private DayPanel dayPanel;

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

		dayPanel = new DayPanel();
		schedules = new Schedules(dateTime);
		dayLists = new DayButtons(DAYBUTTONMAX);
		
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
		calenPanel.add(dayPanel.createWeekPane());//曜日ラベル
		calenPanel.add(dayPanel.createDayPanel(dayLists));//日付ボタン
		
		//予定パネル
		scheduleSwitchingPane = new SwitchingPanel();
		for(int i =0; i < SWITCHINGPANELNAME.length;i++) {
			scheduleSwitchingPane.createPane(SWITCHINGPANELNAME[i]);
			scheduleSwitchingPane.getPanels(i).setPreferredSize(new Dimension(470, 220));
			scheduleSwitchingPane.getPanels(i).setLayout(new BorderLayout());
		}
		/* switchingPanels */
		//概要
		ListPanel listPanel = new ListPanel(scheduleSwitchingPane.getActionListener());
		scheduleSwitchingPane.addPanels(0, listPanel);
		//追加
		AddPanel addPane = new AddPanel(scheduleSwitchingPane.getActionListener());
		scheduleSwitchingPane.addPanels(1, addPane);
		//詳細
		DetailPanel detailPane = new DetailPanel(scheduleSwitchingPane.getActionListener());
		scheduleSwitchingPane.addPanels(2, detailPane);
		//編集
		EditPanel editPane = new EditPanel(scheduleSwitchingPane.getActionListener());
		scheduleSwitchingPane.addPanels(3, editPane);
		
		//日付ボタン　クリック　ー＞　日付渡す
		for(int i=0; i < DAYBUTTONMAX; i++) {
			dayLists.getDayButtons(i).addActionListener(new dayButtonActionListener(new ScheduleProcess(scheduleSwitchingPane), listPanel, schedules));
		}
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, scheduleSwitchingPane);
		
		
	}
	
	public void updateMonth() {
		nextButton.setText(plusMonths().getMonthValue() + "月");
		nowLabel.setText(dateTime.getYear() + "年" + dateTime.getMonthValue() + "月");
		backButton.setText(minusMonths().getMonthValue() + "月");
		dayLists.updetaDayButton(dateTime);;
	}
	public void backMonthUpdate() {
		dateTime = minusMonths();
		updateMonth();
		schedules.backSchedule(dateTime);
	}
	public void nextMonthUpdate() {
		dateTime = plusMonths();
		updateMonth();
		schedules.nextSchedule(dateTime);
	}
	
	class dayButtonActionListener implements ActionListener{
		private ScheduleProcess schedulePane;
		private Schedules schedules;
		private ListPanel listPanel;
		@Override
		public void actionPerformed(ActionEvent e) {
			DayButton dayButton = (DayButton) e.getSource();
			this.schedulePane.dayButtonAction(listPanel, dayButton.getDateTime(), this.schedules);
		}
		
		public dayButtonActionListener(ScheduleProcess schedulePane, ListPanel listPanel, Schedules schedules) {
			this.schedulePane = schedulePane;
			this.listPanel = listPanel;
			this.schedules = schedules;
		}
	}
}

