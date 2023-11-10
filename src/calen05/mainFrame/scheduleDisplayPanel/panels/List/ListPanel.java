package calen05.mainFrame.scheduleDisplayPanel.panels.List;

import java.awt.BorderLayout;
import java.awt.Point;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import calen05.mainFrame.scheduleDisplayPanel.switchingPanel.OperationPanel;
import calen05.scheduleManagement.Schedule;

public class ListPanel extends JPanel{
	private final OperationPanel operationPanel;
	public final String newLine = "\r\n";

	private JTextPaneMy scheduleListPane;
	private JScrollPane scrollpane;
	//private JViewport vBar;
	
	private JButton listPanel; 
	private JButton addPanel;
	private JButton deletPanel;
	private JButton detailPanel;
	public void setDetailPanelEnabled(Boolean enabled) {
		detailPanel.setEnabled(enabled);
	}
	
	private List<Schedule> daySchedule;
	public Schedule getScheduleID(int selectID) {
		return daySchedule.stream().filter(daySchedule -> daySchedule.getID() == selectID).collect(Collectors.toList()).get(0);
	}
	
	public void addListPanelListener(ListPanelListener listener) {
		scheduleListPane.addListPanelListener(listener);
	}
	
	//スケジュールディスプレイに追加
	public void updateSchedule(List<Schedule> daySchedule) {
		this.daySchedule = daySchedule;
		this.daySchedule.sort((a,b)-> a.getID() - b.getID());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		scheduleListPane.clearText();
		for(int i=0;i < this.daySchedule.size();i++)
		scheduleListPane.append(
				this.daySchedule.get(i).getStartDateTime().format(format) 
				+ " " + this.daySchedule.get(i).getEndDateTime().format(format) 
				+ " " +this.daySchedule.get(i).getTitle() 
				+ newLine);
		
		scheduleListPane.updateUI();
		scrollpane.getViewport().setViewPosition(new Point(0, 0));
	}
	
	//スケジュールディスプレイ作成
	private JScrollPane createDisplayPane() {
		scheduleListPane = new JTextPaneMy();
		scheduleListPane.setEditable(false);

		scrollpane = new JScrollPane();
		scrollpane.setViewportView(scheduleListPane);
		scrollpane.setBorder(new EmptyBorder(0, 4, 3, 3));
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scrollpane;
	}
	//カードレインアウト切り替えボタン作成
	private JPanel createSwitchingPane() {
		JPanel jpanel = new JPanel();
		listPanel = operationPanel.getSwitchingButton(OperationPanel.LISTPANEL);
		jpanel.add(listPanel);
		addPanel = operationPanel.getSwitchingButton(OperationPanel.ADDPANEL);
		jpanel.add(addPanel);
		deletPanel = new JButton("削除");
		jpanel.add(deletPanel);
		detailPanel = operationPanel.getSwitchingButton(OperationPanel.DETAILPANEL);
		jpanel.add(detailPanel);
				
		return jpanel;
	}
	
	public ListPanel(OperationPanel operationPanel) {
		this.operationPanel = operationPanel;
		setLayout(new BorderLayout());

		add(createSwitchingPane(), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
		setDetailPanelEnabled(false);
	}
	
}
