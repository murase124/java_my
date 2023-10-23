package calen05.ScheduleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule {

	//　予定のID
	private int id;
	public int getID() {
		return id;
	}
	// 日付
	private LocalDateTime startDateTime;
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	private LocalDateTime endDateTime;
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	//　予定のタイトル
	private String title;
	public String getTitle() {
		return title;
	}
	// 予定の内容
	private ArrayList<String> texts = new ArrayList<>();
	public ArrayList<String> getTexts() {
		return texts;
	}
	
	public void setSchedule(int id, LocalDateTime startDate, LocalDateTime endDate, String title, ArrayList<String> text) {
		this.id = id;
		this.startDateTime = startDate;
		this.endDateTime = endDate;
		this.title = title;
		this.texts = text;
	}
	public Schedule(int id, LocalDateTime startDate, LocalDateTime endDate, String title, ArrayList<String> text){
		setSchedule(id,startDate,endDate,title,text);
	}
	public Schedule(int id, LocalDateTime startDate, LocalDateTime endDate, String title, String text){
		ArrayList<String> texts = new ArrayList<String>();
		texts.add(text);
		
		setSchedule(id,startDate,endDate,title,texts);
	}
	
}
