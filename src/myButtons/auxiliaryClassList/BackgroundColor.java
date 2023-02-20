package myButtons.auxiliaryClassList;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class BackgroundColor  {

	
	public final String NOFOCUS = "NOFOCUS";
	public final String FOCUS = "FOCUS";
	public Map<String, Color> backgroundColors = new HashMap<>();

	public void set (String key, Color color){
		backgroundColors.put(key, color);
	}
	
	public Color get (String key){
		return backgroundColors.get(key);
	}
	
	

	void backgroundColor(){	
		backgroundColors.put(NOFOCUS, new Color(255,255,255)) ;
		backgroundColors.put(FOCUS, new Color(255,255,255)) ;
	}
}