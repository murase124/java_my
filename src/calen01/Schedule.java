package calen01;

import java.util.ArrayList;

public class Schedule {

	//　予定のID
	private int scheduleID;
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	// 日付
	private String scheduleStartDate;
	public String getScheduleStartDate() {
		return scheduleStartDate;
	}
	public void setScheduleStartDate(String scheduleDate) {
		this.scheduleStartDate = scheduleDate;
	}
	private String scheduleEndDate;
	public String getScheduleEndDate() {
		return scheduleEndDate;
	}
	public void setScheduleEndDate(String scheduleDate) {
		this.scheduleEndDate = scheduleDate;
	}
	//　時刻
	private String scheduleStartTime;
	public String getScheduleStartTime() {
		return scheduleStartTime;
	}
	public void setScheduleStartTime(String scheduleTime) {
		this.scheduleStartTime = scheduleTime;
	}
	private String scheduleEndTime;
	public String getScheduleEndTime() {
		return scheduleEndTime;
	}
	public void setScheduleEndTime(String scheduleTime) {
		this.scheduleEndTime = scheduleTime;
	}
	//　予定のタイトル
	private String scheduleTitle;
	public String getScheduleTitle() {
		return scheduleTitle;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}
	// 予定の内容
	private ArrayList<String> scheduleText = new ArrayList<>();
	public void clearScheduleTexts() {
		scheduleText.clear();
	}
	public int scheduleTextsCount() {
		return scheduleText.size();
	}
	public void setScheduleText(String Texts) {
		scheduleText.add(Texts);
	}
	
	public void setSchedule(int id, String startDate, String startTime, String endDate, String endTime, String title, ArrayList<String> text) {
		
	}
	/* 保留
	// 予定を繰り返す
	private int loopflg;
	private int loopday;
	private int loopmonth;
	private int loopweek;
	final public String LOOPTEXT[] = { "しない" , "毎日" , "毎週" , "毎月" , "毎年" };
	public Boolean setloopflg(int flg) {
		if(0 <= flg && flg < LOOPTEXT.length) {
			loopflg = flg;
			return true;
		}
		return false;
	}*/
	

}
