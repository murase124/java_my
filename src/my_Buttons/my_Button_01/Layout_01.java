package my_Buttons.my_Button_01;

import java.awt.Color;

import my_Buttons.My_Button_JPanel;
import my_Buttons.My_Button_JPanel_Border;
import my_Buttons.My_Button_JPanel_MouseListener;

public class Layout_01 {

	My_Button_JPanel_MouseListener my_Listener;
	My_Button_JPanel_Border[] my_Line_Border;
	My_Button_JPanel_Border border_Parent;
	
	public Layout_01(My_Button_JPanel my_button_jpanel ) {
		my_Listener = my_button_jpanel.get_Listener();
		my_Line_Border = my_Listener.get_Border();

		my_Line_Border[0].set_pain_num(4,3,2,1);
		my_Line_Border[0].set_Insets(3, 2, 2, 2);
		my_Line_Border[0].set_colors(new Color(180,180,180), new Color(180,180,180), new Color(130,130,130), new Color(130,130,130));
		
		my_Line_Border[1].set_pain_num(1,2,3,4);
		my_Line_Border[1].set_Insets(3, 2, 2, 2);
		my_Line_Border[1].set_colors(new Color(130,130,130), new Color(130,130,130), new Color(180,180,180), new Color(180,180,180));
		my_Listener.set_Background_Color(new Color(160,160,160), new Color(130,130,130));
	
	}
	
}