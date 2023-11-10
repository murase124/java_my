package calen05.mainFrame.scheduleDisplayPanel.panels.add;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calen05.mainFrame.scheduleDisplayPanel.panels.scheduleDetailPane.ScheduleDetailPane;
import calen05.mainFrame.scheduleDisplayPanel.switchingPanel.OperationPanel;

public class AddPanel extends ScheduleDetailPane {
	
	public AddPanel(OperationPanel operationPanel) {
		setOperationPanel(operationPanel);
		setLayout(new BorderLayout());

		add(createSwitchMovePane(), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
	}
	
	/* パネル切り替えボタン作成用 */
	@Override
	public JPanel createSwitchMovePane() {
		JPanel jpanel = new JPanel();
		getOperationPanel();
		JButton listButton = new JButton(getOperationPanel().getPanelNameJP(OperationPanel.LISTPANEL));
		listButton.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int let = 1;
					let = JOptionPane.showConfirmDialog(null, "保存しないでよろしいですか？", "警告", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
					if(let == JOptionPane.YES_OPTION) {
						getOperationPanel().getActionListener(OperationPanel.LISTPANEL).actionPerformed(e);
					}
				}
			}
		);
		jpanel.add(listButton);
		JButton addButton = new JButton("登録");

		jpanel.add(addButton);
		return jpanel;
	}
}
