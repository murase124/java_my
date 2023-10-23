package calen05.schedulePanel.Panels.Detail;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import calen05.schedulePanel.OperationPanel;
import calen05.schedulePanel.Panels.ScheduleDetailPane;

public class DetailPanel extends ScheduleDetailPane{

	public DetailPanel(OperationPanel operationPanel) {
		setOperationPanel(operationPanel);
		setEdit(false);
		setLayout(new BorderLayout());

		add(createSwitchMovePane(), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
		getItem().getStartDate().setText("aaa");
	}
	
	public void setEdit(Boolean flg) {
		getItem().getStartDate().setEditable(flg);
		getItem().getStartTime().setEditable(flg);
		getItem().getEndDate().setEditable(flg);
		getItem().getEndTime().setEditable(flg);
		getItem().getTitle().setEditable(flg);
		getItem().getText().setEditable(flg);
	}
	
	/* パネル切り替えボタン作成用 */
	@Override
	public JPanel createSwitchMovePane() {
		JPanel jpanel = new JPanel();
		jpanel.add(getOperationPanel().getSwitchingButton(getOperationPanel().LISTPANEL));
		jpanel.add(getOperationPanel().getSwitchingButton(getOperationPanel().ADDPANEL));
		jpanel.add(new JButton("削除"));
		//編集へ移行
		JButton editButton = new JButton(getOperationPanel().getPanelNameJP(getOperationPanel().DETAILPANEL));
		editButton.addActionListener(e -> {
			if(((JButton) e.getSource()).getText() == getOperationPanel().getPanelNameJP(getOperationPanel().DETAILPANEL)) {
				setEdit(true);
				((JButton) e.getSource()).setText("完了");
			}else if(((JButton) e.getSource()).getText() == "完了") {
				setEdit(false);
				((JButton) e.getSource()).setText(getOperationPanel().getPanelNameJP(getOperationPanel().DETAILPANEL));
			}
		});
		jpanel.add(editButton);
		return jpanel;
	}
}
