package time;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class Main  extends JFrame {
	
	private JPanel cardPanel;
	private CardLayout layout;
	private JLabel clock_bu;
	private JLabel timer_bu;
	private static Clock clock;
	private static TimerClock timerclock;
    
    final public static String cardPanelName_Clock = "Clock";
    final public static String cardPanelName_Timer = "Timer";
    
	public static void main(String[] args){
		Main main = new Main();
		clock = new Clock(main.cardPanel);
		timerclock = new TimerClock(main.cardPanel);
		main.layout.show(main.cardPanel, cardPanelName_Clock);
		main.setVisible(true);
	}
	
	
    
	public Main() {
		  // CardLayout用パネル
        cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);
        cardPanel.setOpaque(false);
        
        //切り替えボタンパネル
        JPanel phome = new JPanel();
        phome.setOpaque(false);
        
        //Clock移動ボタン
        JPanel pclock = new JPanel();
        pclock.setLayout(new BorderLayout());
        pclock.setBorder(new LineBorder(new Color(10,10,10),2,true));
        pclock.setBackground(new Color(160,160,160));
        
        clock_bu = new JLabel("時間");
        clock_bu.setBorder(BorderFactory.createEmptyBorder(1, 4, 1, 4));
        clock_bu.setFont(new Font("",Font.BOLD ,13));
        clock_bu.addMouseListener(new MouseAdapter()  
		{  
        	@Override
		    public void mouseClicked(MouseEvent e)  
		    {  
				layout.show(cardPanel, cardPanelName_Clock);
				clock.setOffset();
				clock.clockprocess();
		    	return;
		    }  
		}); 
        
        //Timer移動ボタン
		JPanel ptimer = new JPanel();
		ptimer.setLayout(new BorderLayout());
		ptimer.setBorder(new LineBorder(new Color(10,10,10),2,true));
		ptimer.setBackground(new Color(150,150,150));
		
		timer_bu = new JLabel("タイマー");
		timer_bu.setBorder(BorderFactory.createEmptyBorder(1, 4, 1, 4));
		timer_bu.setFont(new Font("",Font.BOLD ,13));
		timer_bu.addMouseListener(new MouseAdapter()  
		{  
			@Override
		    public void mouseClicked(MouseEvent e)  
		    {  
				layout.show(cardPanel, cardPanelName_Timer);
				clock.cancel();
		    	return;
		    }  
		}); 
		
		pclock.add(clock_bu);
		ptimer.add(timer_bu);
		phome.add(pclock);
		phome.add(ptimer);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		setTitle("時計");
		
		getContentPane().add(cardPanel, BorderLayout.CENTER);
		getContentPane().add(phome, BorderLayout.PAGE_END);
		getContentPane().setBackground(new Color(150,150,150));

	}
}
