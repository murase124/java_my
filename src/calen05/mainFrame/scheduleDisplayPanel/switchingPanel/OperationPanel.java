package calen05.mainFrame.scheduleDisplayPanel.switchingPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;

import calen05.mainFrame.dayPanel.dayButtonDTO.DayButton;
import calen05.mainFrame.scheduleDisplayPanel.panels.Detail.DetailPanel;
import calen05.mainFrame.scheduleDisplayPanel.panels.List.ListPanel;
import calen05.mainFrame.scheduleDisplayPanel.panels.add.AddPanel;
import calen05.scheduleManagement.Schedule;
import calen05.scheduleManagement.ScheduleMmanagement;

public class OperationPanel extends SwitchingPanel {
	public static final int LISTPANEL = 0;
	public static final int ADDPANEL = 1;
	public static final int DETAILPANEL = 2;
	private final String[] NAMEJP = { "一覧" , "追加"  , "詳細" };

	public JButton getSwitchingButton(int panelNum) {
		if(isSwitchingNameJP(panelNum)) {	
			return getSwitchingButton(panelNum, getPanelNameJP(panelNum));
		}
		return null;
	}
	
	public String getPanelNameJP(int panelNum) {
		if(isSwitchingNameJP(panelNum)) {	
    		return NAMEJP[panelNum];
		}
        return null;
	}
	public Boolean isSwitchingNameJP(int panelNum) {
		try {
            Objects.checkIndex(panelNum, NAMEJP.length);
    		return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
	}
	

	public Component getDisplayPanel(int panelNum) {
		return getPanels(panelNum).getComponent(0);
	}
	
	public void setDay(DayButton dayButton) {
		if(getComponent(0).isVisible() == true){
			listUpdate(dayButton.getDateTime());
		}else if(getComponent(1).isVisible() == true){

		}
	}
	
	public void listUpdate(LocalDateTime day) {
		//スケジュールリストを表示時の処理
		List<Schedule> daySchedule = ScheduleMmanagement.getDaySchedule(day);
		ListPanel listPanel = (ListPanel) getDisplayPanel(OperationPanel.LISTPANEL);
		listPanel.updateSchedule(daySchedule);
	}
	
	public OperationPanel() {
		// TODO 自動生成されたコンストラクター・スタブ
		for(int i =0; i < NAMEJP.length;i++) {
			createSwitchingPane(i);
			getPanels(i).setPreferredSize(new Dimension(470, 220));
			getPanels(i).setLayout(new BorderLayout());
		}
		
		//リスト
		ListPanel listPanel = new ListPanel(this);
		addPanels(LISTPANEL, listPanel);
		
		//追加
		AddPanel addPanel = new AddPanel(this);
		addPanels(ADDPANEL, addPanel);
		//詳細
		DetailPanel detailPanel = new DetailPanel(this);
		addPanels(DETAILPANEL, detailPanel);
		
		listPanel.addListPanelListener(lineNum -> {
			if(lineNum != -1) {
				ScheduleMmanagement.setIndexOneSchedule(lineNum-1);//行数ー１のindexにアクセス
				listPanel.setDetailPanelEnabled(true);
				detailPanel.setSchedule(ScheduleMmanagement.getOneSchedule());
			}else if(lineNum == -1) {
				listPanel.setDetailPanelEnabled(false);
			}
		});
		
		getActionListener().get(DETAILPANEL).add(() -> {
			if(null != ScheduleMmanagement.getOneSchedule()) {
				detailPanel.setSchedule(ScheduleMmanagement.getOneSchedule());
			}
		});
	}
}
