package calen04.schedulePanel.Panels.List;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

import calen04.ScheduleDTO.Schedule;
import calen04.schedulePanel.CardlayoutActionListener;

public class ListPanel extends JPanel{
	public final String newLine = "\r\n";

	private JTextPaneMy scheduleListPane;
	private JScrollPane scrollpane;
	JViewport vBar;

	//スケジュールディスプレイに追加
	public void updateSchedule(List<Schedule> daySchedule) {
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
	public JButton switchingButton(String text, ActionListener actionListener) {
		JButton jbutton = new JButton(text);
		if(actionListener != null) jbutton.addActionListener(actionListener);
		return jbutton;
	}
	public JButton switchingButton(String text, ActionListener[] actionListener) {
		JButton jbutton = new JButton(text);
		for(int i=0;i < actionListener.length;i++)
		if(actionListener != null) jbutton.addActionListener(actionListener[i]);
		return jbutton;
	}
	
	private JPanel createSwitchingPane(ArrayList<CardlayoutActionListener> switchingActionListeners) {
		JPanel jpanel = new JPanel();
		jpanel.add(switchingButton("一覧", switchingActionListeners.get(0)));
		jpanel.add(switchingButton("追加", switchingActionListeners.get(1)));
		jpanel.add(switchingButton("削除", (ActionListener) null));
		jpanel.add(switchingButton("詳細", switchingActionListeners.get(2)));
				
		return jpanel;
	}
	
	public ListPanel(ArrayList<CardlayoutActionListener> actionListeners) {		
		setLayout(new BorderLayout());

		add(createSwitchingPane(actionListeners), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);

	}
	
}
