package calen04.schedulePanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CardlayoutActionListener implements ActionListener{
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