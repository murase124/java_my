package calen04.schedulePanel.Panels.Detail;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import calen04.schedulePanel.CardlayoutActionListener;
import calen04.schedulePanel.Panels.ScheduleDetailPane;

public class DetailPanel extends ScheduleDetailPane{

	public DetailPanel(ArrayList<CardlayoutActionListener> actionListeners) {
		setEditable();
		setLayout(new BorderLayout());

		add(createSwitchMovePane(actionListeners), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
	}
	
	public void setEditable() {
		getItem().getStartDate().setEditable(false);
		getItem().getStartDate().setText("aaa");
		getItem().getStartTime().setEditable(false);
		getItem().getEndDate().setEditable(false);
		getItem().getEndTime().setEditable(false);
		getItem().getTitle().setEditable(false);
		getItem().getText().setEditable(false);
	}
	/* パネル切り替えボタン作成用 */
	@Override
	public JPanel createSwitchMovePane(ArrayList<CardlayoutActionListener> actionListeners) {
		JPanel jpanel = new JPanel();
		jpanel.add(switchingButton("一覧", actionListeners.get(0)));
		jpanel.add(switchingButton("追加", actionListeners.get(1)));
		jpanel.add(switchingButton("削除", null));
		jpanel.add(switchingButton("編集", actionListeners.get(3)));
		return jpanel;
	}
}
