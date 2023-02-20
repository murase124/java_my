package paint;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class switching implements ActionListener{
    CardLayout layout;
	JPanel cardPanel;
	String name;
	ColorPanel color_panel;

	switching(JPanel cardPanel, String name, ColorPanel color_panel){
		this.cardPanel = cardPanel;
		this.name = name;
		this.layout = (CardLayout)cardPanel.getLayout();
		this.color_panel = color_panel;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		layout.show(cardPanel, name);
		color_panel.set();
	}
	
}

public class MainSimpleDraw extends JFrame implements ActionListener{
	public static void main(String[] args) {
		new MainSimpleDraw();
	}
	
	JPanel cardPanel;
    CardLayout layout;
    DefaltColorPanel defalt_color_panel ;
	SliderColorPanel slider_color_panel ;
	
	public MainSimpleDraw() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);
        
        JButton switching_defalt = new JButton("変更");
		JButton switching_slider = new JButton("変更");
		
		MyPanel MyPanel = new MyPanel(Color.BLACK);
		defalt_color_panel = new DefaltColorPanel(MyPanel,switching_defalt);
		slider_color_panel  = new SliderColorPanel(MyPanel,switching_slider);
		
		switching_defalt.addActionListener(new switching(cardPanel, "slider", defalt_color_panel));
		switching_slider.addActionListener(new switching(cardPanel, "defalt", slider_color_panel));
		
		defalt_color_panel.setName("defalt");
		slider_color_panel.setName("slider");
		
		cardPanel.add(defalt_color_panel , "defalt");
		cardPanel.add(slider_color_panel , "slider");
		
		getContentPane().add(cardPanel, BorderLayout.PAGE_START);
		getContentPane().add(MyPanel , BorderLayout.CENTER);
		setSize(1000, 600);
		setVisible(true);
		
		MyPanel.setpaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
		layout.next(cardPanel);
		if(defalt_color_panel.isVisible())	defalt_color_panel.set();
		if(slider_color_panel.isVisible())	slider_color_panel.set();
		

	}
}
