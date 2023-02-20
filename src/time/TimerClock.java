package time;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myButtons.MyButtonJPanel;
import myButtons.myButton00.Layout00;
import opaqueChange.OpaqueChange;
import time.TimeLabel.TimeLabel;
import verticalTextLabal.VerticalText;


/*
 *  setCreateEmptyBorderAppearance arrange use
 *	setCreateEmptyBorderは見た目を整えるために使用
 */


public class TimerClock extends JPanel {
	
	private TimeLabel hour;//時
	private TimeLabel minutes;//分
	private TimeLabel seconds;//秒
	private TimeLabel mm_Seconds;//秒	
	private Count_MouseListener count = null;
	private Boolean countProcessFlg = false;
	
    final public static String cardPanelName_Timer = "Timer";

	//フィールド値関係のメソッド
    //ミリ秒初期化
	public void resetMmSeconds() {
		mm_Seconds.setText(0);
	}
	//初期化　時間ラベル
	public void resetAll() {
		hour.setText(0);
		minutes.setText(0);
		seconds.setText(0);
		mm_Seconds.setText(0);
	}
	//カウント処理　スタート
	public void countStart() {
		count.start();
	}
	//カウント処理　ストップ
	public void countStop() {
		count.stop();
	}
	//カウント処理中判定フラグ　ゲット
	public Boolean getCountProcessFlg() {
		return countProcessFlg;
	}
	//カウント処理中判定フラグ　セット
	public void setCountProcessFlg(Boolean countProcessFlg) {
		this.countProcessFlg = countProcessFlg;
	}
	//フィールド値関係のメソッド　ここまで

	
	TimerClock(JPanel cardPanel){
		 hour = new TimeLabel(99);//maxvalue 99 (99時)
		 minutes = new TimeLabel(59);//maxvalue 59 (59分)
		 seconds = new TimeLabel(59);//maxvalue 59 (59秒)
		 mm_Seconds = new TimeLabel("000",999);//maxvalue 999 (999ミリ秒)
		 
		//親panel
		JPanel p = new JPanel();
		
		//タイマー 値　値の設定
		JPanel pdispre = new JPanel();
		pdispre.setLayout(new BoxLayout(pdispre, BoxLayout.Y_AXIS));
		
		pdispre.add(UP_JPanel()); //タイマープラスボタン配置
		pdispre.add(timer_JPanel()); //タイマー数値配置
		pdispre.add(DOWN_JPanel()); //タイマーマイナスボタン配置
		
		//タイマー　動かす　値リセット
		JPanel countProcessPanel = new JPanel();
		countProcessPanel.setLayout(new BoxLayout(countProcessPanel, BoxLayout.X_AXIS));
		countProcessPanel.add(Box.createRigidArea(new Dimension(5,countProcessPanel.getMaximumSize().height)));
		countProcessPanel.add(start_stop_JPanel());//タイマー スタート　ストップ
		countProcessPanel.add(Box.createRigidArea(new Dimension(3,countProcessPanel.getMaximumSize().height)));
		countProcessPanel.add(reset_JPanel());//タイマー リセット
		
		
		p.add(BorderLayout.CENTER, pdispre);
		p.add(BorderLayout.WEST, countProcessPanel);
		
		String[] names = new String[] {"Bottom"};
		new OpaqueChange(p, names);
		
		cardPanel.add(p, cardPanelName_Timer);
	}
	
	//スタートストップボタン
	public MyButtonJPanel start_stop_JPanel() {
		MyButtonJPanel button_panel = new MyButtonJPanel();		
		new Layout00(button_panel);
		button_panel.setText(new VerticalText("スタート"));
		/*
		 * start_stop_Panelの見た目を整える
		 * Appearance　Border
		 */
		button_panel.setCreateEmptyBorder(3, 1, 4, 3);
		button_panel.setBackground(new Color(160,160,160));
		button_panel.setName("Bottom");
		button_panel.setOpaque(true);

		this.count = new Count_MouseListener(button_panel);
		button_panel.addMouseListener(this.count);
		
		return button_panel;
	}
	//リセットボタン
	public MyButtonJPanel reset_JPanel() {
		MyButtonJPanel button_panel = new MyButtonJPanel();
		new Layout00(button_panel);
		button_panel.setText(new VerticalText("リセット"));
		/*
		 * button_panelの見た目を整える
		 * Appearance　Border
		 */
		button_panel.setCreateEmptyBorder(1, 4, 2, 4);
		button_panel.setBackground(new Color(160,160,160));
		button_panel.setName("Bottom");
		button_panel.setOpaque(true);
		
		button_panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){  
		    	resetAll();
		    	return;
		    }
		});
		
		return button_panel;
	}
	
	//時間ラベルパネル
	public JPanel timer_JPanel() {
		//時
		JPanel pH = new JPanel();
		pH.setLayout(new  BoxLayout(pH, BoxLayout.X_AXIS));
		hour.setFont(new Font("",Font.PLAIN ,40));
		pH.add(hour);
		
		//分
		JPanel pm = new JPanel();
		pm.setLayout(new  BoxLayout(pm, BoxLayout.X_AXIS));
		minutes.setFont(new Font("",Font.PLAIN ,40));
		pm.add(minutes);
		
		//秒
		JPanel ps = new JPanel();
		ps.setLayout(new  BoxLayout(ps, BoxLayout.X_AXIS));
		seconds.setFont(new Font("",Font.PLAIN ,40));
		ps.add(seconds);
		
		//ミリ秒
		JPanel pmm = new JPanel();
		pmm.setLayout(new BoxLayout(pmm, BoxLayout.X_AXIS));
		mm_Seconds.setFont(new Font("",Font.PLAIN ,10));
		mm_Seconds.setDigit(3);
		pmm.add(mm_Seconds);
		pmm.setBorder(BorderFactory.createEmptyBorder(20, 1, 0, 0));
		
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
		pd1.add(delimiter1);
		//区切り２
		JPanel pd2 = new JPanel();
		pd2.setLayout(new  BoxLayout(pd2, BoxLayout.X_AXIS));
		pd2.setBorder(BorderFactory.createEmptyBorder(0, 5, 7, 5));//ラベル
		JLabel delimiter2 = new JLabel(":");
		delimiter2.setFont(new Font("",Font.PLAIN ,40));
		pd2.add(delimiter2);
		
		ptime.add(pH);
		ptime.add(pd1);
		ptime.add(pm);
		ptime.add(pd2);
		ptime.add(ps);
		ptime.add(pmm);
		return ptime;
	}
	
	//時間ラベル　増やすボタン
	public JPanel UP_JPanel() {
		//時１プラスボタン
		JPanel phup = new JPanel();
		phup.setLayout(new  BoxLayout(phup, BoxLayout.X_AXIS));
		phup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		JLabel hour_up = new JLabel();
		hour_up = new JLabel("上");
		hour_up.setFont(new Font("",Font.PLAIN ,15));
		hour_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		hour_up.addMouseListener(new up_down_MouseListener(true, hour)); 
		phup.add(hour_up);
		
		//分１プラスボタン
		JPanel pmup = new JPanel();
		pmup.setLayout(new  BoxLayout(pmup, BoxLayout.X_AXIS));
		pmup.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));//横幅
		JLabel minutes_up = new JLabel();
		minutes_up = new JLabel("上");
		minutes_up.setFont(new Font("",Font.PLAIN ,15));
		minutes_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		minutes_up.addMouseListener(new up_down_MouseListener(true, minutes)); 
		pmup.add(minutes_up);
		
		//秒１プラスボタン
		JPanel psup = new JPanel();
		psup.setLayout(new  BoxLayout(psup, BoxLayout.X_AXIS));
		psup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		JLabel seconds_up = new JLabel();
		seconds_up = new JLabel("上");
		seconds_up.setFont(new Font("",Font.PLAIN ,15));
		seconds_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		seconds_up.addMouseListener(new up_down_MouseListener(true, seconds)); 
		psup.add(seconds_up);
		
		//アップパネル
		JPanel pup = new JPanel();
		pup.setLayout(new  BoxLayout(pup, BoxLayout.X_AXIS));
		pup.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));//ラベル
		pup.add(phup);
		pup.add(pmup);
		pup.add(psup);
		
		return pup;
	}
	
	//時間ラベル　減らすボタン
	public JPanel DOWN_JPanel() {
		//時１マイナスボタン
		JPanel phdown = new JPanel();
		phdown.setLayout(new  BoxLayout(phdown, BoxLayout.X_AXIS));
		phdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		JLabel hour_down = new JLabel();
		hour_down = new JLabel("下");
		hour_down.setFont(new Font("",Font.PLAIN ,15));
		hour_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		hour_down.addMouseListener(new up_down_MouseListener(false, hour)); 
		phdown.add(hour_down);
		
		//分１マイナスボタン
		JPanel pmdown = new JPanel();
		pmdown.setLayout(new  BoxLayout(pmdown, BoxLayout.X_AXIS));
		pmdown.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));//横幅
		JLabel minutes_down = new JLabel();
		minutes_down = new JLabel("下");
		minutes_down.setFont(new Font("",Font.PLAIN ,15));
		minutes_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		minutes_down.addMouseListener(new up_down_MouseListener(false, minutes)); 
		pmdown.add(minutes_down);
		
		//秒１マイナスボタン
		JPanel psdown = new JPanel();
		psdown.setLayout(new  BoxLayout(psdown, BoxLayout.X_AXIS));
		psdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		JLabel seconds_down = new JLabel();
		seconds_down= new JLabel("下");
		seconds_down.setFont(new Font("",Font.PLAIN ,15));
		seconds_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		seconds_down.addMouseListener(new up_down_MouseListener(false, seconds)); 
		psdown.add(seconds_down);
		
		//ダウンパネル
		JPanel pdown = new JPanel();
		pdown.setLayout(new  BoxLayout(pdown, BoxLayout.X_AXIS));
		pdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));//ラベル
		pdown.add(phdown);
		pdown.add(pmdown);
		pdown.add(psdown);
		
		return pdown;
	}

	//時間ラベル　変更処理
	class up_down_MouseListener implements MouseListener{
		private boolean up_downFlg;
		private TimeLabel jlabel;
		private Timer up_down_timer;

		up_down_MouseListener(boolean flg, TimeLabel jlabel){
			this.up_downFlg = flg;
			this.jlabel = jlabel;
		}
		
		@Override
	    public void mousePressed(MouseEvent e){  
	    	up_down_timer = new Timer();
			if(!countProcessFlg) {
				TimerTask timertask = new TimerTask() {
					public void run() {
						if(up_downFlg) {
							jlabel.up();
						}else {
							jlabel.down();
						}
				    	resetMmSeconds();
					}
				};
				/*
				 * 800ミリ秒後から50ミリ秒ごとにUP又はDOWNを実行
				 * 800ミリ秒　, 50ミリ秒　　は適当な間隔
				 */
				up_down_timer.schedule(timertask,800, 50);
			}
	    	return;
	    }  
	   
		@Override
		public void mouseReleased(MouseEvent e){
			if(!countProcessFlg) {
				up_down_timer.cancel();
				if(up_downFlg) {
					jlabel.up();
				}else {
					jlabel.down();
				}
				resetMmSeconds();
			}
		}
		
		@Override public void mouseClicked(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
	}
	
	
	//タイマー　カウント処理
	class Count_MouseListener implements MouseListener{
		Timer timer;
		Timestamp endTimestamp;
		MyButtonJPanel start_stop_Panel;
		Count_MouseListener(MyButtonJPanel start_stop_Panel){
			this.start_stop_Panel = start_stop_Panel;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!getCountProcessFlg()) {
				start();
			}else {
				stop();
			}
		}
		
		//カウント処理　スタート
		public void start() {
			setCountProcessFlg(true);
			((VerticalText) start_stop_Panel.get_text()).setText("ストップ");
			start_stop_Panel.setCreateEmptyBorder(1, 3, 2, 5);

			int time_value = ((Integer.valueOf(hour.getText())*60 + Integer.valueOf(minutes.getText()))*60 + Integer.valueOf(seconds.getText()))*1000+Integer.valueOf(mm_Seconds.getText());
			endTimestamp = new Timestamp(System.currentTimeMillis()+time_value);
	
			timer = new Timer();
			TimerTask timertask = new TimerTask() {
				@Override
				public void run() {
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					if(endTimestamp.compareTo(timestamp) < 0) {
						stop();
						return;
					}
					timerProcess(timestamp);
				}
			};
			timer.schedule(timertask,0, 6);
		 }
		//カウント処理　ストップ
		public void stop() {
			timer.cancel();
			setCountProcessFlg(false);
			((VerticalText) start_stop_Panel.get_text()).setText("スタート");
			start_stop_Panel.setCreateEmptyBorder(3, 1, 4, 3);

			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			if(endTimestamp.compareTo(timestamp2) < 0) resetAll();
		}
		
		//時間ラベル更新処理
		private void timerProcess(Timestamp timestamp2) {
			Timestamp timestamp = new Timestamp(endTimestamp.getTime() - timestamp2.getTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String str = sdf.format(timestamp);
			hour.setText(str);
			sdf.applyLocalizedPattern("mm");
			str = sdf.format(timestamp);
			minutes.setText(str);
			sdf.applyLocalizedPattern("ss");
			str = sdf.format(timestamp);
			seconds.setText(str);
			sdf.applyLocalizedPattern("SSS");
			str = sdf.format(timestamp);
			mm_Seconds.setText(str);
		}
		
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
		
	}
}
