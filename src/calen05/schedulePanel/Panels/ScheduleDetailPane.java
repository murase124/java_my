package calen05.schedulePanel.Panels;

import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calen05.ScheduleDTO.Schedule;
import calen05.schedulePanel.OperationPanel;

public abstract class ScheduleDetailPane extends JPanel {
	private OperationPanel operationPanel;
	public OperationPanel getOperationPanel() {
		return operationPanel;
	}
	public void setOperationPanel(OperationPanel switchingPanel) {
		this.operationPanel = switchingPanel;
	}

	private DetailItem item = new DetailItem();
	public DetailItem getItem() {
		return item;
	}

	
	public void setSchedule(Schedule schedule) {
		item.setStartDate(schedule.getStartDateTime());
		item.setStartTime(schedule.getStartDateTime());
		item.setEndDate(schedule.getStartDateTime());
		item.setEndTime(schedule.getStartDateTime());
		item.setTitle(schedule.getTitle());
		ArrayList<String> texts = schedule.getTexts();
		String text = "";
		for(int i =0;i< texts.size();i++) {
			text += texts.get(i);
		}
		item.setText(text);
	}
	public Schedule getSchedule() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH時mm分");

		LocalDateTime start = LocalDateTime.parse(item.getStartDate().getText()+item.getStartTime().getText(),dtf);
		LocalDateTime end = LocalDateTime.parse(item.getEndDate().getText()+item.getEndTime().getText(),dtf);
		ArrayList<String> texts = new ArrayList<>();
		String str = item.getText().getText();
		String[] strs = str.split("\n");

		for(int i=0; i< strs.length;i++) {
			texts.add(strs[i]);
		}
		
		Schedule schedule = new Schedule(
				-1
				, start
				, end
				, item.getTitle().getText()
				, texts);
		return schedule;
	}
	
	public JPanel createDisplayPane(){
		/* 上　日時パネル */
		JPanel startPane = new JPanel();
		startPane.add(item.getStartDateTime());
		startPane.add(item.getStartDate());
		startPane.add(item.getStartTime());
		item.getStartDate().setPreferredSize(new Dimension(80, 17));
		item.getStartTime().setPreferredSize(new Dimension(70, 17));
		startPane.setBorder(new EmptyBorder(0, 0, 0, -30));
		
		JPanel endPane = new JPanel();
		endPane.add(item.getEndDateTime());
		endPane.add(item.getEndDate());
		endPane.add(item.getEndTime());
		item.getEndDate().setPreferredSize(new Dimension(80, 17));
		item.getEndTime().setPreferredSize(new Dimension(70, 17));
		endPane.setBorder(new EmptyBorder(0, -30, 0, 0));
		
		JPanel topPane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
		topPane.add(startPane);
		topPane.add(endPane);
		
		/* 下　テキストパネル */
		JPanel titlepane = new JPanel();
		titlepane.add(item.getTitle());
		item.getTitle().setPreferredSize(new Dimension(370, 23));
		titlepane.setBorder(new EmptyBorder(-2, 0, -2, 0));

		JPanel textepane = new JPanel();
		textepane.add(item.getText());
		item.getText().setPreferredSize(new Dimension(400, 115));
		textepane.setBorder(new EmptyBorder(-10, 0, 0, 0));
		
		JPanel underPane = new JPanel();
		underPane.setLayout(new BoxLayout(underPane, BoxLayout.Y_AXIS));
		underPane.add(titlepane);
		underPane.add(textepane);
		
		/* まとめパネル */
		JPanel jpenel = new JPanel();
		jpenel.setLayout(new BoxLayout(jpenel, BoxLayout.Y_AXIS));
		jpenel.add(topPane);
		jpenel.add(underPane);
		return jpenel;
	}
	
	abstract public JPanel createSwitchMovePane();

}
