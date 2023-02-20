package paint;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class Slider_color_panel extends Color_Panel {
	final int RGB_MIN_INDEX_NUM = 0;
	final int RGB_MAX_INDEX_NUM = 2;
	final int FAT_INDEX_NUM = 3;

	
	JSlider[] slider_RGB = new JSlider[3];
	My_Txtfield_Label[] cardLalabel_RGB = new My_Txtfield_Label[3];
	String[] string_RGB = new String[] {"Red", "Green", "Blue"};
	Timer[] timer_card_RGB = new Timer[] {new Timer(),new Timer(),new Timer()};

	JSlider slider_fat;
	My_Txtfield_Label cardLalabel_fat;
	String string_fat = new String("太さ");
	Timer ftimer_card = new Timer();

	MyPanel MyPanel;
	
	
	Slider_color_panel(MyPanel MyPanel_06_end , JButton switching) {
		this.MyPanel = MyPanel_06_end;
		colorpanel(switching);
	}
	
	public void colorpanel(JButton switching) {
		add(switching);
		
		setBackground(new Color(200,200,200));
		for(int i =0; i <= RGB_MAX_INDEX_NUM; i++) {
			cardLalabel_RGB[i] = new My_Txtfield_Label("0", new Dimension(40, 20));
			cardLalabel_RGB[i].set_label(string_RGB[i]);
			
			slider_RGB[i] = new JSlider(0, 255, 0);
			slider_RGB[i].setPreferredSize(new Dimension(100, 20));
			slider_RGB[i].addChangeListener(new Slider_color_panel_ChangedListener(i));
			
			cardLalabel_RGB[i].textfield.addKeyListener(new Slider_color_panel_ChangedListener(i));;
			cardLalabel_RGB[i].textfield.addActionListener(new Slider_color_panel_ChangedListener(i));
			cardLalabel_RGB[i].textfield.addFocusListener(new Slider_color_panel_ChangedListener(i));
			
			add(cardLalabel_RGB[i]); add(slider_RGB[i]);
		}
		cardLalabel_fat = new My_Txtfield_Label("5", new Dimension(40, 20));
		cardLalabel_fat.set_label(string_fat);
		
		slider_fat = new JSlider(1, 100, 5);
		slider_fat.setPreferredSize(new Dimension(100, 20));
		slider_fat.addChangeListener(new Slider_color_panel_ChangedListener(FAT_INDEX_NUM));
		
		cardLalabel_fat.textfield.addKeyListener(new Slider_color_panel_ChangedListener(FAT_INDEX_NUM));;
		cardLalabel_fat.textfield.addActionListener(new Slider_color_panel_ChangedListener(FAT_INDEX_NUM));
		cardLalabel_fat.textfield.addFocusListener(new Slider_color_panel_ChangedListener(FAT_INDEX_NUM));
		
		add(cardLalabel_fat); add(slider_fat);
	}
	
	@Override
	public void set() {
		slider_fat.setValue(MyPanel.get_line_size());
		int [] color_int = Arrays.copyOf(MyPanel.get_line_color_int(),3);

		int concentration = MyPanel.get_concentration();

		for(int i=0;i<3;i++) {
			if(color_int[i]+concentration <= 255)
			{
				if(color_int[i]+concentration >= 0) {
					color_int[i] = color_int[i]+concentration;
					
				}else {
					color_int[i] = 0;
				}
			}else {
				color_int[i] = 255;
			}
			
		}
		MyPanel.set_concentration(0);
		for(int i=0;i<3; i++)  slider_RGB[i].setValue(color_int[i]);
	}
	


	//カラーパレットのリスナー
	class Slider_color_panel_ChangedListener implements  ActionListener,KeyListener,ChangeListener,FocusListener{
		int i_RGB;
		String color_num="0";
		 
		Slider_color_panel_ChangedListener(int i_RGB){
			 this.i_RGB = i_RGB;
		}
		 
		/*** slider ***/
		//スライダー　ラベルの値変更　カラーを設定
		@Override
		public void stateChanged(ChangeEvent e) {
			MyPanel.set_line_color(new Color(slider_RGB[0].getValue(),slider_RGB[1].getValue(),slider_RGB[2].getValue()));
			MyPanel.set_line_size(slider_fat.getValue());
			
			if(RGB_MIN_INDEX_NUM <= i_RGB && i_RGB <= RGB_MAX_INDEX_NUM) {
				cardLalabel_RGB[i_RGB].set_value("" + slider_RGB[i_RGB].getValue());
				timer_card_RGB[i_RGB].cancel();timer_card_RGB[i_RGB] = new Timer();
				//表記を戻す　”濃さ数値”から”濃さ”
				timer_card_RGB[i_RGB].schedule(new TimerTask() { public void run() { cardLalabel_RGB[i_RGB].set_label(string_RGB[i_RGB]); }}, 1900);
			}else if(FAT_INDEX_NUM == i_RGB) {
				cardLalabel_fat.set_value("" + slider_fat.getValue());
				ftimer_card.cancel();ftimer_card = new Timer();
				//表記を戻す　”濃さ数値”から”濃さ”
				ftimer_card.schedule(new TimerTask() { public void run() { cardLalabel_fat.set_label(string_fat); }}, 1900);
			}
		}
	 
		/*** textfiled ***/
		 //エンターイベント スライダーの値変更　stateChanged連動
		@Override
		public void actionPerformed(ActionEvent e) {
			if(RGB_MIN_INDEX_NUM <= i_RGB && i_RGB <= RGB_MAX_INDEX_NUM) {
				slider_RGB[i_RGB].setValue(Integer.valueOf(cardLalabel_RGB[i_RGB].get_value()));
			}else if(i_RGB == FAT_INDEX_NUM) {
				slider_fat.setValue(Integer.valueOf(cardLalabel_fat.get_value()));
			}
		}
		
		/*** textfiled ***/
		//入力チェック
		@Override
		public void keyTyped(KeyEvent e) {
			if(RGB_MIN_INDEX_NUM <= i_RGB && i_RGB <= RGB_MAX_INDEX_NUM) {
					new My_Key_Filter(cardLalabel_RGB[i_RGB], e , 255);
			}else
			if(i_RGB == FAT_INDEX_NUM) {
				new My_Key_Filter(cardLalabel_fat, e , 100);
			}
		}
		
		//表記　戻す　　スライダーに値設定　stateChanged連動
		@Override
		public void focusLost(FocusEvent e) {
			if(RGB_MIN_INDEX_NUM <= i_RGB && i_RGB <= RGB_MAX_INDEX_NUM) {
				slider_RGB[i_RGB].setValue(Integer.valueOf(cardLalabel_RGB[i_RGB].get_value()));
			}else if(i_RGB == FAT_INDEX_NUM) {
				slider_fat.setValue(Integer.valueOf(cardLalabel_fat.get_value()));
			}
			timer_card_RGB[i_RGB].cancel();timer_card_RGB[i_RGB] = new Timer();
			//表記を戻す　”濃さ数値”から”濃さ”
			timer_card_RGB[i_RGB].schedule(new TimerTask() { public void run() { cardLalabel_RGB[i_RGB].set_label(string_RGB[i_RGB]); }}, 1900);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void focusGained(FocusEvent e) {	}
	}
}


