package paint;

import javax.swing.JButton;
import javax.swing.JPanel;

abstract public class ColorPanel extends JPanel {
	abstract void set();
	//switching　パネルを切り替えるボタン
	abstract public void colorpanel(JButton switching);
}
