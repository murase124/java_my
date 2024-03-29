package verticalTextLabal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VerticalText extends JPanel{
	String string = "";
	Font font = null;
	Color color = null;
	boolean two_digits_flg = true; //数字が２個の場合　横並びで記載
	boolean alphabet_beside_flg = true; //英字を　右回転で横で記載
	String int_text = "";

	public VerticalText() {
	}
	public VerticalText(String string) {
		this.string = string;
		
		setText(string);
	}
	public VerticalText(String string, Font font) {
		this.string = string;
		this.font = font;
		
		setText(string);
	}
	
	public VerticalText(String string, Color color) {
		this.string = string;
		this.color = color;
		
		setText(string);
	}
	public VerticalText(String string, Font font, Color color) {
		this.string = string;
		this.font = font;
		this.color = color;
		
		setText(string);
	}
	public VerticalText(String string, Font font, Color color, boolean two_digits_flg, boolean alphabet_beside_flg) {
		this.font = font;
		this.color = color;
		this.two_digits_flg = two_digits_flg;
		this.alphabet_beside_flg = alphabet_beside_flg;
		
		setText(string);
	}
	
	public void setText(String string) {
		this.string = string;
		update();
	}public void set_font(Font font) {
		this.font = font;
		update();
	}public void set_color(Color color) {
		this.color = color;
		update();
	}public void set_two_digits_flg(boolean two_digits_flg) {
		this.two_digits_flg = two_digits_flg;
		update();
	}public void set_alphabet_beside_flg(boolean alphabet_beside_flg) {
		this.alphabet_beside_flg = alphabet_beside_flg;
		update();
	}public void update() {
		main_process(this.string);
	}public String get_string() {
		return this.string;
	}
	
	//文字列を分ける
	public void main_process(String string) {
		 removeAll();
	     repaint();
	        
		if(color != null) {
			setBackground(color);	
			setOpaque(true);
		}else {
			setOpaque(false);
		}
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		String[] strArray = string.split("");
		for(String s : strArray) {
			if(int_text(s)) continue;
			Jlavel_add(s);
		}	
		int_text("");
		
	}
	
	//パネルに追加
	public void Jlavel_add(String text) {
		JLabel jlabel = new JLabel(text);
		if(font != null) jlabel.setFont(font);
		
		jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(jlabel );
		conversion(jlabel);
	}
	
	
	//数字が二文字なら半角数字で横に並べ その他　全角数字
	public boolean int_text(String text) {
		if(!text.equals("")) {
			if(Character.isDigit(text.charAt(0))) {
				int_text += text;
				return true;
			}
		}
		if(!int_text.equals("")) {
			//数字が２桁のとき
			if(int_text.length() == 2 && two_digits_flg) {
				Jlavel_add(toHalfWidth(int_text));
			}else {
				String[] int_text_Arrays = toFullWidth(int_text).split("");
				for(String int_text_Array : int_text_Arrays) {
					Jlavel_add(int_text_Array);
				}
			}
			int_text = "";
		}
		return false;
	}
	
	//「半角数字」を「全角数字」へ変換処理を実施する。
	public static String toFullWidth(String s) {
		  StringBuilder sb = new StringBuilder(s);
		  for (int i = 0; i < s.length(); i++) {
		    char c = s.charAt(i);
		    if (0x30 <= c && c <= 0x39) {
		      sb.setCharAt(i, (char) (c + 0xFEE0));
		    }
		  }
		  return sb.toString();
		}
	
	//「全角数字」を「半角数字」へ変換処理を実施する。
	public static String toHalfWidth(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			//0から9
			if (0xFF10 <= c && c <= 0xFF19) sb.setCharAt(i, (char) (c - 0xFEE0));
		}
	 	return sb.toString();
	}
		
	
	//回転
	public void conversion(JLabel label) {
        String text = label.getText();
        
        if(text.equals("、")) {
        	label.setUI(new Dot());
        }else if(text.equals("。")) {
        	label.setUI(new Period());
        }else if(text.equals("「")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if(text.equals("」")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if(text.equals("ー")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if(text.equals("＜")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if(text.equals("＞")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if(text.equals("《")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if(text.equals("》")) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if('A' <= text.charAt(0)  && text.charAt(0) <= 'Z' && alphabet_beside_flg) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }else if('a' <= text.charAt(0)  && text.charAt(0) <= 'z' && alphabet_beside_flg) {
        	label.setUI(new RotateTextLabelUI(RotateTextLabelUI.ROTATE_RIGFHT));
        }
	}

}
