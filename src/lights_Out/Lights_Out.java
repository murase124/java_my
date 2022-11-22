package lights_Out;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class Lights_Out extends JFrame implements ActionListener {
	
	JLabel but[][];//ボタン
	boolean flg[][];//ボタンの状態

	Lights_Out(int x,int y , JPanel cardPanel ){
		
		
		but = new JLabel[x][y];
		flg = new boolean[x][y];
		
		//初期値　オフ
		for(int i =0; i< but.length; i++)
		Arrays.fill(flg[i], false);
		
		//パネル数　ボタン依存
		JPanel panel01 = new JPanel();
		panel01.setLayout(new GridLayout(but.length, but[0].length));
		
		//ボタン初期値　
		for(int i =0; i< but.length; i++) {
			for(int j =0; j < but[0].length; j++) {
				but[i][j]= new JLabel();
				but[i][j].setHorizontalAlignment(JLabel.CENTER);
				but[i][j].setBackground(Color.GRAY);
				but[i][j].setOpaque(true);
				but[i][j].setBorder(new LineBorder(Color.BLACK , 1));
				panel01.add(but[i][j]);
				
				but[i][j].addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				    	change_Event(e);
				    	if(result_flg()) {
				    		Main.main_flg("Lights_Out",0);
				    		return;
				    	}
				    }  
				}); 
			}
		}
        cardPanel.add(panel01, "Lights_Out");
	}
	
	
	//ボタンイベント
	@Override
	public void actionPerformed(ActionEvent as) {
		// TODO 自動生成されたメソッド・スタブ
	}
	
	
	public void change_Event(EventObject as) {
		//押されたボタン　と周り　カラー変更
		LOOP_I:
		for(int i =0; i< but.length; i++) {
			for(int j =0; j < but[0].length; j++) {
				if(as.getSource() == but[i][j]) {
					if(i+1 < but.length)
					{flg[i+1][j] = change(but[i+1][j],flg[i+1][j]);};
					if(i-1 >= 0) 
					{flg[i-1][j] = change(but[i-1][j],flg[i-1][j]);};
					if(j+1 < but[0].length)
					{flg[i][j+1] = change(but[i][j+1],flg[i][j+1]);};
					if(j-1 >= 0)
					{flg[i][j-1] = change(but[i][j-1],flg[i][j-1]);};
					
					flg[i][j] = change(but[i][j],flg[i][j]);
					break LOOP_I;
				}
			}
		}
	}
		
	public Boolean result_flg() {
		//判定
		for(;;) {
			for(int i =0; i< but.length; i++) {
				for(int j =0; j < but[0].length; j++) {
					if(!flg[i][j]) return false; //まだ
				}
			}
			//ゴール処理
			for(int i =0; i< but.length; i++) {
				for(int j =0; j < but[0].length;j++) {
					but[i][j].setText("Goor");
					but[i][j].setBackground(Color.ORANGE);
				}
			}
			return true;//ゴール
		}
	}
	
	//カラー変更処理
	public boolean change(JLabel but , boolean flg) {
		if(flg) {
			but.setBackground(Color.GRAY);
			return false;
		}else {
			but.setBackground(Color.GREEN);
			return true;
		}
	}
	

}
