package calen04.schedulePanel.Panels.Detail;

import java.util.ArrayList;

import javax.swing.JButton;

import calen04.schedulePanel.Panels.ScheduleDetailPane;

public class DetailPanel extends ScheduleDetailPane{

	public DetailPanel(ArrayList<JButton> buttons) {
		super(buttons);
		getStartDate().setEditable(false);
		getStartDate().setText("aaa");
		getStartTime().setEditable(false);
		getEndDate().setEditable(false);
		getEndTime().setEditable(false);
		getTitle().setEditable(false);
		getText().setEditable(false);
	}
	
}
