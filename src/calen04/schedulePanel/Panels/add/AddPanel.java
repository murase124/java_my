package calen04.schedulePanel.Panels.add;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calen04.schedulePanel.CardlayoutActionListener;
import calen04.schedulePanel.Panels.ScheduleDetailPane;

public class AddPanel extends ScheduleDetailPane {
	
	public AddPanel(ArrayList<CardlayoutActionListener> actionListeners) {
		setLayout(new BorderLayout());

		add(createSwitchMovePane(actionListeners), BorderLayout.NORTH);
		add(createDisplayPane(), BorderLayout.CENTER);
	}
	
	/* パネル切り替えボタン作成用 */
	@Override
	public JPanel createSwitchMovePane(ArrayList<CardlayoutActionListener> actionListeners) {
		JPanel jpanel = new JPanel();
		jpanel.add(switchingButton("一覧",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int let = 1;
					let = JOptionPane.showConfirmDialog(null, "保存しないでよろしいですか？", "警告", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
					if(let == JOptionPane.YES_OPTION) {
						actionListeners.get(0).run();
					}
				}
			}
		));
		jpanel.add(switchingButton("登録", null));
		return jpanel;
	}

}
