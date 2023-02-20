package myButtons.auxiliaryClassList;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class FourDirectionsColors {
	/*
	 * Four Directions Color 
	 * Key(String) "TOP", "LEFT", "BOTTOM", "RIGHT"
	 * Value(Color)  String position　drawing　Color
	 */
	private Map<String, Color> fourColors = new HashMap<>();
	private String TOP = "Top";
	private String LEFT = "Left";
	private String BOTTOM = "Bottom";
	private String RIGHT = "Right";
	
	/*
	 * BLACK Color Initialize
	 */
	public FourDirectionsColors(){
		fourColors.put(TOP, new Color(0,0,0));
		fourColors.put(LEFT, new Color(0,0,0));
		fourColors.put(BOTTOM , new Color(0,0,0));
		fourColors.put(RIGHT , new Color(0,0,0));
	}
	public void setFourColors(Color topColor, Color leftColor, Color bottomColor, Color rightColor){
		fourColors.put(TOP, topColor);
		fourColors.put(LEFT, leftColor);
		fourColors.put(BOTTOM, bottomColor);
		fourColors.put(RIGHT, rightColor);
	}
	public void setFourColorsTop(Color topColor){
		fourColors.put(TOP, topColor);
	}
	public void setFourColorsLeft(Color leftColor){
		fourColors.put(LEFT, leftColor);
	}
	public void setFourColorsBottom(Color bottomColor){
		fourColors.put(BOTTOM, bottomColor);
	}
	public void setFourColorsRight(Color rightColor){
		fourColors.put(RIGHT, rightColor);
	}
}
