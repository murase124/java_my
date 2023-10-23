package calen04.schedulePanel;

import java.time.LocalDateTime;
import java.util.List;

import calen04.ScheduleDTO.Schedule;
import calen04.ScheduleDTO.Schedules;
import calen04.schedulePanel.Panels.List.ListPanel;


public class ScheduleProcess {
		
	private SwitchingPanel switchingPanel;//概要表示
	

	
	/* 日付ボタン時の処理 */
	public void dayButtonAction(LocalDateTime date, Schedules schedules) {
		
	}
	public void dayButtonAction(ListPanel listPanel, LocalDateTime date, Schedules schedules) {
		//スケジュールリストを表示時の処理
		if(switchingPanel.getComponent(0).isVisible() == true){
			List<Schedule> daySchedule = schedules.getDaySchedule(date);
			listPanel.updateSchedule(daySchedule);
		}else if(switchingPanel.getComponent(1).isVisible() == true){
		
		}
	}
	

	public ScheduleProcess(SwitchingPanel switchingPanel){
		this.switchingPanel = switchingPanel;
	}
}
