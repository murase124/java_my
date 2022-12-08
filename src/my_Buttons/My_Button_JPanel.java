package my_Buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class My_Button_JPanel extends JPanel {

	JLayeredPane pane = new JLayeredPane();
	JPanel jpanel = new JPanel();
	Insets insets = new Insets(0, 0, 0, 0);
	int width_height[] = new int[] {0,0};
	int over_width_height[] = new int[] {0,0};
	JComponent string = null;
	Border border = null;
	
	public My_Button_JPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(jpanel);
		jpanel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
		jpanel.add(pane);
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
	}
	
	public void set_Whole_Insets(Insets insets) {
		this.insets.top += insets.top;
		this.insets.bottom += insets.bottom;
		this.insets.right += insets.right;
		this.insets.left += insets.left;
		
		this.width_height[0] += insets.left + insets.right;
		this.width_height[1] += insets.top + insets.bottom;
	}
	public Insets get_Whole_Insets() {
		return this.insets;
	}
	
	public void set_Parent_Insets() {
		this.insets = new Insets(0, 0, 0, 0);
		this.width_height = new int[] {0,0};
		set_Whole_Insets(jpanel.getParent().getInsets());
		set_Whole_Insets(jpanel.getInsets());
	}
	
	public void set_Size() {
		if(string == null) return;
		Dimension string_insets = string.getMinimumSize();
		int width = string_insets.width + width_height[0] - over_width_height[0];
		int height = string_insets.height + width_height[1] - over_width_height[1];
		jpanel.getParent().setSize(width , height);	
	}
	public void set_Over_Size(int over_width, int over_height) {
		this.over_width_height[0] = over_width;
		this.over_width_height[1] = over_height;
		set_Size();
	}
	
	void set_Border() {
		if(string == null || border == null) return;
		string.setBorder(border);
	}
	public void set_Insets(int top,int left, int bottom,int right) {
		set_Insets(new Insets(top, left, bottom, right));
	}
	public void set_Insets(Insets insets) {
		border = new Border() {
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {}
			@Override
			public boolean isBorderOpaque() {return false;}
			@Override
			public Insets getBorderInsets(Component c) {
				return insets;
			}
		};
		set_Border();
	}
	
	public void set_text(JComponent string) {
		this.string = string;
		pane.add(string);
		pane.setLayer(string, 0);
		set_Parent_Insets();
		set_Size();
	}
}
