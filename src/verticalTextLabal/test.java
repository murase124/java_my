package vertical_text_labal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class test extends JFrame{
	
  public static void main(String[] args){
	  new test();
  }
  
  test(){
	getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel p1 = new JPanel();
	p1.setBounds(10,19,100,100);
	p1.setBackground(Color.yellow);
	p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
	Font font = new Font("",Font.PLAIN ,30);
	vertical_text p = new vertical_text("あたらしいあさがきた、aAB。333", font ,new Color(
			(int) (Math.random()*256),
			(int) (Math.random()*256),
			(int) (Math.random()*256)));
	c1("あたらしいあさがきた、aAB。333",p1);
	add(p);
	add(p1);

	setVisible(true);
	setBounds(200,100,200,600);
  }
  
  JPanel c1 (String string , JPanel p) {
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
	
		String[] strArray = string.split("");
		for(String s : strArray) {
			JLabel jlavel = new JLabel(s);
			jlavel.setFont(new Font("",Font.PLAIN ,30));
			p.add(jlavel);
		}
		return p;
	}
}