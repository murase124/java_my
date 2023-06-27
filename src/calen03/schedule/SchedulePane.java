package calen03.schedule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import calen03.DB;


public class SchedulePane extends SwitchingPane{
	
	public final String[] SWITCHINGBUTTONNAME = { "list" , "add"  , "detail" , "edit" };
	public final String[] SWITCHINGBUTTONTEXT = { "リスト" , "追加"  , "詳細" , "編集" };
	public final String[] PROCESSBUTTONNAME = { "new" , "delet" , "save" };
	public final String[] PROCESSBUTTONTEXT = { "登録" , "削除" , "保存" };
	public final String newLine = "\r\n";
	
	private ListPane listPane;//リスト表示
	private EditPane editPane;//リスト表示
	private DetailPane detailPane;//リスト表示
	private AddPane addPane;
	private Map<String, JButton> buttons = new HashMap<>();

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
		if(getComponent(0).isVisible() == true){
			setDaySchedule(date);
			listPane.updateSchedule(daySchedule);
		}else if(getComponent(1).isVisible() == true){
		
		}
	}
	
	/* パネル切り替えボタン作成用 */
	public JButton switchingButton(String text, ActionListener actionListener) {
		JButton jbutton = new JButton(text);
		if(actionListener != null) jbutton.addActionListener(actionListener);
		return jbutton;
	}
	
	public SchedulePane(LocalDateTime date){
		createSchedule(date);
		for(int i =0; i < SWITCHINGBUTTONNAME.length;i++) {
			addCreatePane(SWITCHINGBUTTONNAME[i], SWITCHINGBUTTONTEXT[i]);
			getPanels(i).setPreferredSize(new Dimension(470, 220));
			getPanels(i).setLayout(new BorderLayout());
		}
		
		//リスト
		buttons.put(PROCESSBUTTONNAME[1], switchingButton(PROCESSBUTTONTEXT[1], null));
		ArrayList<JButton> listPaneSwitchingButtons = new ArrayList<>();
		listPaneSwitchingButtons.add(switchingButton(getActionButtonTexts(0), getActionListener(0)));
		listPaneSwitchingButtons.add(switchingButton(getActionButtonTexts(1), getActionListener(1)));
		listPaneSwitchingButtons.add(buttons.get(PROCESSBUTTONNAME[1]));
		listPaneSwitchingButtons.add(switchingButton(getActionButtonTexts(2), getActionListener(2)));
		listPane = new ListPane(listPaneSwitchingButtons);
		addPanels(0, listPane);
		
		//追加
		buttons.put(PROCESSBUTTONNAME[0], switchingButton(PROCESSBUTTONTEXT[0], null));
		ArrayList<JButton> addPaneOperationButtons = new ArrayList<>();
		addPaneOperationButtons.add(switchingButton(getActionButtonTexts(0),
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int let = 0;
					let = JOptionPane.showConfirmDialog(null, "保存しないでよろしいですか？", "警告", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
					if(let == JOptionPane.YES_OPTION) {
						getActionListener(0).run();
					}
				}
			}
		));
		listPaneSwitchingButtons.add(buttons.get(PROCESSBUTTONNAME[0]));
		addPane = new AddPane(addPaneOperationButtons);
		addPanels(1, addPane);
		
		//詳細
		buttons.put(PROCESSBUTTONNAME[1], switchingButton(PROCESSBUTTONTEXT[1], null));
		ArrayList<JButton> detailPaneOperationButtons = new ArrayList<>();
		detailPaneOperationButtons.add(switchingButton(getActionButtonTexts(0), getActionListener(0)));
		detailPaneOperationButtons.add(switchingButton(getActionButtonTexts(1), getActionListener(1)));
		listPaneSwitchingButtons.add(buttons.get(PROCESSBUTTONNAME[1]));
		detailPaneOperationButtons.add(switchingButton(getActionButtonTexts(3), getActionListener(3)));
		detailPane = new DetailPane(detailPaneOperationButtons);
		addPanels(2, detailPane);
		
		//編集
		buttons.put(PROCESSBUTTONNAME[2], switchingButton(PROCESSBUTTONTEXT[2], null));
		ArrayList<JButton> editPaneOperationButtons = new ArrayList<>();
		editPaneOperationButtons.add(switchingButton(getActionButtonTexts(0), getActionListener(0)));
		editPaneOperationButtons.add(switchingButton(getActionButtonTexts(1), getActionListener(1)));
		listPaneSwitchingButtons.add(buttons.get(PROCESSBUTTONNAME[2]));
		editPane = new EditPane(editPaneOperationButtons);
		addPanels(3, editPane);
	}
	
	
}
