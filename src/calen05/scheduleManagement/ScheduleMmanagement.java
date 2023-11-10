package calen05.scheduleManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import calen05.dateTimeManagement.DateTimeMmanagement;

public class ScheduleMmanagement {
		
	/* スケジュールリストクラス */
	private static Schedules schedules = new Schedules(DateTimeMmanagement.getNow());
	
	/* 選択した特定スケジュール */
	public static Schedule getOneSchedule() {
		return schedules.getOneSchedule();
	}
	//特定日のListで先頭から何個目
	public static void setIndexOneSchedule(int num) {
		try {
			schedules.setOneSchedule(getDaySchedule().get(num));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("err," + num);
		}
	}
	//IDで検索
	public static void setIDOneSchedule(int num) {
		try {
			schedules.setOneSchedule(schedules.getSchedule().stream().filter(x -> x.getID() == num).findFirst().get());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("err," + num);
		}
	}
	
	/* 選択した特定日のスケジュール管理 */
	//特定日の時点の今月(真ん中の月)
	private static LocalDateTime dayNowMonthDate = DateTimeMmanagement.getNow();
	public static LocalDateTime getDayMonthDate() {
		return dayNowMonthDate;
	}
	//特定日
	private static LocalDateTime dayDate = DateTimeMmanagement.getNow();
	public static LocalDateTime getDayDate() {
		return dayDate;
	}
	//特定日を取得
	public static List<Schedule> getDaySchedule(LocalDateTime start) {
		dayNowMonthDate = DateTimeMmanagement.getNow();
		dayDate = LocalDateTime.of(start.getYear(),start.getMonth(),start.getDayOfMonth(),0,0);
		return schedules.getDaySchedule(dayDate);
	}
	//設定されている特定日スケジュールを取得
	public static List<Schedule> getDaySchedule() {
		return schedules.getDaySchedule();
	}
	
	/* 特定スケジュールをデータベースから更新 */
	public static Schedule updateOneSchedule() {
		if(dayDate.getMonthValue() == DateTimeMmanagement.getNext().getMonthValue()) update(Schedules.NEXTUPDETE);
		if(dayDate.getMonthValue() == DateTimeMmanagement.getNow().getMonthValue()) update(Schedules.NOWUPDETE);
		if(dayDate.getMonthValue() == DateTimeMmanagement.getPrev().getMonthValue()) update(Schedules.PREVUPDETE);
		
		getDaySchedule(dayDate);
		setIDOneSchedule(getOneSchedule().getID());
		return getOneSchedule();
		
	}
	
	/* スケジュールをデータベースから取得する */
	//特定月のスケジュールを更新
	private static void update(int num) {
		switch (num) {
			case Schedules.NEXTUPDETE: {
				schedules.getNextSchedule().clear();
				schedules.setNextSchedule(schedules.updateSchedule(DateTimeMmanagement.getNext()));
				break;
			}
			case Schedules.NOWUPDETE: {
				schedules.getNowSchedule().clear();
				schedules.setNowSchedule(schedules.updateSchedule(DateTimeMmanagement.getNow()));
				break;
			}
			case Schedules.PREVUPDETE: {
				schedules.getPrevSchedule().clear();
				schedules.setPrevSchedule(schedules.updateSchedule(DateTimeMmanagement.getPrev()));
				break;
			}
		}
		schedules.updateSchedule();
	}
	//表示月を１月分来月へ
	@SuppressWarnings("unchecked")
	public static void next() {
		schedules.setPrevSchedule((ArrayList<Schedule>) schedules.getNowSchedule().clone());
		schedules.setNowSchedule((ArrayList<Schedule>) schedules.getNextSchedule().clone());
		update(Schedules.NEXTUPDETE);
	}
	//表示月を１月分前月へ
	@SuppressWarnings("unchecked")
	public static void prev() {
		schedules.setNextSchedule((ArrayList<Schedule>) schedules.getNowSchedule().clone());
		schedules.setNowSchedule((ArrayList<Schedule>)  schedules.getPrevSchedule().clone());
		update(Schedules.PREVUPDETE);
    }
}
