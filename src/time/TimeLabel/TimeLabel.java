package time.TimeLabel;

import javax.swing.JLabel;

public class TimeLabel extends JLabel{
	private int max = 0;//値の最大値　含む
	private int min = 0;//値の最小値　含む
	private Boolean valueChangeFlg = true;
	private int digit = 2;//桁数

	//フィールド関連メソッド
	public Boolean getFlg() {
		if(valueChangeFlg == null) valueChangeFlg = false;
		return valueChangeFlg;
	}
	public void setFlg(Boolean flg) {
		this.valueChangeFlg = flg;
	}
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
		setText(getText());
	}
	//フィールド関連メソッド　ここまで
	public TimeLabel(int max){
		setFlg(true);
		this.max = max;
		setText(0);
	}
	public TimeLabel(String timetext, int max){
		setFlg(true);
		this.max = max;
		setText(timetext);
	}
	public TimeLabel(int timetext, int max){
		setFlg(true);
		this.max = max;
		setText(timetext);
	}
	public TimeLabel(String timetext, int min, int max){
		setFlg(true);
		this.min = min;
		this.max = max;
		setText(timetext);
	}
	public TimeLabel(int timetext, int min, int max){
		setFlg(true);
		this.min = min;
		this.max = max;
		setText(timetext);
	}
	//値を1増やす
	public void up() {
		if(!getFlg()) return;
		try {
			int num = (Integer.parseInt(getText()) + 1 )% (max+1);
			setText(num);
			return;
		} catch (Exception e) {
			return;
		}
		
	}
	//値を1減らす
	public void down() {
		if(!getFlg()) return;
		try {
			int num = (Integer.parseInt(getText())-1);
			num = num < min ? max : num;
			setText(num);
			return;
		} catch (Exception e) {
			 return;
		}
	}
	/*
	 *	値のチェック	
	 *	桁数になるよう0埋め
	 *	マスターのsetText呼び出し
	 */
	@Override
	public void setText(String time) {
		if(!getFlg()) return;
		try {
			if(Integer.parseInt(time) > max) return;
			if(Integer.parseInt(time) < min) return;

			super.setText(String.format("%"+digit+"s", time).replace(" ", "0"));
			return;
		} catch (Exception e) {
			return;
		}
	}
	//int型をString型に変換
	public void setText(int time) {
		if(!getFlg()) return;
		setText(time + "");
	}
}