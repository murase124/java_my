package my_Buttons.my_Button_01;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class My_Button_JPanel_01_Hover implements Border{

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Insets insets = getBorderInsets(c);
	    g.setColor(new Color(140,140,140));
	    g.fillRect(x, y, insets.left, height);
	    g.fillRect(x, y, width, insets.top);

	    g.setColor(new Color(120,120,120));
	    g.fillRect(x + width - insets.right, y, insets.right, height);
	    g.setColor(new Color(100,100,100));
	    g.fillRect(x, y + height - insets.bottom, width, insets.bottom);
	}
	@Override
	public boolean isBorderOpaque() {
		return false;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
	    return new Insets(1, 1, 3, 2);
	}
}