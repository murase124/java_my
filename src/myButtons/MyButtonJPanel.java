package myButtons;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myButtons.auxiliaryClassList.ChangeLayout;
import myButtons.myButton00.ChangeLayout00;

public class MyButtonJPanel extends JPanel {

	private JPanel jpanel = new JPanel();
	private JComponent textcomponent = null;
	private MyButtonJPanelMouseListener my_Listener;
	
	public MyButtonJPanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		jpanel.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
		my_Listener = new MyButtonJPanelMouseListener(this);

		addMouseListener(my_Listener);
		set_Layout(new ChangeLayout00());
		this.setOpaque(false);
	}
	
	/*
	 * addition
	 * Set_Get
	 */
	public void setText(JComponent text) {
		this.textcomponent = text;
		if(getComponentCount() != 0)removeAll();//コンポーネントがあるなら削除
		add(textcomponent);
	}
	public void setText(String text) {
		this.textcomponent = new JLabel(text);
		if(getComponentCount() != 0)removeAll();//コンポーネントがあるなら削除
		add(textcomponent);
	}
	public JComponent get_text() {
		return getTextcomponent();
	}
	
	public void set_Layout(ChangeLayout change_layout) {
		getMy_Listener().setChangeLayout(change_layout);
		change_layout.set_panel(this);
	}
	public Boolean setCreateEmptyBorder(int top,int left, int bottom, int right) {
		if(textcomponent == null) return false;
		textcomponent.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		return true;
	}
	
	/*
	 * Basic
	 * Set_Get
	 */
	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}


	public JComponent getTextcomponent() {
		return textcomponent;
	}

	public void setTextcomponent(JComponent textcomponent) {
		this.textcomponent = textcomponent;
	}

	public MyButtonJPanelMouseListener getMy_Listener() {
		return my_Listener;
	}

	public void setMy_Listener(MyButtonJPanelMouseListener my_Listener) {
		this.my_Listener = my_Listener;
	}

	

	 
	
}
