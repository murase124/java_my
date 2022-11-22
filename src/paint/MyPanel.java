package paint;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

class MyPanel extends JPanel implements MouseListener, MouseMotionListener{

	int[] color_int = new int[] {0,0,0};
	int[] color_box = new int[] {0,0,0};
	Color color = new Color(0,0,0);
	int concentration = 0; //濃さ
	Image image=createImage(getWidth(),getHeight());
	
	int size = 5;

	
	public MyPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		set_line_color(Color.BLACK);
	}
	
	public MyPanel(int[] color) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		set_line_color(color);
	}
	
	public MyPanel(Color color) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		set_line_color(color);
	}
	
	
	
	public void set_line_color(int[] color) {
		// TODO 自動生成されたメソッド・スタブ
		this.color_int = color;
	}
	public void set_line_color(Color color) {
		// TODO 自動生成されたメソッド・スタブ
		this.color_int[0] = color.getRed();
		this.color_int[1] = color.getGreen();
		this.color_int[2] = color.getBlue();
	}
	public Color get_line_color() {
		// TODO 自動生成されたメソッド・スタブ
		return color;
	}
	public int[] get_line_color_int() {
		// TODO 自動生成されたメソッド・スタブ
		return color_int;
	}
	
	public void set_concentration(int concentration) {
		// TODO 自動生成されたメソッド・スタブ
		this.concentration = concentration;
	}
	
	public int get_concentration() {
		// TODO 自動生成されたメソッド・スタブ
		return concentration;
	}

	public void set_line_size(int size) {
		// TODO 自動生成されたメソッド・スタブ
		this.size = size;
	}
	public int get_line_size() {
		// TODO 自動生成されたメソッド・スタブ
		return size;
	}

	
	
	public void setpaint() {
		// TODO 自動生成されたメソッド・スタブ
		if(image ==null ){
			 image=createImage(getWidth(),getHeight());
			 Graphics g1=image.getGraphics(); 
			 g1.setColor(Color.WHITE);
			 g1.fillRect(0, 0, getWidth(), getHeight());
		}
		paint_update();
	}
	
	public void paint_update() {
		// TODO 自動生成されたメソッド・スタブ
		Graphics g = getGraphics();
		g.drawImage(image,0,0,this);		
	}
	
	
	public void mypaint(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
				 Graphics g=image.getGraphics(); 
				for(int i=0;i<3;i++) 
					if(color_int[i]+concentration <= 255)
					{
						if(color_int[i]+concentration >= 0) {
							color_box[i] = color_int[i]+concentration;
						}else {
							color_box[i] = 0;
						}
					}else {
						color_box[i] = 255;
					}
				color = new Color(color_box[0], color_box[1], color_box[2]);
				g.setColor(color);
				g.fillRect(e.getX() - size/2, e.getY() - size/2, size, size);
				paint_update();	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mypaint(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mypaint(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {}
	
	
}