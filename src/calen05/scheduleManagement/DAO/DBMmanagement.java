package calen05.scheduleManagement.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import calen05.scheduleManagement.Schedule;

public class DBMmanagement {
	private static DB db = new DB();
	
	//scheduleのList型で取得
	public static ArrayList<Schedule> getSchedule(LocalDateTime date) {		
		return getSchedule(date, 1).get(0);
	}
	
	public static ArrayList<ArrayList<Schedule>> getSchedule(LocalDateTime startDate, int size) {		
		if(!db.connection()) {return null;}
		ArrayList<ArrayList<Schedule>> schedule = new ArrayList<ArrayList<Schedule>>();
		try {
			for(int i=0;i< size;i++) {
				schedule.add(new ArrayList<Schedule>());
				ResultSet rs = (db.getSchedule(startDate.plusMonths(i), startDate.plusMonths(i+1)));
				while(rs.next()) {
					
					ArrayList<String> texts= (ArrayList<String>) Arrays.asList(rs.getString(db.ResultSet_TEXTS).split("\n"));
					System.out.print("DBmanagement : ");//デバック
					System.out.println(rs.getInt(db.ResultSet_ID) + "," + rs.getTimestamp(db.ResultSet_STARTTIMESTAMP).toLocalDateTime() + "," + rs.getTimestamp(db.ResultSet_ENDTIMESTAMP).toLocalDateTime() + "," + rs.getTimestamp(db.ResultSet_CREATETIMESTAMP).toLocalDateTime() + "," +  rs.getString(db.ResultSet_TITLE) + "," + rs.getString(db.ResultSet_TEXTS) + "," + rs.getString(db.ResultSet_DELETFLG));//デバック
					schedule.get(i).add(new Schedule(rs.getInt(db.ResultSet_ID), rs.getTimestamp(db.ResultSet_STARTTIMESTAMP).toLocalDateTime(),  rs.getTimestamp(db.ResultSet_ENDTIMESTAMP).toLocalDateTime(), rs.getString(db.ResultSet_TITLE),  texts));
				}
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		db.close();
		return schedule;
	}
	
	public static int updateSchedule(Schedule schedule) {
		String texta  = String.join("\n" ,schedule.getTexts());
		return db.updateSchedule(schedule.getID(), schedule.getStartDateTime(), schedule.getEndDateTime(), schedule.getTitle(), texta);
	}
	
	
	
	public static void shutdown() {
		db.close();
	}
}
