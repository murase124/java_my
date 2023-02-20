package my_Buttons;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import my_Buttons.my_Button_01.Layout_01;
import vertical_text_labal.vertical_text;

public class Test extends JFrame {
	
    public static void main(String[] args) {
    	new Test();
    }
    
    
    Test(){

		setLayout(new GridLayout());

		JPanel j = new JPanel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	My_Button_JPanel a = new My_Button_JPanel();
    	My_Button_JPanel a1 = new My_Button_JPanel();
    	vertical_text jl =new vertical_text("あああああああああ");
    	JLabel jl1 =new JLabel("あああああああああ");
    	new Layout_01(a);
    	new Layout_01(a1);

    	
    	a.set_Insets(10,10,10,10);
    	a1.set_Insets(10,10,10,10);
    	a.set_text(jl);
    	a1.set_text(jl1);
    	j.add(a);
    	//j.add(a1);
    	add(j);
    	
    	a.setSize(getPreferredSize());
    	a.set_X_Y(240, 100);
    	j.setLayout(null);
    	setSize(400, 400);
        j.setBackground(new Color(150,150,150));

    	setVisible(true);
    }
}
