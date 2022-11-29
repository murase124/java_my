package time;

	import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vertical_text_labal.vertical_text;

public class Timer_Clock extends JPanel {
	
	JLabel hour;//時
	JLabel minutes;//分
	JLabel seconds;//秒
	JLabel mm_Seconds;//秒	
	boolean start_flg = false;
	JPanel start_stop_Panel;
	CardLayout layout = new CardLayout();
	
	Timer_Clock(JPanel cardPanel){
		//親panel
		JPanel p = new JPanel();
		p.setBackground(new Color(150,150,150));
		
		//タイマー 値　値の設定
		JPanel pdispre = new JPanel();
		pdispre.setLayout(new BoxLayout(pdispre, BoxLayout.Y_AXIS));
		
		pdispre.add(UP_JPanel()); //タイマープラスボタン配置
		pdispre.add(timer_JPanel()); //タイマー数値配置
		pdispre.add(DOWN_JPanel()); //タイマーマイナスボタン配置
		
		//タイマー　動かす　値リセット
		JPanel motion = new JPanel();
		motion.setLayout(new BoxLayout(motion, BoxLayout.X_AXIS));
		
		motion.add(start_stop_JPanel());//タイマー スタート　ストップ
		motion.add(reset_JPanel());//タイマー リセット
		
		
		p.add(BorderLayout.CENTER, pdispre);
		p.add(BorderLayout.WEST, motion);
		
		//new Opaque_change(p);
		p.setOpaque(true);
		
		cardPanel.add(p, "Timer");
		
	}
	
	public JPanel start_stop_JPanel() {
		
		vertical_text start = new vertical_text("スタート");
		vertical_text stop = new vertical_text("ストップ");
		start_stop_Panel = new JPanel();
		start_stop_Panel.setLayout(layout);
		start_stop_Panel.add(start , "start");
		start_stop_Panel.add(stop , "stop");
		start_stop_Panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		start.addMouseListener(new start());
		
		return start_stop_Panel;
	}
	
	public JPanel reset_JPanel() {
		
		vertical_text reset = new vertical_text("リセット");
		reset.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 7));

		reset.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e)  
		    {  
		    	reset_all();
		    	return;
		    }
		});
		return reset;
	}
	
	
	public JPanel UP_JPanel() {
		//アップパネル
		JPanel pup = new JPanel();
		pup.setLayout(new  BoxLayout(pup, BoxLayout.X_AXIS));
		pup.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));//ラベル
		
		//時１プラスボタン
		JPanel phup = new JPanel();
		phup.setLayout(new  BoxLayout(phup, BoxLayout.X_AXIS));
		phup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		JLabel hour_up = new JLabel();
		hour_up = new JLabel("上");
		hour_up.setFont(new Font("",Font.PLAIN ,15));
		hour_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		hour_up.setOpaque(true);
		hour_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	if(!get_start_flg()) {
			    	int num = (Integer.parseInt(hour.getText()) + 1 )% 100;
			    	hour.setText(num > 9 ? "" + num : "0" + num);
			    	reset_mm_Seconds();
		    	}
		    	return;
		    }  
		}); 
		phup.add(hour_up);

		
		//分１プラスボタン
		JPanel pmup = new JPanel();
		pmup.setLayout(new  BoxLayout(pmup, BoxLayout.X_AXIS));
		pmup.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));//横幅

		JLabel minutes_up = new JLabel();
		minutes_up = new JLabel("上");
		minutes_up.setFont(new Font("",Font.PLAIN ,15));
		minutes_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		minutes_up.setOpaque(true);
		minutes_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	if(!get_start_flg()) {
			    	int num = (Integer.parseInt(minutes.getText()) + 1 )% 60;
			    	minutes.setText(num > 9 ? "" + num : "0" + num);
			    	reset_mm_Seconds();
		    	}
		    	return;
		    }  
		}); 
		pmup.add(minutes_up);

		
		//秒１プラスボタン
		JPanel psup = new JPanel();
		psup.setLayout(new  BoxLayout(psup, BoxLayout.X_AXIS));
		psup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

		JLabel seconds_up = new JLabel();
		seconds_up = new JLabel("上");
		seconds_up.setFont(new Font("",Font.PLAIN ,15));
		seconds_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		seconds_up.setOpaque(true);
		seconds_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	if(!get_start_flg()) {
			    	int num = (Integer.parseInt(seconds.getText()) + 1 )% 60;
			    	seconds.setText(num > 9 ? "" + num : "0" + num);
			    	reset_mm_Seconds();
		    	}
		    	return;
		    }  
		}); 
		psup.add(seconds_up);

		
		pup.add(phup);
		pup.add(pmup);
		pup.add(psup);
		return pup;
				
	}
	
	
	public JPanel timer_JPanel() {
		//タイムパネル
		JPanel ptime = new JPanel();
		ptime.setLayout(new  BoxLayout(ptime, BoxLayout.X_AXIS));
		ptime.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 0));//ラベル

		//区切り
		JPanel pd1 = new JPanel();
		pd1.setLayout(new  BoxLayout(pd1, BoxLayout.X_AXIS));
		pd1.setBorder(BorderFactory.createEmptyBorder(0, 5, 7, 5));//ラベル
		
		JLabel delimiter1 = new JLabel(":");
		delimiter1.setFont(new Font("",Font.PLAIN ,40));
		delimiter1.setOpaque(true);
		pd1.add(delimiter1);
		
		//区切り
		JPanel pd2 = new JPanel();
		pd2.setLayout(new  BoxLayout(pd2, BoxLayout.X_AXIS));
		pd2.setBorder(BorderFactory.createEmptyBorder(0, 5, 7, 5));//ラベル
		
		JLabel delimiter2 = new JLabel(":");
		delimiter2.setFont(new Font("",Font.PLAIN ,40));
		delimiter2.setOpaque(true);
		pd2.add(delimiter2);

		
		//時
		JPanel pH = new JPanel();
		pH.setLayout(new  BoxLayout(pH, BoxLayout.X_AXIS));

		hour = new JLabel("00");
		hour.setFont(new Font("",Font.PLAIN ,40));
		hour.setOpaque(true);
		pH.add(hour);
		
		//分
		JPanel pm = new JPanel();
		pm.setLayout(new  BoxLayout(pm, BoxLayout.X_AXIS));

		minutes = new JLabel("00");
		minutes.setFont(new Font("",Font.PLAIN ,40));
		minutes.setOpaque(true);
		pm.add(minutes);
		
		//秒
		JPanel ps = new JPanel();
		ps.setLayout(new  BoxLayout(ps, BoxLayout.X_AXIS));

		seconds = new JLabel("00");
		seconds.setFont(new Font("",Font.PLAIN ,40));
		seconds.setOpaque(true);
		ps.add(seconds);
		
		JPanel pmm = new JPanel();
		pmm.setLayout(new BoxLayout(pmm, BoxLayout.X_AXIS));

		mm_Seconds = new JLabel("000");
		mm_Seconds.setFont(new Font("",Font.PLAIN ,10));
		mm_Seconds.setOpaque(true);
		pmm.add(mm_Seconds);
		pmm.setBorder(BorderFactory.createEmptyBorder(20, 1, 0, 0));
		
		ptime.add(pH);
		ptime.add(pd1);
		ptime.add(pm);
		ptime.add(pd2);
		ptime.add(ps);
		ptime.add(pmm);
		return ptime;
	}
	
	
	public JPanel DOWN_JPanel() {
		//ダウンパネル
		JPanel pdown = new JPanel();
		pdown.setLayout(new  BoxLayout(pdown, BoxLayout.X_AXIS));
		pdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));//ラベル
		
		//時１マイナスボタン
		JPanel phdown = new JPanel();
		phdown.setLayout(new  BoxLayout(phdown, BoxLayout.X_AXIS));
		phdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JLabel hour_down = new JLabel();
		hour_down = new JLabel("下");
		hour_down.setFont(new Font("",Font.PLAIN ,15));
		hour_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		hour_down.setOpaque(true);
		hour_down.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  if(!get_start_flg()) {
			    	int num = (Integer.parseInt(hour.getText())-1);
			    	num = num < 0 ? num + 100 : num;
			    	hour.setText(num > 9 ? "" + num : "0" + num);
			    	reset_mm_Seconds();
		    	}
		    	return;
		    }  
		}); 
		phdown.add(hour_down);
		
		//分１マイナスボタン
		JPanel pmdown = new JPanel();
		pmdown.setLayout(new  BoxLayout(pmdown, BoxLayout.X_AXIS));
		pmdown.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));//横幅

		JLabel minutes_down = new JLabel();
		minutes_down = new JLabel("下");
		minutes_down.setFont(new Font("",Font.PLAIN ,15));
		minutes_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		minutes_down.setOpaque(true);
		minutes_down.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	if(!get_start_flg()) {
			    	int num = (Integer.parseInt(minutes.getText()) - 1 );
			    	num = num < 0 ? num + 60 : num;
			    	minutes.setText(num > 9 ? "" + num : "0" + num);
			    	reset_mm_Seconds();
		    	}
		    	return;
		    }  
		}); 
		pmdown.add(minutes_down);
		
		//秒１マイナスボタン
		JPanel psdown = new JPanel();
		psdown.setLayout(new  BoxLayout(psdown, BoxLayout.X_AXIS));
		psdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

		JLabel seconds_down = new JLabel();
		seconds_down= new JLabel("下");
		seconds_down.setFont(new Font("",Font.PLAIN ,15));
		seconds_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		seconds_down.setOpaque(true);
		seconds_down.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	if(!get_start_flg()) {
			    	int num = (Integer.parseInt(seconds.getText()) - 1 );
			    	num = num < 0 ? num + 60 : num;
			    	seconds.setText(num > 9 ? "" + num : "0" + num);
			    	reset_mm_Seconds();
		    	}
		    	return;
		    }  
		}); 
		psdown.add(seconds_down);
		
		pdown.add(phdown);
		pdown.add(pmdown);
		pdown.add(psdown);
		return pdown;
				
	}
	
	public void set_start_stop_panel(String name) {
		layout.show(start_stop_Panel, name);
	}
	public void set_start_flg(boolean start_flg) {
		this.start_flg = start_flg;
	}
	public void set_hour(String text) {
		this.hour.setText(text);
	}
	public void set_minutes(String text) {
		this.minutes.setText(text);
	}
	public void set_seconds(String text) {
		 this.seconds.setText(text);
	}
	public void set_mm_Seconds(String text) {
		 this.mm_Seconds.setText(text);
	}
	public boolean get_start_flg() {
		return this.start_flg;
	}
	public String get_hour() {
		return this.hour.getText();
	}
	public String get_minutes() {
		return this.minutes.getText();
	}
	public String get_seconds() {
		return this.seconds.getText();
	}
	public String get_mm_Seconds() {
		return this.mm_Seconds.getText();
	}
	public void reset_mm_Seconds() {
		this.mm_Seconds.setText("000");
	}
	public void reset_all() {
		this.hour.setText("00");
		this.minutes.setText("00");
		this.seconds.setText("00");
		this.mm_Seconds.setText("000");
	}
	
	class stop implements MouseListener{

		start start;
		stop(start start){
			this.start = start;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			start.stop();
			set_start_stop_panel("start");
			set_start_flg(false);
			Timestamp target_Timestamp = new Timestamp(System.currentTimeMillis());

			if(start.get_target_Timestamp().compareTo(target_Timestamp) < 0) reset_all();
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	
	class start implements MouseListener{
		Timer timer;
		Timestamp target_Timestamp;
		int num=0;
		@Override
		public void mouseClicked(MouseEvent e) {
			if(get_start_flg()) {System.out.println("a"); return;}
			set_start_flg(true);
			set_start_stop_panel("stop");
			int time_value = (Integer.valueOf(get_hour())*60 + Integer.valueOf(get_minutes()))*60 + Integer.valueOf(get_seconds());
			time_value = time_value * 1000;
			
			target_Timestamp = new Timestamp(System.currentTimeMillis()+time_value);
			 SimpleDateFormat sdf = new SimpleDateFormat("HH時mm分ss秒SSS");
		      String str = sdf.format(target_Timestamp);
		      num =0;

			System.out.println(str +",b");
			timer = new Timer();
			TimerTask timertask = new TimerTask() {
				@Override
				public void run() {
					Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
					if(target_Timestamp.compareTo(timestamp2) < 0) {
						end();
						return;
					}
					timer_process(timestamp2,num++);
				}
			};
			timer.schedule(timertask,0, 6);
		}
		
		public void timer_process(Timestamp timestamp2, int num) {
			
			Timestamp timestamp = new Timestamp(target_Timestamp.getTime() - timestamp2.getTime()-32400000);
			SimpleDateFormat sdf = new SimpleDateFormat("HH時mm分ss秒SSS");
			sdf.setTimeZone(TimeZone.getTimeZone("JST"));
			String str = sdf.format(timestamp);

			sdf = new SimpleDateFormat("HH");
			str = sdf.format(timestamp);
		    set_hour(str);
		    sdf = new SimpleDateFormat("mm");
			str = sdf.format(timestamp);
		    set_minutes(str);
			sdf = new SimpleDateFormat("ss");
			str = sdf.format(timestamp);
			set_seconds(str);
		    sdf = new SimpleDateFormat("SSS");
			str = sdf.format(timestamp);
		    set_mm_Seconds(str);
		}
		
		public void end() {
			timer.cancel();
			set_start_stop_panel("start");
			set_start_flg(false);
			reset_all();
			target_Timestamp = new Timestamp(System.currentTimeMillis());
			 SimpleDateFormat sdf = new SimpleDateFormat("HH時mm分ss秒SSS");
		      String str = sdf.format(target_Timestamp);

			System.out.println(str +",c");
		}
		
		public void stop() {
			timer.cancel();
		}
		public Timestamp get_target_Timestamp() {
			return this.target_Timestamp;
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {	}
		
	}
	
	class Opaque_change {
		
		boolean Opaque_flg = false;
		
		Opaque_change(JPanel jpanel) {
			Opaques_panel(jpanel);
		}
		
		Opaque_change(JPanel jpanel , boolean Opaque_flg) {
			Opaques_panel(jpanel);
			this.Opaque_flg = Opaque_flg;
		}
		
		//パネル
		public void Opaques_panel(JPanel jpanel) {
			if(jpanel.getComponentCount() != 0) {

				for(int i =0; i < jpanel.getComponentCount();i++) {
					if(jpanel.getComponent(i).getClass().getName().equals("javax.swing.JPanel")) {
						Opaques_panel((JPanel) jpanel.getComponent(i));
					}else {
						Opaques_no_panel((JComponent) jpanel.getComponent(i));
					}
				}
				jpanel.setOpaque(Opaque_flg);
			}
			return;
		}
		
		//パネル以外　
		public void Opaques_no_panel(JComponent jpanel ) {
			jpanel.setOpaque(Opaque_flg);
			return;
		}
	}
}
	


