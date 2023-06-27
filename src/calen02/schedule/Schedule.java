package calen02.schedule;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule {

	//　予定のID
	private int id;
	public int getID() {
		return id;
	}
	// 日付
	private LocalDateTime startDate;
	public LocalDateTime getStartDate() {
		return startDate;
	}
	private LocalDateTime endDate;
	public LocalDateTime getEndDate() {
		return endDate;
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
		this.startDate = startDate;
		this.endDate = endDate;
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
