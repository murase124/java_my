package calen05;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import calen05.DayButtonDTO.DayButton;
import calen05.DayButtonDTO.DayButtons;
import calen05.DayPanel.DayPanel;
import calen05.ScheduleDTO.Schedule;
import calen05.ScheduleDTO.Schedules;
import calen05.schedulePanel.OperationPanel;
import calen05.schedulePanel.Panels.Detail.DetailPanel;
import calen05.schedulePanel.Panels.List.ListPanel;
import calen05.schedulePanel.Panels.add.AddPanel;


public class Calen extends JFrame {
	
	public final String[] SWITCHINGPANELNAME = { "list" , "add"  , "detail" , "edit" };
	public final String[] SWITCHINGPANELNAMEJP = { "一覧" , "追加"  , "詳細" , "編集" };
	private OperationPanel operationPanel;
	
	public final int DAYBUTTONMAX = 42;
	private DayButtons dayLists;
	private Schedules schedules;

	private JButton nextButton;
	private JLabel nowLabel;
	private JButton backButton;
	private DayPanel dayPanel;

	private LocalDateTime dateTime;
	
	public static void main(String[] args) {
		Calen calen = new Calen();
		calen.setSize(500,500);
		calen.setVisible(true);
	}
	
	public Calen() {
		dateTime = LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),1,0,0);

		dayPanel = new DayPanel();
		schedules = new Schedules(dateTime);
		dayLists = new DayButtons(DAYBUTTONMAX, dateTime);
		
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
		
		//予定表示パネル
		operationPanel = new OperationPanel();
		
		/* switchingPanels */
		//概要
		ListPanel listPanel = new ListPanel(operationPanel);
		operationPanel.addPanels(operationPanel.LISTPANEL, listPanel);
		//追加
		AddPanel addPane = new AddPanel(operationPanel);
		operationPanel.addPanels(operationPanel.ADDPANEL, addPane);
		//詳細
		DetailPanel detailPane = new DetailPanel(operationPanel);
		operationPanel.addPanels(operationPanel.DETAILPANEL, detailPane);
		
		
		//日付ボタン　クリック　ー＞　日付渡す
		for(int i=0; i < DAYBUTTONMAX; i++) {
			dayLists.getDayButtons(i).addActionListener(new dayButtonActionListener(schedules, operationPanel));
		}
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, operationPanel);
	}
	
	public void updateMonth() {
		nextButton.setText(dateTime.plusMonths(1).getMonthValue() + "月");
		nowLabel.setText(dateTime.getYear() + "年" + dateTime.getMonthValue() + "月");
		backButton.setText(dateTime.minusMonths(1).getMonthValue() + "月");
	}
	public void backMonthUpdate() {
		dateTime = dateTime.minusMonths(1);
		updateMonth();
		schedules.backSchedule();
		dayLists.backMonths();
	}
	public void nextMonthUpdate() {
		dateTime = dateTime.plusMonths(1);
		updateMonth();
		schedules.nextSchedule();
		dayLists.nextMonths();
	}
	
	class dayButtonActionListener implements ActionListener{
		private Schedules schedules;
		private OperationPanel operationPanel;
		private ListPanel listPanel;
		private AddPanel addPanel;
		private DetailPanel detailPanel;

		
		@Override
		public void actionPerformed(ActionEvent e) {
			DayButton dayButton = (DayButton) e.getSource();
			List<Schedule> daySchedule = schedules.getDaySchedule(dayButton.getDateTime());
			if(operationPanel.getComponent(0).isVisible() == true){
				listPanel.updateSchedule(daySchedule);
			}else if(operationPanel.getComponent(1).isVisible() == true){
			
			}
			
			
			System.out.println(operationPanel.getPanelsComponent(operationPanel.LISTPANEL));
		}
		
		public dayButtonActionListener(Schedules schedules, OperationPanel operationPanel) {
			this.schedules = schedules;
			this.operationPanel = operationPanel;
			this.listPanel = (ListPanel) operationPanel.getPanelsComponent(operationPanel.LISTPANEL);
			this.addPanel = (AddPanel) operationPanel.getPanelsComponent(operationPanel.ADDPANEL);
			this.detailPanel = (DetailPanel) operationPanel.getPanelsComponent(operationPanel.DETAILPANEL);
		}
	}
}