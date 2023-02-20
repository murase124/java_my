package myButtons;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import myButtons.auxiliaryClassList.BackgroundColor;
import myButtons.auxiliaryClassList.ChangeLayout;
import myButtons.auxiliaryClassList.MyButtonJPanelBorders;
import myButtons.myButton00.ChangeLayout00;

public class MyButtonJPanelMouseListener implements MouseListener {

	private JPanel jpanel;
	private BackgroundColor backgroundColor;
	private MyButtonJPanelBorders border = new MyButtonJPanelBorders();
	private ChangeLayout changeLayout = new ChangeLayout() {@Override public void changeLayout(String comand) {}};
	
		
	/*
	 * Basic
	 * Get_Set
	 */
	public JPanel getJPanel() {
		return jpanel;
	}
	public void setJPanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}
	public MyButtonJPanelBorders getBorder() {
		return border;
	}
	public void setBorder(MyButtonJPanelBorders border) {
		this.border = border;
		changeLayout(changeLayout.NOW);
	}
	public BackgroundColor getBackgroundColor() {
		return backgroundColor;
	}
	
	public ChangeLayout getChangeLayout() {
		return changeLayout;
	}
	public void setChangeLayout(ChangeLayout change_layout) {
		this.changeLayout = change_layout;
	}

	/*
	 * Basic
	 * Get_Set END
	 */
	
	public MyButtonJPanelMouseListener(JPanel jpanel) {
		backgroundColor = new BackgroundColor();
		this.setJPanel(jpanel);
		this.getJPanel().setBorder(getBorder().get(getBorder().NORMAL));
		this.changeLayout(changeLayout.NOW);
		this.getJPanel().setOpaque(true);
	}
	private void changeLayout(String comand) {
		changeLayout.changeLayout(comand);
	}
	
	public void changeBackgroundColor(String key) {
		getJPanel().setBackground(backgroundColor.get(key));
	}
	
	@Override
	public void mouseEntered(MouseEvent e){  
		changeLayout(changeLayout.ENTERED);
    	return;
    }
	
	@Override
	public void mouseExited(MouseEvent e){  
		changeLayout(changeLayout.EXITED);
    	return;
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		changeLayout(changeLayout.PRESSED);
    	return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		changeLayout(changeLayout.RELEASED);
    	return;
	}
	
	/*
	 * addition
	 * Set
	 */
	public void setBackgroundColor_FOCUS(Color color) {
		backgroundColor.set(backgroundColor.FOCUS ,color);
		changeLayout(changeLayout.NOW);
	}
	public void setBackgroundColor_NOFOCUS(Color color) {
		backgroundColor.set(backgroundColor.NOFOCUS ,color);
		changeLayout(changeLayout.NOW);
	}
	public void setBackgroundColor(Color noFocus, Color focus) {
		setBackgroundColor_NOFOCUS(noFocus);
		setBackgroundColor_FOCUS(focus);
	}
	
	
	MyButtonJPanelMouseListener(){
		setChangeLayout(new ChangeLayout00());
	}
}

