package my_Buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class My_Button_JPanel_Border implements Border{
	Insets insets = new Insets(0,0,0,0);
	Color[] colors = new Color[] {new Color(0,0,0),new Color(0,0,0),new Color(0,0,0),new Color(0,0,0)};
	int[] paint_num = new int[] {1,2,3,4};
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Insets insets = getBorderInsets(c);
		for(int i =0; i < 4; i++) {
			switch (paint_num[i]-1) {
			case 0: {
				g.setColor(colors[0]);
				g.fillRect(x, y, width, insets.top);
			    continue;
			}
			case 1: {
				 g.setColor(colors[1]);
				 g.fillRect(x, y, insets.left, height);
				 continue;
			}
			case 2: {
				 g.setColor(colors[2]);
				 g.fillRect(x, y + height - insets.bottom, width, insets.bottom);
				 continue;
			}
			case 3: {
				 g.setColor(colors[3]);
				 g.fillRect(x + width - insets.right, y, insets.right, height);
				 continue;
			}
			default:
			}
		}	   
	}
	@Override
	public boolean isBorderOpaque() {
		return false;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
	    return this.insets;
	}
	
	
	public void set_Insets(Insets insets) {
		this.insets = insets;
	}
	public void set_Insets(int top,int left, int bottom,int right) {
		set_Insets(new Insets(top, left, bottom, right));
	}
	
	
	public boolean set_colors(Color[] color) {
		if(color.length != 4) return false;

		for(int i =0; i < 4; i++)this.colors[i] = color[i];
		return true;
	}
	public boolean set_colors(Color top, Color left, Color bottom, Color right) {
		return set_colors(new Color[] {top, left, bottom, right});
	}
	public void set_colors_top(Color top) {
		this.colors[0] = top;
	}
	public void set_colors_left(Color left) {
		this.colors[1] = left;
	}
	public void set_colors_bottom(Color bottom) {
		this.colors[2] = bottom;
	}
	public void set_colors_right(Color right) {
		this.colors[3]  = right;
	}
	public Color[] get_color() {
		return this.colors;
	}
	public Color get_colors_top() {
		return this.colors[0];
	}
	public Color get_colors_left() {
		return this.colors[1];
	}
	public Color get_colors_bottom() {
		return this.colors[2];
	}
	public Color get_colors_right() {
		return this.colors[3];
	}
	
	public boolean set_pain_num(int num1, int num2, int num3, int num4) {
		return set_paint_num(new int[] {num1,num2,num3,num4});
	}
	
	public boolean set_paint_num(int[] num) {
		if(num.length != 4) return false;
		for(int i=0;i<4;i++) {
			int j;
			for(j=0;j<4;j++) {
				if(num[j] == i+1) break;
			}
			if(j == 4)	return false;	
		}
		this.paint_num = num;
		return true;
	}
	
	public int[] get_paint_num() {
		return this.paint_num;
	}
}


