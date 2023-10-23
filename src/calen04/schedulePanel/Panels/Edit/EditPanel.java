package calen04.schedulePanel.Panels.Edit;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import calen04.schedulePanel.CardlayoutActionListener;
import calen04.schedulePanel.Panels.ScheduleDetailPane;

public class EditPanel extends ScheduleDetailPane{

	public EditPanel(ArrayList<CardlayoutActionListener> actionListeners) {
		setLayout(new BorderLayout());

		add(createSwitchMovePane(actionListeners), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
	}
	
	
	/* パネル切り替えボタン作成用 */
	@Override
	public JPanel createSwitchMovePane(ArrayList<CardlayoutActionListener> actionListeners) {
		JPanel jpanel = new JPanel();
		jpanel.add(switchingButton("一覧", actionListeners.get(0)));
		jpanel.add(switchingButton("追加", actionListeners.get(1)));
		jpanel.add(switchingButton("削除", null));
		jpanel.add(switchingButton("保存", actionListeners.get(2)));
		return jpanel;
	}
	
	
}
