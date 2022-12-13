package my_Buttons;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class My_Button_JPanel_MouseListener implements MouseListener {

	int color_num =0;
	JPanel jpanel;
	public Color[] background_color = new Color[2];
	My_Button_JPanel_Border[] border =new My_Button_JPanel_Border[2];
	
	public My_Button_JPanel_MouseListener(JPanel jpanel) {
		background_color[0] = new Color(255,255,255);
		background_color[1] = new Color(255,255,255);
		 border[0] =new My_Button_JPanel_Border();
		 border[1] =new My_Button_JPanel_Border();
		this.jpanel = jpanel;
		this.jpanel.setBorder(border[0]);
		this.change_Background_Color(0);
		this.jpanel.setOpaque(true);
	}
	
	private void change_Button_Layout(int num) {
		if(num == 0) {
			change_Background_Color(0);
		}else if(num == 1) {
			this.jpanel.setBorder(border[0]);
			change_Background_Color(1);
		}else if(num == 2) {
			this.jpanel.setBorder(border[1]);
		}
	}
	
	private Color change_Background_Color(int num) {
		jpanel.setBackground(background_color[num]);
		return background_color[num];
	}
	
	@Override
	public void mouseEntered(MouseEvent e){  
		change_Button_Layout(++color_num);
    	return;
    }
	
	@Override
	public void mouseExited(MouseEvent e){  
		change_Button_Layout(--color_num);
    	return;
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		change_Button_Layout(++color_num);
    	return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		change_Button_Layout(--color_num);
    	return;
	}

	public boolean set_Background_Color(int num, Color color) {
		if(0 > num && num > 1) return false;
		background_color[num] = color;
		change_Button_Layout(color_num);
		return true;
	}
	public boolean set_Background_Color(Color color1, Color color2) {
		return set_Background_Color(new Color[] {color1,color2});
	}
	public boolean set_Background_Color(Color[] color) {
		if(color.length != 2) return false;
		background_color = color;
		change_Button_Layout(color_num);
		return true;
	}
	public My_Button_JPanel_Border[] get_Border() {
		return border;
	}
	

}
