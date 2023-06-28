package calen04.schedulePanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SwitchingPanel extends JPanel{
	private CardLayout cardlayout;
	
	private ArrayList<String> panelsNames = new ArrayList<>();
	private ArrayList<JPanel> panels = new ArrayList<>();
	private ArrayList<String> actionButtonTexts = new ArrayList<>();
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
	public ArrayList<String> getActionButtonTexts() {
		return actionButtonTexts;
	}
	public String getActionButtonTexts(int num) {
		return actionButtonTexts.get(num);
	}
	public CardlayoutActionListener getActionListener(int num) {
		return actionListener.get(num);
	}
	
	public int addCreatePane(String name, String text) {
		JPanel panel = new JPanel();
		this.panelsNames.add(name);
		this.actionButtonTexts.add(text);
		this.panels.add(panel);
		this.add(panel, name);
		actionListener.add( new CardlayoutActionListener(this, name, cardlayout));
		return this.panelsNames.size()-1;
	}
	
	public SwitchingPanel(){
		cardlayout = new CardLayout();
		setLayout(cardlayout);
	}
	
	class CardlayoutActionListener implements ActionListener{
		private String name;
		private JPanel panel;
		private CardLayout cardlayout;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			 this.cardlayout.show(panel, name);
		}
		public void run() {
			this.cardlayout.show(panel, name);
		}
		
		
		public CardlayoutActionListener(JPanel panel, String name, CardLayout cardlayout){
			this.panel = panel;
			this.name = name;
			this.cardlayout = cardlayout;
		}
		
	}
	
}
