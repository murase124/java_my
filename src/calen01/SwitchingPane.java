package calen01;

import java.awt.CardLayout;
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
	private ArrayList<JPanel> panles = new ArrayList<>();
	private ArrayList<JButton> buttons = new ArrayList<>();

	public ArrayList<String> getName() {
		return name;
	}	
	public ArrayList<JPanel> getPanles() {
		return panles;
	}
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
	public int add(JPanel panel, String name) {
		JButton button = new JButton();

		this.name.add(name);
		this.panles.add(panel);
		this.switchingPane.add(panel, name);
		this.buttons.add(button);
		button.addActionListener( e -> { this.cardlayout.show(switchingPane, name); });
		return this.name.size()-1;
	}
	
	SwitchingPane(){
		switchingPane = new JPanel();
		cardlayout = new CardLayout();
		switchingPane.setLayout(cardlayout);
	}
	


}
