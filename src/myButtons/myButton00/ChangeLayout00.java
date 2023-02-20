package myButtons.myButton00;

import myButtons.auxiliaryClassList.ChangeLayout;

public class ChangeLayout00 extends ChangeLayout{
	
	public ChangeLayout00(){
		changeLayout_num = NORMALMODE;
	}
	
	private int changeLayout_num;//
	final int NORMALMODE = 0;
	final int FOCUSMODE = 1;
	final int PRESSEDMODE = 2;
	public void changeLayout(int num) {
		
			if(num == NORMALMODE) {
				getMy_Listener().changeBackgroundColor(getBackgroundColor().NOFOCUS);
			}else if(num == FOCUSMODE) {
				getMy_Listener().getJPanel().setBorder(getMy_Border().get(getMy_Border().NORMAL));
				getMy_Listener().changeBackgroundColor(getBackgroundColor().FOCUS);
			}else if(num == PRESSEDMODE) {
				getMy_Listener().getJPanel().setBorder(getMy_Border().get(getMy_Border().PRESSED));
			}
	}
	
	@Override
	public void changeLayout(String comand) {
		/*
		 * ボタンの表示変更処理
		 * MouseListener基準 
		 * NOW 今の状態で更新
		 * 
		 */
		if(comand.equals(ENTERED)) {
			changeLayout(++changeLayout_num);
		}else if(comand.equals(EXITED)) {
			changeLayout(--changeLayout_num);
		}else if(comand.equals(PRESSED)) {
			changeLayout(++changeLayout_num);
		}else if(comand.equals(RELEASED)) {
			changeLayout(--changeLayout_num);
		}else if(comand.equals(NOW)) {
			changeLayout(changeLayout_num);
		}
	}
	
}
