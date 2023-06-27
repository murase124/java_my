package calen02.schedule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

public class ListPane{
	public final String newLine = "\r\n";

	private JTextPaneMy scheduleListPane;
	private JScrollPane scrollpane;
	private JPanel panel;
	public JPanel getPanel() {
		return panel;
	}
	JViewport vBar;

	//スケジュールディスプレイに追加
	public void updateSchedule(List<Schedule> daySchedule) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		scheduleListPane.clearText();
		for(int i=0;i < daySchedule.size();i++)
		scheduleListPane.append(
				daySchedule.get(i).getStartDate().format(format) 
				+ " " + daySchedule.get(i).getEndDate().format(format) 
				+ " " +daySchedule.get(i).getTitle() 
				+ newLine);
		scheduleListPane.append("abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
		scheduleListPane.append("a" + newLine);
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
	private JPanel createOperationPane(ArrayList<JButton> buttons) {
		JPanel operationPane = new JPanel();
		for(int i=0;i < buttons.size();i++) {
			JButton button = buttons.get(i);
			operationPane.add(button);
			if(i != buttons.size()-1)operationPane.add(Box.createRigidArea(new Dimension(10,10)));
		}
		return operationPane;
	}
	
	public JPanel createPanel(ArrayList<JButton> buttons) {		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.add(createOperationPane(buttons), BorderLayout.NORTH);
		panel.add(createDisplayPane(), BorderLayout.CENTER);

		return panel;
	}
	
}
