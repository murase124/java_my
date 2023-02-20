package myButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyButtonver2 extends JPanel{
	 @Override protected void paintComponent(Graphics g) {
	       super.paintComponent(g);

		     if (!isOpaque()) {
		         int w = getWidth() - 0;
		         int h = getHeight() - 0;
		         Graphics2D g2 = (Graphics2D) g.create();
		        System.out.println(w+",aaa"+h);


		         g2.setPaint(Color.green);
		         g2.fillRoundRect(0, 0, w, h, 50, 30);

		         g2.setPaint(Color.YELLOW);
		         g2.fillRoundRect(2, 2, w-4, h-4, 50, 30);

		        
		         g2.setPaint(Color.GRAY);
		         g2.drawRoundRect(0, 0, w, h, 50, 30);
		         g2.dispose();
		       }
		       //super.paintComponent(g);
		     }
	 
	  @Override public void updateUI() {
	    super.updateUI();
	    setOpaque(false);
	    setBorder(new Border() {
			
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				// TODO 自動生成されたメソッド・スタブ
				Insets insets = getBorderInsets(c);
				 g.setColor(Color.orange);

				g.fillRect(x, y, width, insets.top);

				 g.fillRect(x, y, insets.left, height);
				
				 g.fillRect(x, y + height - insets.bottom, width, insets.bottom);
				
				 g.fillRect(x + width - insets.right, y, insets.right, height);
			}
			
			@Override
			public boolean isBorderOpaque() {
				// TODO 自動生成されたメソッド・スタブ
				return false;
			}
			
			@Override
			public Insets getBorderInsets(Component c) {
				// TODO 自動生成されたメソッド・スタブ
				return new Insets(10, 10, 100, 10);
			}
		});
	    setBorder(BorderFactory.createEmptyBorder(40, 8, 4, 8));

	  }
}
