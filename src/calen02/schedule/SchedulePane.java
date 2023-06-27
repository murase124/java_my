package calen02.schedule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JPanel;

import calen02.DB;

public class SchedulePane {
	public final String[] BUTTONNAME = { "list" , "add"  , "edit" };
	public final String[] BUTTONTEXT = { "リスト" , "追加"  , "変更" };
	public final String newLine = "\r\n";

	private SwitchingPane panel;
	public JPanel getPanel() {
		return panel.getSwitchingPane();
	}
	public SwitchingPane getSwitchingPaneClass() {
		return panel;
	}
	private ListPane listPane;//リスト表示
	public ListPane getlistpane() {
		return listPane;
	}
	private JButton deletButton;

	/* スケジュール */
	//選択日のスケジュール
	private List<Schedule> daySchedule;
	public void setDaySchedule(LocalDateTime start) {
		LocalDateTime end = start.plusDays(1);
		daySchedule = schedule.stream().filter(schedule -> schedule.getStartDate().isBefore(end) && (schedule.getEndDate().isAfter(start) || schedule.getEndDate().isEqual(start))).collect(Collectors.toList());
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
	private void createSchedule(LocalDateTime date) {
		setSchedule(nextSchedule, date.plusMonths(1));
		setSchedule(mowSchedule, date);
		setSchedule(backSchedule, date.minusMonths(1));
		setSchedule();
	}
	
	
	/* 日付ボタン時の処理 */
	public void dayButtonAction(LocalDateTime date) {
		//スケジュールリストを表示時の処理
		if(panel.getSwitchingPane().getComponent(0).isVisible() == true){
			setDaySchedule(date);
			listPane.updateSchedule(daySchedule);
		}
		
	}
	
	
	public SchedulePane(LocalDateTime date){
		createSchedule(date);
		panel = new SwitchingPane();
		for(int i =0; i < BUTTONNAME.length;i++) {
			panel.addCreatePane(BUTTONNAME[i], BUTTONTEXT[i]);
			panel.getPanels(i).setPreferredSize(new Dimension(470, 220));
			panel.getPanels(i).setLayout(new BorderLayout());
		}
		
		listPane = new ListPane();
		ArrayList<JButton> listPaneOperationButtons = new ArrayList<>();
		deletButton = new JButton("削除");
		listPaneOperationButtons.add(panel.getButtons(0));
		listPaneOperationButtons.add(panel.getButtons(1));
		listPaneOperationButtons.add(deletButton);
		listPaneOperationButtons.add(panel.getButtons(2));
		panel.addPanels(0, listPane.createPanel(listPaneOperationButtons));
	}
}
