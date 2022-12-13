package my_Buttons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class My_Button_JPanel extends JPanel {

	JPanel jpanel = new JPanel();

	Border border = null;
	
	int over_width_height[] = new int[] {0,0};
	JComponent textcomponent = null;
	
	My_Button_JPanel_MouseListener my_Listener;
	
	
	public My_Button_JPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		jpanel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
		my_Listener = new My_Button_JPanel_MouseListener(this);

		addMouseListener(my_Listener);
	}
	
	public void set_text(JComponent text) {
		this.textcomponent = text;
		add(text);
		set_Border();
	}
	public JComponent get_text() {
		return this.textcomponent;
	}
	
	private void set_Border() {
		if(textcomponent == null || border == null) return;
		textcomponent.setBorder(border);
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
	
	public void set_Insets(int top,int left, int bottom,int right) {
		set_Insets(new Insets(top, left, bottom, right));
	}
	
	public void set_X_Y(int x,int y) {
    	setBounds(x, y, getPreferredSize().width,getPreferredSize().height);
    }
	public Dimension get_Size() {
    	return getPreferredSize();
    }
	
	public My_Button_JPanel_MouseListener get_Listener() {
		return my_Listener;
	}
	
}
