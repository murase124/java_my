package calen05.scheduleManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import calen05.scheduleManagement.DAO.DBMmanagement;

public class Schedules {
	/* スケジュール */
	/* 選択した特定のスケジュール */
	private Schedule oneSchedule = null;
	public Schedule getOneSchedule() {
		return oneSchedule;
	}
	public void setOneSchedule(Schedule schedule) {
		oneSchedule = schedule;
	}
	
	/* 選択した日のスケジュール */
	//選択日のスケジュール
	private List<Schedule> daySchedule = null;
	public List<Schedule> getDaySchedule(LocalDateTime start) {
		LocalDateTime end = start.plusDays(1);
		//isBefore  getStartDateTime()がendより前
		//isAfter   getEndDateTime()がstartより後
		//isEqual   getEndDateTime()がstartと等しい
		daySchedule = schedule.stream().filter(schedule -> schedule.getStartDateTime().isBefore(end) && (schedule.getEndDateTime().isAfter(start) || schedule.getEndDateTime().isEqual(start))).collect(Collectors.toList());
		return daySchedule;
	}
	public List<Schedule> getDaySchedule() {
		return daySchedule;
	}
	
	/* 各3月分ごとのリスト */
	public final static int NEXTUPDETE = 0;//次の月
	public final static int NOWUPDETE = 1;//今の月
	public final static int PREVUPDETE = 2;//前の月
	//３月管理するリスト
	private ArrayList<ArrayList<Schedule>> threeMonthsSchedule = new ArrayList<ArrayList<Schedule>>();
	//次月スケジュール
	public ArrayList<Schedule> getNextSchedule() {
		return threeMonthsSchedule.get(NEXTUPDETE);
	}
	public void setNextSchedule(ArrayList<Schedule> nextSchedule) {
		threeMonthsSchedule.add(NEXTUPDETE, nextSchedule);
	}
	//今月スケジュール
	public ArrayList<Schedule> getNowSchedule() {
		return threeMonthsSchedule.get(NOWUPDETE);
	}
	public void setNowSchedule(ArrayList<Schedule> mowSchedule) {
		threeMonthsSchedule.add(NOWUPDETE, mowSchedule);
	}
	//前月スケジュール
	public ArrayList<Schedule> getPrevSchedule() {
		return threeMonthsSchedule.get(PREVUPDETE);
	}
	public void setPrevSchedule(ArrayList<Schedule> prevSchedule) {
		threeMonthsSchedule.add(PREVUPDETE, prevSchedule);
	}

	/* 表示する3か月のスケジュールを管理 */
	//3か月分のスケジュール
	private List<Schedule> schedule;
	//取得
	public List<Schedule> getSchedule() {
		return schedule;
	}
	//各月のスケジュールリストを結合
	public void updateSchedule() {
		List<Schedule> tmp = Stream.concat(getNextSchedule().stream(), getNowSchedule().stream()).collect(Collectors.toList());
        this.schedule = Stream.concat(getPrevSchedule().stream(), tmp.stream()).collect(Collectors.toList());
	}
	
	/* データベースからスケジュールを取得 */
	//特定月のスケジュールを取得して返却
	public ArrayList<Schedule> updateSchedule(LocalDateTime date) {		
		return DBMmanagement.getSchedule(date);
	}
	//すべての月のスケジュールを更新
	public void allUpdateSchedule(LocalDateTime date) {
		ArrayList<ArrayList<Schedule>>  scheduleList= DBMmanagement.getSchedule(date.minusMonths(1), 3);
		setPrevSchedule(scheduleList.get(0));
		setNowSchedule(scheduleList.get(1));
		setNextSchedule(scheduleList.get(2));
		updateSchedule();
	}
	
	/* コンストラクタ */
	//表示する3か月分のスケジュール
	public Schedules(LocalDateTime dateTime) {
		allUpdateSchedule(dateTime);
	}
}
