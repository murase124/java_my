package paint;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

class Defalt_color_panel extends Color_Panel{
	
	MyPanel MyPanel;

	//色
	ButtonGroup Colorgroup = new ButtonGroup();
	JRadioButton[] colorButton = new JRadioButton[5];
	JRadioButton others;
	String[] colortext = new String[] {
			"黒","白","赤","緑","青"};
	Color[] color = new Color[] {Color.BLACK,Color.WHITE,Color.RED,Color.GREEN,Color.BLUE,};
		
	//濃さ
	JSlider sl = new JSlider(0, 510 , 255);
	My_Txtfield_Label cardLalabel_Darkness;
	Timer timer = new Timer("Darkness");
		
	//線太さ
	JComboBox<String> comb = new JComboBox<String>();
	int line_size = 5;
	
	//カラーパレット
	JButton bu = new JButton("色");
	
	Defalt_color_panel(MyPanel MyPanel , JButton switching){
		this.MyPanel = MyPanel;
		colorpanel(switching);
	}
	
	public void colorpanel(JButton switching) {
		add(switching);
		
		setBackground(new Color(200,200,200));
		
		//色の選択肢
		for(int i=0;i < colorButton.length; i++) {
			colorButton[i] = new JRadioButton();
			colorButton[i].setText(colortext[i]);
			Colorgroup.add(colorButton[i]);
			colorButton[i].addActionListener(new colorListener());
			add(colorButton[i]);
		}
		colorButton[0].setSelected(true);
		
		others = new JRadioButton();
		others.setText("カスタム");
		Colorgroup.add(others);
		add(others);

		//濃さ
		sl.addChangeListener(new darknessListener());
		sl.setPreferredSize(new Dimension(140, 20));
		
		cardLalabel_Darkness = new My_Txtfield_Label("0",new Dimension(25, 20));
		cardLalabel_Darkness.set_label("濃さ");
		cardLalabel_Darkness.textfield.addKeyListener(new darknessListener());
		cardLalabel_Darkness.textfield.addActionListener(new darknessListener());
		add(cardLalabel_Darkness);
		add(sl);
		
		//線太さ
		comb.addItem("カスタム");
		for(int i=1; i <= 5; i++)comb.addItem("" + i);
		for(int i=10; i <= 20; i += 5)comb.addItem("" + i);
		comb.addItem("" + 50);
		comb.setSelectedIndex(5);

		comb.addActionListener(new fatListener());
		comb.addPopupMenuListener(new fatListener());
		add(comb);
		
		//カラーパレット
		bu.addActionListener(new paletteListener());
		add(bu);
		
	}
	
	//このパネルを呼び出すときに
	@Override
	public void set() {
		//int[] color_int = Arrays.copyOf(MyPanel.get_line_color_int(), 3);
		Colorgroup.setSelected(others.getModel(), true);
		
		int size = MyPanel.get_line_size();
		comb.removeItemAt(0);
		comb.insertItemAt(""+size,0);
		comb.setSelectedIndex(0);
	}

	//色
	class colorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i < colorButton.length; i++)
				if(e.getSource() == colorButton[i]) MyPanel.set_line_color(color[i]);
		}
	}
	
	//線の太さ
	class fatListener implements ActionListener, PopupMenuListener {
		//太さを選択
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(Integer.parseInt(comb.getSelectedItem().toString()) != MyPanel.get_line_size())  {
					line_size = Integer.parseInt(comb.getSelectedItem().toString());
					MyPanel.set_line_size(Integer.parseInt(comb.getSelectedItem().toString()));
				}
			} catch (NumberFormatException nfex) {
				for(int i=0; i < comb.getItemCount();i++) {
					if((""+MyPanel.get_line_size()).equals(comb.getItemAt(i))) {
						comb.setSelectedIndex(i);
					}
				}
			}
		}
		
		//comb の項目リストと閉じるとき　描画
		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			Timer Popup_timer = new Timer();
			Popup_timer.schedule(new TimerTask() {
				@Override
				public void run() {
					 MyPanel.paint_update();
				}
			},6);
		}
		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {	}
		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {	}
		
	}
	
	//色の濃さ
	class darknessListener implements  ChangeListener,KeyListener,ActionListener{
		//スライダーの値を設定
		@Override
		public void stateChanged(ChangeEvent e) {
			//色の濃さ
			cardLalabel_Darkness.set_label("" + (sl.getValue()));
			MyPanel.set_concentration(255-sl.getValue());
			timer.cancel();
			timer = new Timer();
			//表記を戻す　”濃さ数値”から”濃さ”
			TimerTask task = new TimerTask() {
				public void run() {
					cardLalabel_Darkness.set_label("濃さ");
					timer.cancel();
				}
			};
			timer.schedule(task, 2400);
		}
		
		//スライダーに値を設定　stateChangedが連動
		@Override
		public void actionPerformed(ActionEvent e) {
			sl.setValue(Integer.parseInt(cardLalabel_Darkness.get_value()));
		}
		
		//フィルター
		@Override
		public void keyTyped(KeyEvent e) {
			new My_Key_Filter(cardLalabel_Darkness,e,510);
		}

		@Override
		public void keyPressed(KeyEvent e) {	}
		@Override
		public void keyReleased(KeyEvent e) {	}
	}
	
	//カラーパネル
	class paletteListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//カラーパレット
			try {
				MyPanel.set_line_color(JColorChooser.showDialog(MyPanel, "色の選択", Color.WHITE));
			}catch (Exception color_e) {
			}
		}
		
	}
}
