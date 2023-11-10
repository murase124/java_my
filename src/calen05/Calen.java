package calen05;

import calen05.dateTimeManagement.DateTimeMmanagement;
import calen05.mainFrame.MainFrame;
import calen05.scheduleManagement.ScheduleMmanagement;
import calen05.scheduleManagement.DAO.DBMmanagement;

public class Calen{
	private static Calen calen;
	private static MainFrame mainFrame;
	
	public static void main(String[] args) {
		/*デバック*/
		DateTimeMmanagement.setPrev(5);
		ScheduleMmanagement.getDaySchedule(DateTimeMmanagement.getNow().plusDays(1));
		//ScheduleMmanagement.updet();//立ち上げ時にスケジュール読み込み
		/*デバックここまで*/
		calen = new Calen();
		mainFrame = new MainFrame();
	}
	
	class Shutdown extends Thread{
	    public void run(){
	      //ここに、アプリケーション終了時に実施する処理を追加します
	    	DBMmanagement.shutdown();
	      System.out.println("終了しました。");
	    }
	  }
	
	public Calen() {
		DateTimeMmanagement.addNextListener(() -> ScheduleMmanagement.next());
		DateTimeMmanagement.addPrevListener(() -> ScheduleMmanagement.prev());
		Runtime.getRuntime().addShutdownHook(new Shutdown() );
	}
}