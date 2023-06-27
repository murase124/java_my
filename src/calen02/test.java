package calen02;

import java.util.Calendar;

public class test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		// 処理前の時刻を取得
        long startTime = System.currentTimeMillis();
        
        Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		Calendar backCalendar = (Calendar) calendar.clone();
		backCalendar.add(Calendar.MONTH, -1);
		Calendar nextCalendar = (Calendar) calendar.clone();
		nextCalendar.add(Calendar.MONTH, 1);

		for(int i=0;i<10000000;i++) {
			Calendar nextCalendar1 = (Calendar) calendar.clone();
			Calendar nextCalendar2 = (Calendar) calendar.clone();
			Calendar nextCalendar3 = (Calendar) calendar.clone();
			Calendar nextCalendar4 = (Calendar) calendar.clone();

		}
		
		 // 処理後の時刻を取得
        long endTime = System.currentTimeMillis();
 
        System.out.println("開始時刻：" + startTime + " ms");
        System.out.println("終了時刻：" + endTime + " ms");
        System.out.println("処理時間：" + (endTime - startTime) + " ms");
	}

}
