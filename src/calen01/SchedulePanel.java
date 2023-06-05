package calen01;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SchedulePanel {
	public final String[] BUTTONNAME = { "list" , "add"  , "edit" };
	public final String[] BUTTONTEXT = { "リスト" , "追加"  , "変更" };
	public final int[] SWITCHINGPANESIZE = { 480 , 160 };//横、縦
	public final String newLine = "\r\n";

	private ArrayList<JPanel> switchingPanes = new ArrayList<JPanel>();
	private SwitchingPane switchingPane = new SwitchingPane();
	public JPanel getSwitchingPane() {
		return switchingPane.getSwitchingPane();
	}
	public SwitchingPane getSwitchingPaneClass() {
		return switchingPane;
	}

	private JTextPaneMy scheduleListPane;
	private ArrayList<Schedule> schedule = new ArrayList<Schedule>();
	
	private JButton deletButton;
	private JPanel operationPane;
	public JPanel getOperationPane() {
		return operationPane;
	}
	public void setOperationPane(JPanel operationPane) {
		this.operationPane = operationPane;
	}
	
	public void createListPanel() {
		scheduleListPane = new JTextPaneMy();
		switchingPanes.get(0).add(scheduleListPane);
		scheduleListPane.setPreferredSize(new Dimension(SWITCHINGPANESIZE[0], SWITCHINGPANESIZE[1]));
		scheduleListPane.setText("aaaw");
		scheduleListPane.setText(scheduleListPane.getText() + newLine +"raaaaaw");
		scheduleListPane.setEditable(false);
	}
	
	public void createAddPane() {
		JPanel panel = switchingPanes.get(1);
		panel.setPreferredSize(new Dimension(SWITCHINGPANESIZE[0], SWITCHINGPANESIZE[1]));
		panel.setBackground(Color.red);
	}
	
	public void createEditPane() {
	}
	
	public void setList(String day) {
		scheduleListPane.setText(day + newLine);
	}
	
	SchedulePanel(){
		operationPane = new JPanel();
		
		for(int i =0; i < BUTTONNAME.length;i++) {
			JPanel panel = new JPanel();
			switchingPanes.add(panel);
			switchingPane.add(panel, BUTTONNAME[i]);
			
			JButton button = switchingPane.getButtons().get(i);
			button.setText(BUTTONTEXT[i]);
			operationPane.add(button);
			if(i != BUTTONNAME.length-1) operationPane.add(Box.createRigidArea(new Dimension(10,10)));
		}
		deletButton = new JButton("削除");
		operationPane.add(deletButton , 3);
		operationPane.add(Box.createRigidArea(new Dimension(10,10)),3);
	}
}
