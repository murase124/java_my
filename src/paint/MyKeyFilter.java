package paint;

import java.awt.event.KeyEvent;

public class MyKeyFilter {
	
	MyKeyFilter(MyTxtfieldLabel cardLabel, KeyEvent e, int Max){
		
		String color_num = cardLabel.get_value();
		int CaretPosition = cardLabel.textfield.getSelectionStart()+1;
		
		//削除　バックスペース　デリート
		if(Integer.valueOf(e.getKeyChar()) == 8 || Integer.valueOf(e.getKeyChar()) == 127){
			if(cardLabel.get_textfield().length() == 0) {
				color_num = "0";
				CaretPosition = 1;
			}else {
				color_num = cardLabel.get_textfield();
				CaretPosition -= 1;
			}
		}else 
		if('0' <= e.getKeyChar() && e.getKeyChar() <= '9' ) {
			if("0".equals(color_num)) {
				color_num = String.valueOf(e.getKeyChar());
			}else
				//選択　YES：　置き換え　　NO：　挿入　
			if(null != cardLabel.textfield.getSelectedText()) {
				String[] box = color_num.split("");
				color_num = "";
				boolean flg = true;
				for(int i=0;i < box.length;i++) {
					if(i < cardLabel.textfield.getSelectionStart() || cardLabel.textfield.getSelectionEnd() <= i ) {
						color_num += box[i];
					}else {
						if(flg) {
							flg = false;
							color_num += String.valueOf(e.getKeyChar());
						}
					}
				}
			}else {
				if(color_num.length() > (""+Max).length()) CaretPosition -= 1;
				String[] box = color_num.split("");
				color_num = "";
				boolean flg = true;
				
				//挿入箇所
				for(int i=0;i < box.length;i++) {
					//キャレット位置　NO：　同じ　AND　挿入前
					if(i < cardLabel.textfield.getSelectionStart() || !flg ) {
						color_num += box[i];
					}else{
						if(flg) {
							//挿入箇所　途中
							flg = false; i--;
							color_num += String.valueOf(e.getKeyChar());
						}
					}
				}
				if(flg) {
					//挿入箇所　最後
					flg = false;
					color_num += String.valueOf(e.getKeyChar());
				}
			} 
		}else {
			e.consume();
			return;
		}
		
		if(Max < Integer.parseInt(color_num)) color_num = ""+Max;
		if(CaretPosition > color_num.length()) CaretPosition = color_num.length();
		cardLabel.set_value(color_num);
		cardLabel.textfield.setCaretPosition(CaretPosition);
		e.consume();
		return;
	}
}