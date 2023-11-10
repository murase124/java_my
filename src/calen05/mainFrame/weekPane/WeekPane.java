package calen05.mainFrame.weekPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeekPane extends JPanel {
	public final String WEEKTEXT[] = new String[] {"日","月","火","水","木","金","土"};	

	//曜日パネル作成
		public WeekPane() {
			setLayout(new GridLayout(1, 7, 2, 2));
			for(int i=0;i < WEEKTEXT.length;i++) {
				JLabel label = new JLabel(WEEKTEXT[i]);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
				if(i == 0) label.setForeground(Color.RED);//日曜日
				if(i == 6) label.setForeground(Color.BLUE);//土曜日
				add(label);
			}
		}
}
