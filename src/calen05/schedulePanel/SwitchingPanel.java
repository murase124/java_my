package calen05.schedulePanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwitchingPanel extends JPanel{
	private CardLayout cardlayout;
	private Map<Integer, JPanel> panels = new HashMap<>();
	private Map<Integer, CardlayoutActionListener> actionListener = new HashMap<>();

	public CardlayoutActionListener getActionListener(int panelNum) {
		return actionListener.get(panelNum);
	}
	public JPanel getPanels(int panelNum) {
		return this.panels.get(panelNum);
	}
	public void addPanels(int panelNum, Component pane) {
		panels.get(panelNum).removeAll();
		panels.get(panelNum).add(pane);
	}
	
	public JButton getSwitchingButton(int panelNum, String name) {
		JButton jbutton = new JButton(name);
		jbutton.addActionListener(actionListener.get(panelNum));
		return jbutton;
	}
	
	protected void createPane(int panelNum, String name) {
		JPanel panel = new JPanel();
		this.panels.put(panelNum, panel);
		this.add(panel, panelNum);
		actionListener.put(panelNum, new CardlayoutActionListener(this, name, cardlayout));
	}
	
	public SwitchingPanel(){
		cardlayout = new CardLayout();
		setLayout(cardlayout);
	}
	
}
