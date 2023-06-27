package calen02.schedule;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SwitchingPane {


	private JPanel switchingPane;
	private CardLayout cardlayout;
	public JPanel getSwitchingPane() {
		return switchingPane;
	}
	
	private ArrayList<String> name = new ArrayList<>();
	private ArrayList<JPanel> panels = new ArrayList<>();
	private ArrayList<JButton> buttons = new ArrayList<>();

	public ArrayList<String> getName() {
		return name;
	}	
	public String getName(int num) {
		return name.get(num);
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
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	public JButton getButtons(int num) {
		return buttons.get(num);
	}
	public void setButtonText(int num, String text) {
		buttons.get(num).setText(text);;
	}
	
	public int addCreatePane(String name, String text) {
		JButton button = new JButton();
		JPanel panel = new JPanel();
		this.name.add(name);
		this.panels.add(panel);
		this.switchingPane.add(panel, name);
		this.buttons.add(button);
		button.addActionListener( e -> { this.cardlayout.show(this.switchingPane, name); });
		button.setText(text);;
		return this.name.size()-1;
	}
	
	SwitchingPane(){
		switchingPane = new JPanel();
		cardlayout = new CardLayout();
		switchingPane.setLayout(cardlayout);
	}
	


}
