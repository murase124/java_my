package calen05.mainFrame.scheduleDisplayPanel.panels.Detail;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calen05.mainFrame.scheduleDisplayPanel.panels.scheduleDetailPane.ScheduleDetailPane;
import calen05.mainFrame.scheduleDisplayPanel.switchingPanel.OperationPanel;
import calen05.scheduleManagement.ScheduleMmanagement;

public class DetailPanel extends ScheduleDetailPane{

	public DetailPanel(OperationPanel operationPanel) {
		setOperationPanel(operationPanel);
		setEdit(false);
		setLayout(new BorderLayout());

		add(createSwitchMovePane(), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
	}
	
	public void setEdit(Boolean flg) {
		getStartDate().setEditable(flg);
		getStartTime().setEditable(flg);
		getEndDate().setEditable(flg);
		getEndTime().setEditable(flg);
		getTitle().setEditable(flg);
		getText().setEditable(flg);
	}
	
	/* パネル切り替えボタン作成用 */
	private JButton editButton;
	private JButton delButton;
	private Boolean editModFlg = false;
	public Boolean isEditModFlg() {
		return editModFlg;
	}

	public void setEditModFlg(Boolean editModFlg) {
		this.editModFlg = editModFlg;
	}

	private Boolean keepStateFlg = true;
	public Boolean isKeepState() {
		return keepStateFlg;
	}
	public void setKeepState(Boolean keepModFlg) {
		this.keepStateFlg = keepModFlg;
	}

	@Override
	public JPanel createSwitchMovePane() {
		JPanel jpanel = new JPanel();
		jpanel.add(getOperationPanel().getSwitchingButton(OperationPanel.LISTPANEL));
		jpanel.add(getOperationPanel().getSwitchingButton(OperationPanel.ADDPANEL));
		delButton = new JButton("削除");
		delButton.addActionListener(e -> keepProcess());
		for (int i=0; i< getJTextField().length; i++) {
			getJTextField()[i].addKeyListener(new KeyListener() {
				@Override public void keyTyped(KeyEvent e) {}
				@Override public void keyReleased(KeyEvent e) {}
				@Override
				public void keyPressed(KeyEvent e) {
					setKeepState(false);
					delButton.setEnabled(true);
				}
			});
		}
		getText().addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				setKeepState(false);
				delButton.setEnabled(true);
			}
		});
		jpanel.add(delButton);
		getOperationPanel();
		//編集へ移行
		editButton = new JButton("編集");
		editButton.addActionListener(e -> editModButton(e));
		jpanel.add(editButton);
		return jpanel;
	}
	public void editModButton(ActionEvent e) {
		if(isEditModFlg()) {
			if(!isKeepState()) {	
				int let = 1;
				let = JOptionPane.showConfirmDialog(null, "保存しないでよろしいですか？", "警告", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				if(let == JOptionPane.YES_OPTION) {
					setKeepState(true);
					setSchedule(ScheduleMmanagement.getOneSchedule());
				}else {
					return;
				}
			}
			
			setEdit(false);
			editButton.setText("編集");
			delButton.setText("削除");
			setEditModFlg(false);
			delButton.setEnabled(true);
		}else if(!isEditModFlg()) {
			
			setEdit(true);
			delButton.setText("保存");
			editButton.setText("完了");
			setEditModFlg(true);
			delButton.setEnabled(false);
		}
	}
	
	public Boolean keepProcess() {
		setKeepState(true);
		//DBMmanagement.updateSchedule(getSchedule());
		ScheduleMmanagement.updateOneSchedule();
		return true;
	}
	
	
}
