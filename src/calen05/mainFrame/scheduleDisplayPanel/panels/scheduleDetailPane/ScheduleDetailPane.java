package calen05.mainFrame.scheduleDisplayPanel.panels.scheduleDetailPane;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calen05.mainFrame.scheduleDisplayPanel.switchingPanel.OperationPanel;

public abstract class ScheduleDetailPane extends ItemManagement {
	/* パネル切り替えのオペレーションパネル */
	private OperationPanel operationPanel;
	public OperationPanel getOperationPanel() {
		return operationPanel;
	}
	public void setOperationPanel(OperationPanel operationPanel) {
		this.operationPanel = operationPanel;
	}
	abstract public JPanel createSwitchMovePane();

	public JPanel createDisplayPane(){
		/* 上　日時パネル */
		JPanel startPane = new JPanel();
		startPane.add(getStartDateTime());
		startPane.add(getStartDate());
		startPane.add(getStartTime());
		getStartDate().setPreferredSize(new Dimension(80, 17));
		getStartTime().setPreferredSize(new Dimension(70, 17));
		startPane.setBorder(new EmptyBorder(0, 0, 0, -30));
		
		JPanel endPane = new JPanel();
		endPane.add(getEndDateTime());
		endPane.add(getEndDate());
		endPane.add(getEndTime());
		getEndDate().setPreferredSize(new Dimension(80, 17));
		getEndTime().setPreferredSize(new Dimension(70, 17));
		endPane.setBorder(new EmptyBorder(0, -30, 0, 0));
		
		JPanel topPane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
		topPane.add(startPane);
		topPane.add(endPane);
		
		/* 下　テキストパネル */
		JPanel titlepane = new JPanel();
		titlepane.add(getTitle());
		getTitle().setPreferredSize(new Dimension(370, 23));
		titlepane.setBorder(new EmptyBorder(-2, 0, -2, 0));

		JPanel textepane = new JPanel();
		textepane.add(getText());
		getText().setPreferredSize(new Dimension(400, 115));
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
	

}
