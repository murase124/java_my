package myButtons.auxiliaryClassList;

import java.util.HashMap;
import java.util.Map;

import myButtons.MyButtonJPanelBorder;

public class MyButtonJPanelBorders {

	public final String PRESSED = "PRESSED";
	public final String NORMAL = "NORMAL";
	public Map<String, MyButtonJPanelBorder> mybuttonjpanelborders = new HashMap<>();

	public void set (String key, MyButtonJPanelBorder color){
		mybuttonjpanelborders.put(key, color);
	}
	
	public MyButtonJPanelBorder get (String key){
		return mybuttonjpanelborders.get(key);
	}
	
	

	void backgroundColor(){	
		mybuttonjpanelborders.put(PRESSED, new MyButtonJPanelBorder());
		mybuttonjpanelborders.put(NORMAL, new MyButtonJPanelBorder());
	}
}
