package lights_Out;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main  extends JFrame{
	
	static Main main = new Main();
	
	public static void main(String[] args){
		
		main = new Main();
		new Home(main.cardPanel);
        
        main.setVisible(true);
	}
	
	
	JPanel cardPanel;
    CardLayout layout;
    
	public Main() {
		// TODO 自動生成されたコンストラクター・スタブ
		  // CardLayout用パネル
        cardPanel = new JPanel();
        layout = new CardLayout();
        cardPanel.setLayout(layout);
        add(cardPanel, BorderLayout.CENTER);

		// TODO 自動生成されたコンストラクター・スタブ
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 300, 300);
		setTitle("Event");

	}

	
	static void main_flg(String name, int status)  {
		if("Home".equals(name)) {
			if(status == 0) {
				new Lights_Out(2,3,main.cardPanel);
				main.layout.show(main.cardPanel, "Lights_Out");
			}
		}
		if("Lights_Out".equals(name)) {
			if(status == 0)  {
				Timer timer = new Timer(false);
				TimerTask task = new TimerTask() {
					public void run() {
						main.layout.show(main.cardPanel, "Home");
						timer.cancel();
					}
				};
				timer.schedule(task, 5000);
			}
		}
	}
}
