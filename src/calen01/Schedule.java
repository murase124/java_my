package calen01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Schedule {

	// keyの値　１個目○○年○○月　２個目○○日　valuesの値　予定のID
		private Map<Integer, Map<Integer, ArrayList<Integer>>> scheduleID = new HashMap<>();
		// keyの値　１個目○○年○○月　２個目○○日　3個目ID　valuesの値　予定のタイトル
		private Map<Integer, Map<Integer, Map<Integer, String>>> scheduleTitle = new HashMap<>();
		// keyの値　１個目scheduleID　２個目何行目　valuesの値　予定の内容
		private Map<Integer, Map<Integer, String>> scheduleText = new HashMap<>();
		public void clearScheduleTexts(int time) {
			scheduleText.get(time).clear();
		}
		public int scheduleTextsCount(int time, int day) {
			return scheduleID.get(time).get(day).size();
		}
		public void setScheduleText(int ID, Map<Integer, String> Texts) {
			scheduleText.get(ID).putAll(Texts);
		}

}
