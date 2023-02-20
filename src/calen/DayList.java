package calen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayList {
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
	
	//曜日 WEEKTEXTの添え字
	final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};
	private ArrayList<Integer> dayWeek = new ArrayList<>();
	public ArrayList<Integer> getDayWeek() {
		return dayWeek;
	}
	public Integer getDayWeek(int key) {
		return dayWeek.get(key);
	}
	public void setDayWeek(Integer values) {
		dayWeek.add(values);
	}
	public void setDayWeek(int key, Integer values) {
		dayWeek.add(key, values);
	}
	
	//年4桁、月2桁、日2桁を区切文字りなし
	private ArrayList<Integer> timeText = new ArrayList<>();
	public ArrayList<Integer> getTimeText() {
		return timeText;
	}
	public Integer getTimeText(int key) {
		return timeText.get(key);
	}
	public void setTimeText(Integer values) {
		timeText.add(values);
	}
	public void setTimeText(int key, Integer values) {
		timeText.add(key, values);
	}
	
	// keyの値　１個目○○年○○月　２個目○○日　valuesの値　予定のID
	private Map<Integer, Map<Integer, ArrayList<Integer>>> scheduleID = new HashMap<>();
	// keyの値　１個目○○年○○月　２個目○○日　3個目ID　valuesの値　予定のタイトル
	private Map<Integer, Map<Integer, Map<Integer, String>>> scheduleTitle = new HashMap<>();
	// keyの値　１個目○○年○○月　２個目○○日　3個目ID　valuesの値　予定の内容
	private Map<Integer, Map<Integer, Map<Integer, String>>> scheduleText = new HashMap<>();
	public void clearScheduleTexts(int time) {
		scheduleText.get(time).clear();
	}
	public int scheduleTextsCount(int time, int day) {
		return scheduleText.get(time).get(day).size();
	}
	public void setScheduleText(int time, Map<Integer, Map<Integer, String>> Texts) {
		scheduleText.get(time).putAll(Texts);
	}
	
	
	
	private Calendar backCalendar;//前月
	private Calendar calendar;//今月
	private Calendar nextCalendar;//来月
	/*
	 * get○○Month OR get○○Year
	 * 年又は月を返す
	 */
	public int getNextMonth() {
		return nextCalendar.get(Calendar.MONTH);
	}
	public int getNextYear() {
		return nextCalendar.get(Calendar.YEAR);
	}
	public int getBackMonth() {
		return backCalendar.get(Calendar.MONTH);
	}
	public int getBackYear() {
		return backCalendar.get(Calendar.YEAR);
	}
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
	private JButton nextButton;
	private JLabel nowButton;
	private JButton backButton;
	public JButton getNextButton() {
		return nextButton;
	}
	public JLabel getNowButton() {
		return nowButton;
	}
	public JButton getBackButton() {
		return backButton;
	}
	
	/*
	 * DayList
	 * JBuutonをDAYBUTTONMAX個作成
	 * フォント設定
	 * フォントカラー　日曜　赤色、　土曜　青色
	 */
	DayList(){
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
		nextButton = new JButton();
		backButton = new JButton();
		nowButton = new JLabel();
		
		createDay();
	}
	
	/*
	 * setAll
	 * 指定の位置に　(key)
	 * ボタンのテキスト、時間、曜日をセット
	 */
	public JButton setAll(int key, String button, Integer time) {
		if(key > DAYBUTTONMAX) return null;
		setTimeText(key, time);
		setDayWeek(key, key % 7);
		return setDayButton(key, button);
	}
	
	/* 
	 * createDay
	 * Calendar型変数作成
	 * 前月 backCalendar
	 * 今月 calendar
	 * 来月 nextCalendar
	 * monthDisplayUpdateメソッド 実行
	 * updetaDayButtonメソッド 実行
	 */
	public void createDay() {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		nextCalendar = (Calendar) calendar.clone();
		nextCalendar.add(Calendar.MONTH, 1);
		backCalendar = (Calendar) calendar.clone();
		backCalendar.add(Calendar.MONTH, -1);
		
		updetaDayButton();
	}
	/*
	 * nextMonth
	 * Calendar型変数を全て一か月後に
	 * monthDisplayUpdateメソッド 実行
	 * updetaDayButtonメソッド 実行
	 */
	public void nextMonth() {
		backCalendar = (Calendar) calendar.clone();
		calendar = (Calendar) nextCalendar.clone();
		nextCalendar.add(Calendar.MONTH, 1);
		
		updetaDayButton();
	}
	/*
	 * nextMonth
	 * Calendar型変数を全て一か月前に
	 * monthDisplayUpdateメソッド 実行
	 * updetaDayButtonメソッド 実行
	 */
	public void backMonth() {
		nextCalendar = (Calendar) calendar.clone();
		calendar = (Calendar) backCalendar.clone();
		backCalendar.add(Calendar.MONTH, -1);
		
		updetaDayButton();
	}
	
	public void updetaDayButton() {
		int updateKey = 0;
		int time;//年4桁、月2桁、日2桁　可読性のための箱
		
		int backlastDay = backCalendar.getActualMaximum(Calendar.DATE);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		
		backCalendar.set(Calendar.DATE, backlastDay);
		int backLastWeek = backCalendar.get(Calendar.DAY_OF_WEEK);
		
		updeteMonthText();
		
		//今月の最初の曜日までの穴埋め
		for(int i =--backLastWeek; i >= 0;i--) {
			time = timMmolding(backCalendar, i-backlastDay);
			setAll(updateKey, String.valueOf(i+backlastDay), time);
			updateKey++;
		}
		//今月
		for(int i =1; i <= lastDay;i++) {
			time = timMmolding(calendar, i+1);
			setAll(updateKey, String.valueOf(i), time);
			updateKey++;
		}
		//余りを穴埋め
		for(int i =1; updateKey < DAYBUTTONMAX;i++) {
			time = timMmolding(nextCalendar, i);
			setAll(updateKey, String.valueOf(i), time);
			updateKey++;
		}
	}
	/*
	 * timMmolding
	 * 年4桁、月2桁、日2桁を連結して返す
	 */
	private int timMmolding(Calendar month, int day) {
		int time;
		time = month.get(Calendar.YEAR);
		time = time*100 + month.get(Calendar.MONTH)+1;
		time = time*100 +day;
		return time;
	}
	
	public void updeteMonthText() {
		nextButton.setText((getNextMonth()+1)+"月");
		nowButton.setText(getNowYear() + "年" + (getNowMonth()+1)+"月");
		backButton.setText((getBackMonth()+1)+"月");
	}

}
