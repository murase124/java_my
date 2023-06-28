package calen04.ScheduleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import calen04.DAO.DB;

public class Schedules {
	/* スケジュール */
	//選択日のスケジュール
	private List<Schedule> daySchedule;
	public List<Schedule> getDaySchedule(LocalDateTime start) {
		LocalDateTime end = start.plusDays(1);
		daySchedule = schedule.stream().filter(schedule -> schedule.getStartDate().isBefore(end) && (schedule.getEndDate().isAfter(start) || schedule.getEndDate().isEqual(start))).collect(Collectors.toList());
		return daySchedule;
	}
	public List<Schedule> getDaySchedule() {
		return daySchedule;
	}
	
	//表示する3か月の１番先の月スケジュール
	private ArrayList<Schedule> nextSchedule = new ArrayList<Schedule>();
	//表示する3か月の２番先の月スケジュール
	private ArrayList<Schedule> mowSchedule = new ArrayList<Schedule>();
	//表示する3か月の３番先の月スケジュール
	private ArrayList<Schedule> backSchedule = new ArrayList<Schedule>();
	//表示する3か月分のスケジュール
	private List<Schedule> schedule;
	public void setSchedule() {
		List<Schedule> tmp = Stream.concat(nextSchedule.stream(), mowSchedule.stream()).collect(Collectors.toList());
        this.schedule = Stream.concat(backSchedule.stream(), tmp.stream()).collect(Collectors.toList());
	}
	//スケジュールをデータベースから取得する
	private void setSchedule(ArrayList<Schedule> schedule, LocalDateTime date) {
		DB db = new DB();
		ResultSet rs = (db.getSchedule(date ,date.plusMonths(1)));
		try {
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "," + rs.getTimestamp(2).toLocalDateTime() + "," + rs.getTimestamp(3).toLocalDateTime() + "," + rs.getTimestamp(4).toLocalDateTime() + "," +  rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7));
				schedule.add(new Schedule(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(),  rs.getTimestamp(3).toLocalDateTime(), rs.getString(5),  rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	//表示月を１月分来月へ
	@SuppressWarnings("unchecked")
	public void nextSchedule(LocalDateTime date) {
		backSchedule =  (ArrayList<Schedule>) mowSchedule.clone();
		mowSchedule =  (ArrayList<Schedule>) nextSchedule.clone();
		nextSchedule.clear();
		setSchedule(nextSchedule, date.plusMonths(1));
		setSchedule();
	}
	//表示月を１月分前月へ
	@SuppressWarnings("unchecked")
	public void backSchedule(LocalDateTime date) {
		nextSchedule = (ArrayList<Schedule>) mowSchedule.clone();
		mowSchedule = (ArrayList<Schedule>)  backSchedule.clone();
		backSchedule.clear();
		setSchedule(backSchedule, date.minusMonths(1));
		setSchedule();
    }
	//表示する3か月分のスケジュール
	public Schedules(LocalDateTime date) {
		setSchedule(nextSchedule, date.plusMonths(1));
		setSchedule(mowSchedule, date);
		setSchedule(backSchedule, date.minusMonths(1));
		setSchedule();
	}
}
