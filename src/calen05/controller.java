package calen05;

import java.util.List;

import calen05.ScheduleDTO.Schedule;
import calen05.schedulePanel.SwitchingPanel;
import calen05.schedulePanel.Panels.List.ListPanel;

public class controller {
	static public void dayButtonAction(SwitchingPanel switchingPanel, ListPanel listPanel, List<Schedule> daySchedule ) {
		//スケジュールリストを表示時の処理
		if(switchingPanel.getComponent(0).isVisible() == true){
			listPanel.updateSchedule(daySchedule);
		}else if(switchingPanel.getComponent(1).isVisible() == true){
		
		}
	}
}
