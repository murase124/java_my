package myButtons.myButton00;

import java.awt.Color;

import myButtons.MyButtonJPanel;
import myButtons.auxiliaryClassList.Layout;

public class Layout00 extends Layout{


	public Layout00(MyButtonJPanel my_button_jpanel) {
		super(my_button_jpanel);
	}

	@Override
	public void set_Layout() {
		getMy_Border().get(getMy_Border().NORMAL).setFourDrawingNumber(4, 3, 2, 1);
		getMy_Border().get(getMy_Border().NORMAL).set_Insets(3, 2, 2, 2);
		getMy_Border().get(getMy_Border().NORMAL).setFourColors(new Color(180,180,180), new Color(180,180,180), new Color(130,130,130), new Color(130,130,130));
		
		getMy_Border().get(getMy_Border().PRESSED).setFourDrawingNumber(1,2,3,4);
		getMy_Border().get(getMy_Border().PRESSED).set_Insets(3, 2, 2, 2);
		
		getMy_Border().get(getMy_Border().PRESSED).setFourColorsTop(new Color(130,130,130));
		getMy_Border().get(getMy_Border().PRESSED).setFourColorsLeft(new Color(130,130,130));
		getMy_Border().get(getMy_Border().PRESSED).setFourColorsBottom(new Color(180,180,180));
		getMy_Border().get(getMy_Border().PRESSED).setFourColorsRight(new Color(180,180,180));
		
		getMy_Listener().setBackgroundColor(new Color(160,160,160), new Color(130,130,130));
	}
}