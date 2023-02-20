package myButtons.auxiliaryClassList;

import myButtons.MyButtonJPanel;
import myButtons.MyButtonJPanelMouseListener;

public abstract class ChangeLayout {
	private MyButtonJPanel jpanel;
	private MyButtonJPanelMouseListener my_Listener;
	private MyButtonJPanelBorders my_Border;
	private BackgroundColor backgroundColor; 

	public final String ENTERED = "mouseEntered";
	public final String EXITED = "mouseExited";
	public final String PRESSED = "mousePressed";
	public final String RELEASED = "mouseReleased";
	public final String NOW = "now";	

	public abstract void changeLayout(String comand);
	
	public void set_panel(MyButtonJPanel jpanel) {
		this.setJpanel(jpanel);
		this.setMy_Listener(getJpanel().getMy_Listener());
		this.setMy_Border(getMy_Listener().getBorder());
		this.setBackgroundColor(getMy_Listener().getBackgroundColor());
	}
	
	public BackgroundColor getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(BackgroundColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public MyButtonJPanelMouseListener getMy_Listener() {
		return my_Listener;
	}

	public void setMy_Listener(MyButtonJPanelMouseListener my_Listener) {
		this.my_Listener = my_Listener;
	}

	public MyButtonJPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(MyButtonJPanel jpanel) {
		this.jpanel = jpanel;
	}

	public MyButtonJPanelBorders getMy_Border() {
		return my_Border;
	}

	public void setMy_Border(MyButtonJPanelBorders my_Line_Border) {
		this.my_Border = my_Line_Border;
	}
}
