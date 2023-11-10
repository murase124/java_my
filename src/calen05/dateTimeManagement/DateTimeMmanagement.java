package calen05.dateTimeManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateTimeMmanagement {

	private static LocalDateTime dateTime = LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),1,0,0);
	private static List<DateTimeMmanagementListener> nextListener = new ArrayList<DateTimeMmanagementListener>();
	private static List<DateTimeMmanagementListener> nowListener = new ArrayList<DateTimeMmanagementListener>();
	private static List<DateTimeMmanagementListener> prevListener = new ArrayList<DateTimeMmanagementListener>();

	public static void setDateTime(LocalDateTime date) {
		dateTime = LocalDateTime.of(date.getYear(),date.getMonth(),1,0,0);
		nextListener.forEach(listener->listener.changePerformed());
	}
	public static void setNext(int Month) {
		dateTime = dateTime.plusMonths(Month);
		nextListener.forEach(listener->listener.changePerformed());
	}
	public static void setNext() {
		dateTime =  dateTime.plusMonths(1);
		nextListener.forEach(listener->listener.changePerformed());
	}
	public static void setPrev(int Month) {
		dateTime = dateTime.minusMonths(Month);
		nextListener.forEach(listener->listener.changePerformed());
	}
	public static void setPrev() {
		dateTime = dateTime.minusMonths(1);
		prevListener.forEach(listener->listener.changePerformed());

	}
	public static LocalDateTime getNext() {
		return dateTime.plusMonths(1);
	}
	public static LocalDateTime getNow() {
		return dateTime;
	}
	public static LocalDateTime getPrev() {
		return dateTime.minusMonths(1);
	}
	
	
	public static void addNextListener(DateTimeMmanagementListener listener) {
		nextListener.add(listener);
	}
	public static void addNowListener(DateTimeMmanagementListener listener) {
		nowListener.add(listener);
	}
	public static void addPrevListener(DateTimeMmanagementListener listener) {
		prevListener.add(listener);
	}
}
