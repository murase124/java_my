package calen04.schedulePanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import calen04.ScheduleDTO.Schedule;
import calen04.schedulePanel.Panels.Detail.DetailPanel;
import calen04.schedulePanel.Panels.Edit.EditPanel;
import calen04.schedulePanel.Panels.Overview.OverviewPanel;
import calen04.schedulePanel.Panels.add.AddPanel;


public class SchedulePanel extends SwitchingPanel{
	
	public final String[] SWITCHINGBUTTONNAME = { "Overview" , "add"  , "detail" , "edit" };
	public final String[] SWITCHINGBUTTONTEXT = { "概要" , "追加"  , "詳細" , "編集" };
	public final String[] PROCESSBUTTONNAME = { "new" , "delet" , "save" };
	public final String[] PROCESSBUTTONTEXT = { "登録" , "削除" , "保存" };
	public final String newLine = "\r\n";
	
	private OverviewPanel overviewPanel;//概要表示
	private AddPanel addPane;//追加表示
	private DetailPanel detailPane;//詳細表示
	private EditPanel editPane;//編集表示
	
	/* 日付ボタン時の処理 */
	public void dayButtonAction(LocalDateTime date) {
		//スケジュールリストを表示時の処理
		if(getComponent(0).isVisible() == true){
		}else if(getComponent(1).isVisible() == true){
		
		}
	}
	public void dayButtonAction(List<Schedule> daySchedule) {
		//スケジュールリストを表示時の処理
		if(getComponent(0).isVisible() == true){
			overviewPanel.updateSchedule(daySchedule);
		}else if(getComponent(1).isVisible() == true){
		
		}
	}
	
	/* パネル切り替えボタン作成用 */
	public JButton switchingButton(String text, ActionListener actionListener) {
		JButton jbutton = new JButton(text);
		if(actionListener != null) jbutton.addActionListener(actionListener);
		return jbutton;
	}
	
	public SchedulePanel(){
		for(int i =0; i < SWITCHINGBUTTONNAME.length;i++) {
			addCreatePane(SWITCHINGBUTTONNAME[i], SWITCHINGBUTTONTEXT[i]);
			getPanels(i).setPreferredSize(new Dimension(470, 220));
			getPanels(i).setLayout(new BorderLayout());
		}
		
		//概要
		ArrayList<JButton> overviewPaneSwitchingButtons = new ArrayList<>();
		overviewPaneSwitchingButtons.add(switchingButton(getActionButtonTexts(0), getActionListener(0)));
		overviewPaneSwitchingButtons.add(switchingButton(getActionButtonTexts(1), getActionListener(1)));
		overviewPaneSwitchingButtons.add(switchingButton(PROCESSBUTTONTEXT[1], null));
		overviewPaneSwitchingButtons.add(switchingButton(getActionButtonTexts(2), getActionListener(2)));
		overviewPanel = new OverviewPanel(overviewPaneSwitchingButtons);
		addPanels(0, overviewPanel);
		
		//追加
		ArrayList<JButton> addPaneOperationButtons = new ArrayList<>();
		addPaneOperationButtons.add(switchingButton(getActionButtonTexts(0),
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int let = 0;
					let = JOptionPane.showConfirmDialog(null, "保存しないでよろしいですかaaaaa？", "警告", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
					if(let == JOptionPane.YES_OPTION) {
						getActionListener(0).run();
					}
				}
			}
		));
		addPaneOperationButtons.add(switchingButton(PROCESSBUTTONTEXT[0], null));
		addPane = new AddPanel(addPaneOperationButtons);
		addPanels(1, addPane);
		
		//詳細
		ArrayList<JButton> detailPaneOperationButtons = new ArrayList<>();
		detailPaneOperationButtons.add(switchingButton(getActionButtonTexts(0), getActionListener(0)));
		detailPaneOperationButtons.add(switchingButton(getActionButtonTexts(1), getActionListener(1)));
		detailPaneOperationButtons.add(switchingButton(PROCESSBUTTONTEXT[1], null));
		detailPaneOperationButtons.add(switchingButton(getActionButtonTexts(3), getActionListener(3)));
		detailPane = new DetailPanel(detailPaneOperationButtons);
		addPanels(2, detailPane);
		
		//編集
		ArrayList<JButton> editPaneOperationButtons = new ArrayList<>();
		editPaneOperationButtons.add(switchingButton(getActionButtonTexts(0), getActionListener(0)));
		editPaneOperationButtons.add(switchingButton(getActionButtonTexts(1), getActionListener(1)));
		editPaneOperationButtons.add(switchingButton(PROCESSBUTTONTEXT[2], null));
		editPane = new EditPanel(editPaneOperationButtons);
		addPanels(3, editPane);
	}
	
	
}
