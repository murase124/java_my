package myButtons.auxiliaryClassList;

import myButtons.MyButtonJPanel;
import myButtons.MyButtonJPanelMouseListener;

public abstract class Layout {
	private MyButtonJPanel my_button_jpanel;
	private MyButtonJPanelMouseListener my_Listener;
	private MyButtonJPanelBorders my_Border;
	
	public Layout(MyButtonJPanel my_button_jpanel ) {
		setMy_button_jpanel(my_button_jpanel);
		setMy_Listener(my_button_jpanel.getMy_Listener());
		setMy_Border(getMy_Listener().getBorder());
		set_Layout();
	}
	
	public abstract void set_Layout();

	public MyButtonJPanelBorders getMy_Border() {
		return my_Border;
	}

	private void setMy_Border(MyButtonJPanelBorders my_Line_Border) {
		this.my_Border = my_Line_Border;
	}

	public MyButtonJPanelMouseListener getMy_Listener() {
		return my_Listener;
	}

	private void setMy_Listener(MyButtonJPanelMouseListener my_Listener) {
		this.my_Listener = my_Listener;
	}
	public MyButtonJPanel getMy_button_jpanel() {
		return my_button_jpanel;
	}

	public void setMy_button_jpanel(MyButtonJPanel my_button_jpanel) {
		this.my_button_jpanel = my_button_jpanel;
	}
}
