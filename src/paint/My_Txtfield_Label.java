package paint;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class My_Txtfield_Label extends JPanel {
	
	CardLayout layout = new CardLayout();
	JTextField textfield = new JTextField();
	JLabel jlabel = new JLabel();
	String value = new String();

	My_Txtfield_Label(){
		constructor();
	}
	My_Txtfield_Label(String text){
		set_value(text);
		constructor();
	}
	My_Txtfield_Label(int text){
		set_value(text);
		constructor();
	}
	My_Txtfield_Label(Dimension size){
		setPreferredSize(size);
		constructor();
	}
	My_Txtfield_Label(String text, Dimension size){
		set_value(text);
		setPreferredSize(size);
		constructor();
	}
	My_Txtfield_Label(int text, Dimension size){
		set_value(text);
		setPreferredSize(size);
		constructor();
	}
	
	void constructor() {
		setLayout(layout);
		add(jlabel , "Label");
		add(textfield , "TextField");
		
		jlabel.addMouseListener(new My_Listener());
		textfield.addActionListener(new My_Listener());
		textfield.addFocusListener(new My_Listener());
	}
	
	void set_value() {
		set_label();
		set_textfield();
	}
	void set_value(int value) {
		this.value = value+"";
		set_value();
	}
	void set_value(String value) {
		this.value = value;
		set_value();
	}
	
	void set_label() {
		jlabel.setText(get_value());
	}
	void set_label(String text) {
		jlabel.setText(text);
	}
	void set_textfield() {
		textfield.setText(get_value());
	}
	
	String get_value() {
		return this.value;
	}
	String get_label() {
		return jlabel.getText();
	}
	String get_textfield() {
		return textfield.getText();
	}
	
	
	void change_label() {
		set_value(get_textfield());
		layout.show(this, "Label");
	}
	void change_textfield() {
		set_value();
		layout.show(this, "TextField");
		textfield.requestFocus();
	}
	
	
	class My_Listener implements ActionListener, MouseListener, FocusListener{//フォーカスが移動する　ラベルにする
		
		//エンター　ラベルにする
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			change_label();
		}
		//ラベルクリック　テキストフィールドにする
		@Override
		public void mouseClicked(MouseEvent e) {
			change_textfield();
		}
		@Override
		public void focusLost(FocusEvent e) {
			change_label();
		}
		
		
		@Override
		public void mousePressed(MouseEvent e) {	}
		@Override
		public void mouseReleased(MouseEvent e) {	}
		@Override
		public void mouseEntered(MouseEvent e) {	}
		@Override
		public void mouseExited(MouseEvent e) {	}
		@Override
		public void focusGained(FocusEvent e) {}
		}
	
}
