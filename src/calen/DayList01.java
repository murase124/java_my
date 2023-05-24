package calen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DayList01 {
	//日にちのボタン
	private final int DAYBUTTONMAX = 42;
	private ArrayList<JButton> dayButton = new ArrayList<>();
	public ArrayList<JButton> getDayButton() {
		return dayButton;
	}
	public JButton getDayButton(int key) {
		return dayButton.get(key);
	}
	public JButton setDayButton(int key, String values) {
		getDayButton(key).setText(values);
		return dayButton.get(key);
	}
	public JButton setDayButton(JButton values) {
		dayButton.add(values);
		return values;
	}
	
	//年4桁、月2桁、日2桁を区切文字りなし
	private ArrayList<Integer> timeDay = new ArrayList<>();
	public ArrayList<Integer> getTimeDay() {
		return timeDay;
	}
	public Integer getTimeDay(int key) {
		return timeDay.get(key);
	}
	private void setTimeDay(int key, Integer values) {
		timeDay.add(key, values);
	}
	
	//曜日 WEEKTEXTの添え字
		final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};
		private ArrayList<Integer> dayWeek = new ArrayList<>();
		public ArrayList<Integer> getDayWeek() {
			return dayWeek;
		}
		public Integer getDayWeek(int key) {
			return dayWeek.get(key);
		}
		private void setDayWeek(int key, Integer values) {
			dayWeek.add(key, values);
		}
	
	/**/
	private Calendar calendar;//今月
	/*
	 * get○○Month OR get○○Year
	 * 年又は月を返す
	 */
	public int getNowMonth() {
		return calendar.get(Calendar.MONTH);
	}
	public int getNowYear() {
		return calendar.get(Calendar.YEAR);
	}
	/*
	 * get○○Month OR get○○Year　ここまで
	 */
	
	private JPanel dayPanel;//各日のJButtonを表示
	public JPanel getDayPanel() {
		return dayPanel;
	}
	
	/*
	 * setAll
	 * 指定の位置に　(key)
	 * ボタンのテキスト、時間、曜日をセット
	 */
	public JButton setAll(int id, int day) {
		if(id > DAYBUTTONMAX) return null;
		setTimeDay(id, day);
		setDayWeek(id, id % 7);
		return setDayButton(id, String.valueOf(day));
	}
	
	/* 
	 * createDay
	 * Calendar型変数作成
	 * 今月 calendar
	 */
	public void createDay() {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		updetaDayButton();
	}
	public void setDay(int month) {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		updetaDayButton();
	}
	
	public void updetaDayButton() {
		int updateKey = 0;
		
		Calendar nextCalendar = (Calendar) calendar.clone();
		nextCalendar.add(Calendar.MONTH, 1);
		Calendar backCalendar = (Calendar) calendar.clone();
		backCalendar.add(Calendar.MONTH, -1);
		
		int backlastDay = backCalendar.getActualMaximum(Calendar.DATE);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		
		backCalendar.set(Calendar.DATE, backlastDay);
		int backLastWeek = backCalendar.get(Calendar.DAY_OF_WEEK);
		
		
		//今月の最初の曜日までの穴埋め
		for(int i =--backLastWeek; i >= 0;i--) {
			setAll(updateKey, backlastDay-i);
			updateKey++;
		}
		//今月
		for(int i =1; i <= lastDay;i++) {
			setAll(updateKey, i);
			updateKey++;
		}
		//余りを穴埋め
		for(int i =1; updateKey < DAYBUTTONMAX;i++) {
			setAll(updateKey, i);
			updateKey++;
		}
	}
	
	/*
	 * Day
	 * JBuutonをDAYBUTTONMAX個作成
	 * フォント設定
	 * フォントカラー　日曜　赤色、　土曜　青色
	 */
	DayList01(){
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(6, 7, 2, 3));

		for(int i=0;i < DAYBUTTONMAX;i++) {
			JButton jbutton = setDayButton(new JButton());
			//フォント 
			jbutton.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
			jbutton.setPreferredSize(new Dimension(40, 20));
			if((i % 7) == 0) jbutton.setForeground(Color.RED);
			if((i % 7) == 6) jbutton.setForeground(Color.BLUE);
			//パネルにボタンを追加
			dayPanel.add(jbutton);
		}
				
		createDay();
	}
}
