package time;

	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer_Clock extends JFrame implements ActionListener{
	
	JLabel hour;//時
	JLabel hour_up;//時
	JLabel hour_down;//時
	JLabel minutes;//分
	JLabel minutes_up;//分
	JLabel minutes_down;//分
	JLabel seconds;//秒
	JLabel seconds_up;//秒
	JLabel seconds_down;//秒
	float time;
	
	
	Timer_Clock(JPanel cardPanel){
	// TODO 自動生成されたコンストラクター・スタブ
		
		JPanel p = new JPanel();
		p.setBackground(new Color(150,150,150));
		
		
		JPanel pdispre = new JPanel();
		pdispre.setLayout(new BoxLayout(pdispre, BoxLayout.Y_AXIS));
		
		JPanel pb = new JPanel();
		JLabel start = new JLabel("<html>ス<br>タ<br>｜<br>ト</html>");
		pb.add(start);
		
		
		pdispre.add(UP_JPanel()); //タイマープラスボタン配置
		pdispre.add(timer_JPanel()); //タイマー数値配置
		pdispre.add(DOWN_JPanel()); //タイマーマイナスボタン配置
		
		
		
		p.add(BorderLayout.CENTER,pdispre);
		p.add(BorderLayout.WEST,pb);
		cardPanel.add(p, "Timer");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		/*
		Timer timer = new Timer(false);
		TimerTask task = new TimerTask() {
			public void run() {
				minutes.setText(Timestamp_Hms());
			}
		};
		timer.scheduleAtFixedRate(task,0, 10);
		*/
	}
	
	public JPanel UP_JPanel() {
		//アップパネル
		JPanel pup = new JPanel();
		pup.setLayout(new  BoxLayout(pup, BoxLayout.X_AXIS));
		pup.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));//ラベル
		pup.setBackground(Color.ORANGE);
		
		//時１プラスボタン
		JPanel phup = new JPanel();
		phup.setLayout(new  BoxLayout(phup, BoxLayout.X_AXIS));
		phup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		phup.setBackground(Color.ORANGE);

		hour_up = new JLabel("上");
		hour_up.setFont(new Font("",Font.PLAIN ,15));
		hour_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		hour_up.setBackground(Color.BLACK);
		hour_up.setOpaque(true);
		hour_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	int num = (Integer.parseInt(hour.getText()) + 1 )% 100;
		    	hour.setText(num > 9 ? "" + num : "0" + num);
		    	System.out.println("h");

		    	return;
		    }  
		}); 
		phup.add(hour_up);

		
		//分１プラスボタン
		JPanel pmup = new JPanel();
		pmup.setLayout(new  BoxLayout(pmup, BoxLayout.X_AXIS));
		pmup.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));//横幅
		pmup.setBackground(Color.GRAY);

		minutes_up = new JLabel("上");
		minutes_up.setFont(new Font("",Font.PLAIN ,15));
		minutes_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		minutes_up.setBackground(Color.BLACK);
		minutes_up.setOpaque(true);
		minutes_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	int num = (Integer.parseInt(minutes.getText()) + 1 )% 60;
		    	minutes.setText(num > 9 ? "" + num : "0" + num);
		    	System.out.println("m");
		    	return;
		    }  
		}); 
		pmup.add(minutes_up);

		
		//秒１プラスボタン
		JPanel psup = new JPanel();
		psup.setLayout(new  BoxLayout(psup, BoxLayout.X_AXIS));
		psup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		psup.setBackground(Color.GRAY);

		seconds_up = new JLabel("上");
		seconds_up.setFont(new Font("",Font.PLAIN ,15));
		seconds_up.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		seconds_up.setBackground(Color.BLACK);
		seconds_up.setOpaque(true);
		seconds_up.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	int num = (Integer.parseInt(seconds.getText()) + 1 )% 60;
		    	seconds.setText(num > 9 ? "" + num : "0" + num);
		    	System.out.println("s");
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
		ptime.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));//ラベル

		ptime.setBackground(Color.GRAY);
		//区切り
		JPanel pd1 = new JPanel();
		pd1.setLayout(new  BoxLayout(pd1, BoxLayout.X_AXIS));
		pd1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));//ラベル
		pd1.setBackground(Color.GRAY);
		
		JLabel delimiter1 = new JLabel(":");
		delimiter1.setFont(new Font("",Font.PLAIN ,40));
		delimiter1.setBackground(Color.BLACK);
		delimiter1.setOpaque(true);
		pd1.add(delimiter1);
		
		//区切り
		JPanel pd2 = new JPanel();
		pd2.setLayout(new  BoxLayout(pd2, BoxLayout.X_AXIS));
		pd2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));//ラベル
		pd2.setBackground(Color.GRAY);
		
		JLabel delimiter2 = new JLabel(":");
		delimiter2.setFont(new Font("",Font.PLAIN ,40));
		delimiter2.setBackground(Color.BLACK);
		delimiter2.setOpaque(true);
		pd2.add(delimiter2);

		
		//時
		JPanel pH = new JPanel();
		pH.setLayout(new  BoxLayout(pH, BoxLayout.X_AXIS));
		//pH.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));//ラベル
		pH.setBackground(Color.GRAY);

		hour = new JLabel("00");
		hour.setFont(new Font("",Font.PLAIN ,40));
		hour.setBackground(Color.RED);
		hour.setOpaque(true);
		pH.add(hour);
		
		//秒
		JPanel pm = new JPanel();
		pm.setLayout(new  BoxLayout(pm, BoxLayout.X_AXIS));
		//ps.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));//ラベル
		pm.setBackground(Color.GRAY);

		minutes = new JLabel("00");
		minutes.setFont(new Font("",Font.PLAIN ,40));
		minutes.setBackground(Color.GREEN);
		minutes.setOpaque(true);
		pm.add(minutes);
		
		//分
		JPanel ps = new JPanel();
		ps.setLayout(new  BoxLayout(ps, BoxLayout.X_AXIS));
		//pm.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));//ラベル
		ps.setBackground(Color.GRAY);

		seconds = new JLabel("00");
		seconds.setFont(new Font("",Font.PLAIN ,40));
		seconds.setBackground(Color.GREEN);
		seconds.setOpaque(true);
		ps.add(seconds);
		
		
		
		ptime.add(pH);
		ptime.add(pd1);
		ptime.add(pm);
		ptime.add(pd2);
		ptime.add(ps);
		return ptime;
	}
	
	
	public JPanel DOWN_JPanel() {
		//アップパネル
		JPanel pdown = new JPanel();
		pdown.setLayout(new  BoxLayout(pdown, BoxLayout.X_AXIS));
		pdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));//ラベル
		pdown.setBackground(Color.ORANGE);
		
		//時１プラスボタン
		JPanel phdown = new JPanel();
		phdown.setLayout(new  BoxLayout(phdown, BoxLayout.X_AXIS));
		phdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		phdown.setBackground(Color.ORANGE);

		hour_down = new JLabel("下");
		hour_down.setFont(new Font("",Font.PLAIN ,15));
		hour_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		hour_down.setBackground(Color.BLACK);
		hour_down.setOpaque(true);
		hour_down.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	int num = (Integer.parseInt(hour.getText())-1);
		    	num = num < 0 ? num + 100 : num;
		    	hour.setText(num > 9 ? "" + num : "0" + num);
		    	System.out.println("h");

		    	return;
		    }  
		}); 
		phdown.add(hour_down);

		
		//分１プラスボタン
		JPanel pmdown = new JPanel();
		pmdown.setLayout(new  BoxLayout(pmdown, BoxLayout.X_AXIS));
		pmdown.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));//横幅
		pmdown.setBackground(Color.GRAY);

		minutes_down = new JLabel("下");
		minutes_down.setFont(new Font("",Font.PLAIN ,15));
		minutes_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		minutes_down.setBackground(Color.BLACK);
		minutes_down.setOpaque(true);
		minutes_down.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	int num = (Integer.parseInt(minutes.getText()) - 1 );
		    	num = num < 0 ? num + 60 : num;
		    	minutes.setText(num > 9 ? "" + num : "0" + num);
		    	System.out.println("m");
		    	return;
		    }  
		}); 
		pmdown.add(minutes_down);

		
		//秒１プラスボタン
		JPanel psdown = new JPanel();
		psdown.setLayout(new  BoxLayout(psdown, BoxLayout.X_AXIS));
		psdown.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		psdown.setBackground(Color.GRAY);

		seconds_down= new JLabel("下");
		seconds_down.setFont(new Font("",Font.PLAIN ,15));
		seconds_down.setBorder(BorderFactory.createEmptyBorder(3, 15, 3, 15));
		seconds_down.setBackground(Color.BLACK);
		seconds_down.setOpaque(true);
		seconds_down.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	int num = (Integer.parseInt(seconds.getText()) - 1 );
		    	num = num < 0 ? num + 60 : num;
		    	seconds.setText(num > 9 ? "" + num : "0" + num);
		    	System.out.println("s");
		    	return;
		    }  
		}); 
		psdown.add(seconds_down);

		
		pdown.add(phdown);
		pdown.add(pmdown);
		pdown.add(psdown);
		return pdown;
				
	}
}
	


