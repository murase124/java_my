package calen05.mainFrame.scheduleDisplayPanel.switchingPanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwitchingPanel extends JPanel{
	private CardLayout cardlayout;
	/* 各表示パネル */
	private Map<Integer, JPanel> panels = new HashMap<>();
	public JPanel getPanels(int panelNum) {
		return this.panels.get(panelNum);
	}
	public void addPanels(int panelNum, Component pane) {
		panels.get(panelNum).removeAll();
		panels.get(panelNum).add(pane);
	}
	/* 画面切り替え時のリスナ */
	private Map<Integer, SwitchingActionListener> switchingListener = new HashMap<>();
	public SwitchingActionListener getActionListener(int panelNum) {
		return switchingListener.get(panelNum);
	}
	public Map<Integer, SwitchingActionListener> getActionListener() {
		return switchingListener;
	}
	

	/* リスナを設定したボタン取得 */
	protected JButton getSwitchingButton(int panelNum, String name) {
		JButton jbutton = new JButton(name);
		jbutton.addActionListener(getActionListener().get(panelNum));
		return jbutton;
	}
	
	/* 表示するパネル作成 */
	protected void createSwitchingPane(int panelNum) {
		JPanel panel = new JPanel();
		this.panels.put(panelNum, panel);
		this.add(panel, String.valueOf(panelNum));
		getActionListener().put(panelNum, new SwitchingActionListener());

		JPanel parent = (JPanel) panel.getParent();
		getActionListener().get(panelNum).add(() -> {
			cardlayout.show(parent, String.valueOf(panelNum));
		});
	}
	
	public SwitchingPanel(){
		cardlayout = new CardLayout();
		setLayout(cardlayout);
	}

}
