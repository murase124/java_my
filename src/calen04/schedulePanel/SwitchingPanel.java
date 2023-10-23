package calen04.schedulePanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SwitchingPanel extends JPanel{
	private CardLayout cardlayout;
	
	private ArrayList<String> panelsNames = new ArrayList<>();
	private ArrayList<JPanel> panels = new ArrayList<>();
	//private ArrayList<String> actionButtonTexts = new ArrayList<>();
	private ArrayList<CardlayoutActionListener> actionListener = new ArrayList<>();

	public ArrayList<String> getPanelsNames() {
		return panelsNames;
	}	
	public String getPanelsNames(int num) {
		return panelsNames.get(num);
	}	
	public ArrayList<JPanel> getPanels() {
		return panels;
	}
	public JPanel getPanels(int num) {
		return panels.get(num);
	}
	public void addPanels(int num, Component pane) {
		panels.get(num).add(pane);
	}
	public ArrayList<CardlayoutActionListener> getActionListener() {
		return actionListener;
	}
	public CardlayoutActionListener getActionListener(int num) {
		return actionListener.get(num);
	}
	
	public int createPane(String name) {
		JPanel panel = new JPanel();
		this.panelsNames.add(name);
		this.panels.add(panel);
		this.add(panel, name);
		actionListener.add( new CardlayoutActionListener(this, name, cardlayout));
		return this.panelsNames.size()-1;
	}
	
	public SwitchingPanel(){
		cardlayout = new CardLayout();
		setLayout(cardlayout);
	}
	
}
