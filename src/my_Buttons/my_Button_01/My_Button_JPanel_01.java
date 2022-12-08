package my_Buttons.my_Button_01;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class My_Button_JPanel_01 implements MouseListener {

	JPanel jpanel;
	JPanel	Parent;
	Color[] colors = new Color[] {new Color(160,160,160),new Color(180,180,180)};
	
	public My_Button_JPanel_01(JPanel jpanel) {
		this.jpanel = (JPanel) jpanel.getComponent(0);
		this.jpanel.setBorder(new My_Button_JPanel_01_Default());
		this.jpanel.setOpaque(true);
		get_color(0);

		Parent = jpanel;
		Parent.setBorder(new My_Button_JPanel_01_Click_Parent());
		System.out.println(0 + ""+Parent.isOpaque());
	}
	
	public Color get_color(int num) {
		jpanel.setBackground(colors[num]);
		return colors[num];
	}
	public void set_colors(int num,Color color) {
		this.colors[num] = color;
	}

	@Override
	public void mouseEntered(MouseEvent e){  
		set_colors(0,new Color(170,170,170));
		get_color(0);
		
		System.out.println(get_color(0) + "1" +jpanel.isOpaque());

    	return;
    }
	
	@Override
	public void mouseExited(MouseEvent e){  
		jpanel.setBorder(new My_Button_JPanel_01_Default());
		set_colors(0,new Color(160,160,160));
		get_color(0);

		System.out.println(get_color(0) + "2" + jpanel.isOpaque());

    	return;
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		jpanel.setBorder(new My_Button_JPanel_01_Click());
		set_colors(1,new Color(180,180,180));
		get_color(1);
		
		System.out.println(get_color(1) + "3" + jpanel.isOpaque());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		jpanel.setBorder(new My_Button_JPanel_01_Default());
		get_color(0);
		
		System.out.println(get_color(0) + "4" + jpanel.isOpaque());
	}


}
