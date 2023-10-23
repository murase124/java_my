package calen05.schedulePanel.Panels.List;

import java.awt.BorderLayout;
import java.awt.Point;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import calen05.ScheduleDTO.Schedule;
import calen05.schedulePanel.OperationPanel;

public class ListPanel extends JPanel{
	private final OperationPanel operationPanel;
	public final String newLine = "\r\n";

	private JTextPaneMy scheduleListPane;
	private JScrollPane scrollpane;
	JViewport vBar;

	private List<Schedule> daySchedule;
	public Schedule getSchedule(int selectID) {
		return daySchedule.stream().filter(daySchedule -> daySchedule.getID() == selectID).collect(Collectors.toList()).get(0);//()) && (daySchedule.getEndDateTime().isAfter(start) || daySchedule.getEndDateTime().isEqual(start))).collect(Collectors.toList());
		//return daySchedule.stream().filter(daySchedule -> daySchedule.getID()()) && (daySchedule.getEndDateTime().isAfter(start) || daySchedule.getEndDateTime().isEqual(start))).collect(Collectors.toList());
		//return daySchedule.indexOf()
	}
	
	//スケジュールディスプレイに追加
	public void updateSchedule(List<Schedule> daySchedule) {
		this.daySchedule = daySchedule;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		scheduleListPane.clearText();
		for(int i=0;i < daySchedule.size();i++)
		scheduleListPane.append(
				daySchedule.get(i).getStartDateTime().format(format) 
				+ " " + daySchedule.get(i).getEndDateTime().format(format) 
				+ " " +daySchedule.get(i).getTitle() 
				+ newLine);
		scheduleListPane.append(newLine);
		scheduleListPane.append("aaa" + newLine);
		scheduleListPane.append("aaa" + newLine);
		scheduleListPane.updateUI();
		scrollpane.getViewport().setViewPosition(new Point(0, 0));
		scheduleListPane.changeStyle();
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
		jpanel.add(operationPanel.getSwitchingButton(operationPanel.LISTPANEL));
		jpanel.add(operationPanel.getSwitchingButton(operationPanel.ADDPANEL));
		jpanel.add(new JButton("削除"));
		jpanel.add(operationPanel.getSwitchingButton(operationPanel.DETAILPANEL));
				
		return jpanel;
	}
	
	public ListPanel(OperationPanel operationPanel) {
		this.operationPanel = operationPanel;
		setLayout(new BorderLayout());

		add(createSwitchingPane(), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);

	}
	
}
