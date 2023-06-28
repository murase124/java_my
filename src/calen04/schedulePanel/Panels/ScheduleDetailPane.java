package calen04.schedulePanel.Panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public abstract class ScheduleDetailPane extends JPanel {
	
	private JLabel startDateTime = new JLabel("開始日時");
	private JLabel endDateTime = new JLabel("終了日時");
	private JTextField startDate = new JTextField();
	private JTextField startTime = new JTextField();
	private JTextField endDate = new JTextField();
	private JTextField endTime = new JTextField();
	private JTextField title = new JTextField();
	private JTextPane text = new JTextPane();
	public JTextField getStartDate() {
		return startDate;
	}

	public JTextField getStartTime() {
		return startTime;
	}

	public JTextField getEndDate() {
		return endDate;
	}

	public JTextField getEndTime() {
		return endTime;
	}

	public JTextField getTitle() {
		return title;
	}

	public JTextPane getText() {
		return text;
	}
	
	public JPanel createDisplayPane(){
		/* 上　日時パネル */
		JPanel startPane = new JPanel();
		startPane.add(startDateTime);
		startPane.add(startDate);
		startPane.add(startTime);
		startDate.setPreferredSize(new Dimension(80, 17));
		startTime.setPreferredSize(new Dimension(70, 17));
		startPane.setBorder(new EmptyBorder(0, 0, 0, -30));
		
		JPanel endPane = new JPanel();
		endPane.add(endDateTime);
		endPane.add(endDate);
		endPane.add(endTime);
		endDate.setPreferredSize(new Dimension(80, 17));
		endTime.setPreferredSize(new Dimension(70, 17));
		endPane.setBorder(new EmptyBorder(0, -30, 0, 0));
		
		JPanel topPane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
		topPane.add(startPane);
		topPane.add(endPane);
		
		/* 下　テキストパネル */
		JPanel titlepane = new JPanel();
		titlepane.add(title);
		title.setPreferredSize(new Dimension(370, 23));
		titlepane.setBorder(new EmptyBorder(-2, 0, -2, 0));

		JPanel textepane = new JPanel();
		textepane.add(text);
		text.setPreferredSize(new Dimension(400, 115));
		textepane.setBorder(new EmptyBorder(-10, 0, 0, 0));
		
		JPanel underPane = new JPanel();
		underPane.setLayout(new BoxLayout(underPane, BoxLayout.Y_AXIS));
		underPane.add(titlepane);
		underPane.add(textepane);
		
		/* まとめパネル */
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
		jpanel.add(topPane);
		jpanel.add(underPane);
		
		return jpanel;
	}
	
	public JPanel createSwitchingPane(ArrayList<JButton> buttons) {
		JPanel operationPane = new JPanel();
		for(int i=0;i < buttons.size();i++) {
			JButton button = buttons.get(i);
			operationPane.add(button);
			if(i != buttons.size()-1)operationPane.add(Box.createRigidArea(new Dimension(10,10)));
		}
		return operationPane;
	}
	
	public ScheduleDetailPane(ArrayList<JButton> buttons) {
		setLayout(new BorderLayout());

		add(createSwitchingPane(buttons), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);

	}
	
}
