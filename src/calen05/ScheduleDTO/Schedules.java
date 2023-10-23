package calen05.ScheduleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import calen05.DAO.DB;

public class Schedules {
	/* スケジュール */
	//日付
	LocalDateTime date;
	
	//選択日のスケジュール
	private List<Schedule> daySchedule;
	public List<Schedule> getDaySchedule(LocalDateTime start) {
		LocalDateTime end = start.plusDays(1);
		daySchedule = schedule.stream().filter(schedule -> schedule.getStartDateTime().isBefore(end) && (schedule.getEndDateTime().isAfter(start) || schedule.getEndDateTime().isEqual(start))).collect(Collectors.toList());
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
	public final int NEXTUPDETE = 0;
	public final int NOWUPDETE = 1;
	public final int BACKUPDETE = 2;
	public void updateSchedule(int num) {
		switch (num) {
			case NEXTUPDETE: {
				updateSchedule(nextSchedule, date.plusMonths(1));
				break;
			}
			case NOWUPDETE: {
				updateSchedule(mowSchedule, date);
				break;
			}
			case BACKUPDETE: {
				updateSchedule(backSchedule, date.minusMonths(1));
				break;
			}
		}
	}
	private void updateSchedule(ArrayList<Schedule> schedule, LocalDateTime date) {
		DB db = new DB();
		ResultSet rs = (db.getSchedule(date ,date.plusMonths(1)));
		try {
			while(rs.next()) {
				//デバック
				System.out.println(rs.getInt(db.ResultSet_ID) + "," + rs.getTimestamp(db.ResultSet_STARTTIMESTAMP).toLocalDateTime() + "," + rs.getTimestamp(db.ResultSet_ENDTIMESTAMP).toLocalDateTime() + "," + rs.getTimestamp(db.ResultSet_CREATETIMESTAMP).toLocalDateTime() + "," +  rs.getString(db.ResultSet_TITLE) + "," + rs.getString(db.ResultSet_TEXTS) + "," + rs.getString(db.ResultSet_DELETFLG));
				schedule.add(new Schedule(rs.getInt(db.ResultSet_ID), rs.getTimestamp(db.ResultSet_STARTTIMESTAMP).toLocalDateTime(),  rs.getTimestamp(db.ResultSet_ENDTIMESTAMP).toLocalDateTime(), rs.getString(db.ResultSet_TITLE),  rs.getString(db.ResultSet_TEXTS)));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	//表示月を１月分来月へ
	@SuppressWarnings("unchecked")
	public void nextSchedule() {
		this.date = this.date.plusMonths(1);
		backSchedule =  (ArrayList<Schedule>) mowSchedule.clone();
		mowSchedule =  (ArrayList<Schedule>) nextSchedule.clone();
		nextSchedule.clear();
		updateSchedule(NEXTUPDETE);
		setSchedule();
	}
	//表示月を１月分前月へ
	@SuppressWarnings("unchecked")
	public void backSchedule() {
		this.date = this.date.minusMonths(1);
		nextSchedule = (ArrayList<Schedule>) mowSchedule.clone();
		mowSchedule = (ArrayList<Schedule>)  backSchedule.clone();
		backSchedule.clear();
		updateSchedule(BACKUPDETE);
		setSchedule();
    }
	//表示する3か月分のスケジュール
	public Schedules(LocalDateTime date) {
		this.date = date;
		updateSchedule(NEXTUPDETE);
		updateSchedule(NOWUPDETE);
		updateSchedule(BACKUPDETE);
		setSchedule();
	}
}
