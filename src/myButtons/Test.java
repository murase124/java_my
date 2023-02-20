package myButtons;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myButtons.myButton00.Layout00;


public class Test extends JFrame {
	
    public static void main(String[] args) {
    	new Test();
    }
    
    
    Test(){

		setLayout(null);


		JPanel j = new JPanel();
        j.setBackground(new Color(150,150,150));
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	MyButtonJPanel a = new MyButtonJPanel();
    	new Layout00(a);
    	a.setBackground(Color.orange);
    	a.setText(new JLabel("aa"));
    	a.setBounds(10, 10, 30, 50);
    	

    	add(a);
    	add(j);
    
    	setSize(400, 400);


    	setVisible(true);
    }
}
