package calen05.schedulePanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.JButton;

public class OperationPanel extends SwitchingPanel {
	public final int LISTPANEL = 0;
	public final int ADDPANEL = 1;
	public final int DETAILPANEL = 2;
	private final static String[] NAME = { "list" , "add"  , "detail" };
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
	

	public Component getPanelsComponent(int panelNum) {
		return getPanels(panelNum).getComponent(0);
	}
	
	public OperationPanel() {
		// TODO 自動生成されたコンストラクター・スタブ
		for(int i =0; i < NAME.length;i++) {
			createPane(i, NAME[i]);
			getPanels(i).setPreferredSize(new Dimension(470, 220));
			getPanels(i).setLayout(new BorderLayout());
		}
	}
	
}
