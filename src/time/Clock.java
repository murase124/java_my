package time;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Clock extends JFrame implements ActionListener{

	JLabel Timestamp_yMd;//名前
	JLabel Timestamp_Hms;//名前
	
	
	Clock(JPanel cardPanel){
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(new Color(150,150,150));
		
		//年月日
		JPanel p1 = new JPanel();
		p1.setLayout(new  BoxLayout(p1, BoxLayout.X_AXIS));
		p1.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));//ラベル
		p1.setOpaque(false);

		Timestamp_yMd = new JLabel(Timestamp_yMd());
		Timestamp_yMd.setFont(new Font("",Font.PLAIN ,30));
		p1.add(Timestamp_yMd);
		p.add(p1);
		
		//時間
		JPanel p2 = new JPanel();
		p2.setLayout(new  BoxLayout(p2, BoxLayout.X_AXIS));
		p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//ラベル
		p2.setOpaque(false);

		Timestamp_Hms = new JLabel(Timestamp_Hms());
		Timestamp_Hms.setFont(new Font("",Font.PLAIN ,30));
		p2.add(Timestamp_Hms);
		p.add(p2);
		
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
			public void run() {
				Timestamp_Hms.setText(Timestamp_Hms());
			}
		};
		timer.scheduleAtFixedRate(task,0, 90);
		
		cardPanel.add(p, "Clock");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public String Timestamp_yMd() {
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	        String str = sdf.format(timestamp);
	        return str;
	}
	
	public String Timestamp_Hms() {
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        SimpleDateFormat sdf = new SimpleDateFormat("HH時mm分ss秒");
	        String str = sdf.format(timestamp);
	        return str;
	}
	

}
