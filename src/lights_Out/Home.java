package lights_Out;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Home extends JFrame implements ActionListener{

	JButton but;//ボタン
	JTextArea jt;//名前
	JLabel jl;//名前

	
	public Home(JPanel cardPanel ) {
		// TODO 自動生成されたコンストラクター・スタブ
		
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		
		jl = new JLabel("名前");
		jl.setBounds(50,40,27,20);
		p1.add(jl);
		
		//名前ボックス
		jt = new JTextArea();
		jt.setBounds(90,40,160,20);
		p1.add(jt);
		
		
		//スタートボタン
		but= new JButton("スタート");
		but.addActionListener(this);
		but.setBounds(50, 200, 200, 30);
		p1.add(but);
		
		cardPanel.add(p1, "Home");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		//ゲームスタート
		//new Lights_Out(2, 3, this);
		Main.main_flg("Home",0);
	}
	
	

}
