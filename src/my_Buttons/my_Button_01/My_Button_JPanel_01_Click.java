package my_Buttons.my_Button_01;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class My_Button_JPanel_01_Click implements Border {

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		
	}
	@Override
	public boolean isBorderOpaque() {
		return false;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
	    return new Insets(3, 2, 1, 1);
	}
}
