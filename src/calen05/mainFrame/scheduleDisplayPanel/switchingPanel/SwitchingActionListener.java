package calen05.mainFrame.scheduleDisplayPanel.switchingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SwitchingActionListener implements ActionListener{
	private List<SwitchingListener> listeners = new ArrayList<SwitchingListener>();
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		listeners.forEach(listener->listener.changePerformed());
	}
	public void add(SwitchingListener listener) {
		listeners.add(listener);
	}
}