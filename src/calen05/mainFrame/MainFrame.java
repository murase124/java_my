package calen05.mainFrame;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import calen05.dateTimeManagement.DateTimeMmanagement;
import calen05.mainFrame.dayPanel.DayPanel;
import calen05.mainFrame.dayPanel.dayButtonDTO.DayButton;
import calen05.mainFrame.scheduleDisplayPanel.switchingPanel.OperationPanel;
import calen05.mainFrame.titlePanel.TitlePalel;
import calen05.mainFrame.weekPane.WeekPane;

public class MainFrame extends JFrame  {
	
	public static int DAYBUTTONNUM() {
		return 42;
	}
	
	private JPanel calenPanel;
	private TitlePalel titlePanel;
	private WeekPane weekPane;
	private DayPanel dayPanel;
	private OperationPanel operationPanel;
	public OperationPanel getOperationPanel() {
		return operationPanel;
	}

	public MainFrame() {
		
		//タイトルパネル
		titlePanel = new TitlePalel(DateTimeMmanagement.getNow());
		titlePanel.addNextActionListener(e-> DateTimeMmanagement.setNext());
		titlePanel.addPrevActionListener(e-> DateTimeMmanagement.setPrev());
		
		//日付パネル
		calenPanel = new JPanel();
		calenPanel.setLayout(new BoxLayout(calenPanel , BoxLayout.Y_AXIS));
		weekPane = new WeekPane();
		dayPanel = new DayPanel(DAYBUTTONNUM());
		
		calenPanel.add(weekPane);//曜日ラベル
		calenPanel.add(dayPanel);//日付ボタン
		
		//予定表示パネル
		operationPanel = new OperationPanel();
				
		//日付ボタン押下アクション
		dayPanel.addDayButtonsActionListener(e -> operationPanel.setDay((DayButton) e.getSource()));

		
		DateTimeMmanagement.addNextListener(() -> dayPanel.updetaDay(DateTimeMmanagement.getNow()));
		DateTimeMmanagement.addNextListener(() -> titlePanel.updateMonth(DateTimeMmanagement.getNow()));
		DateTimeMmanagement.addNowListener(() -> dayPanel.updetaDay(DateTimeMmanagement.getNow()));
		DateTimeMmanagement.addNowListener(() -> titlePanel.updateMonth(DateTimeMmanagement.getNow()));
		DateTimeMmanagement.addPrevListener(() -> dayPanel.updetaDay(DateTimeMmanagement.getNow()));
		DateTimeMmanagement.addPrevListener(() -> titlePanel.updateMonth(DateTimeMmanagement.getNow()));

		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(BorderLayout.NORTH, titlePanel);
		getContentPane().add(BorderLayout.CENTER, calenPanel);
		getContentPane().add(BorderLayout.SOUTH, operationPanel);
		
		setSize(500,500);
		setVisible(true);
	}
}
