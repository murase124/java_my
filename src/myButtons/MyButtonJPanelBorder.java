package myButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.border.Border;

public class MyButtonJPanelBorder implements Border{
	final private String TOP = "Top";
	final private String LEFT = "Left";
	final private String BOTTOM = "Bottom";
	final private String RIGHT = "Right";
	
	private Insets insets = new Insets(0,0,0,0);
	/*
	 * Four Directions Color 
	 * Key(String) "TOP", "LEFT", "BOTTOM", "RIGHT"
	 * Value(Color)  String position　drawing　Color
	 */
	private Map<String, Color> fourColors = new HashMap<>();

	/*
	 * Four Directions Line Drawing Number
	 * Key(String) "TOP", "LEFT", "BOTTOM", "RIGHT"
	 * Value(int)    Key Position　Drawing　Number
	 */
	private Map<String, Integer> fourDrawingNumber = new HashMap<>();
	
	public MyButtonJPanelBorder(){
		resetFourDrawingNumber();
		resetFourColors();
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Insets insets = getBorderInsets(c);
		//JPanelの四方に描画する　順番変動
		 List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(fourDrawingNumber.entrySet());
	        // 3.比較関数Comparatorを使用してMap.Entryの値を比較する(昇順)
	        Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
	            public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
	                // 4. 昇順
	                return obj1.getValue().compareTo(obj2.getValue());
	            }
	        });

		for(Entry<String, Integer> entry : list_entries) {
			switch (entry.getKey()) {
			case TOP: {
				g.setColor(fourColors.get(TOP));
				g.fillRect(x, y, width, insets.top);
			    continue;
			}
			case LEFT: {
			    g.setColor(fourColors.get(LEFT));
				g.fillRect(x, y, insets.left, height);
				continue;
			}
			case BOTTOM: {
				g.setColor(fourColors.get(BOTTOM));
				g.fillRect(x, y + height - insets.bottom, width, insets.bottom);
				continue;
			}
			case RIGHT: {
				g.setColor(fourColors.get(RIGHT));
				g.fillRect(x + width - insets.right, y, insets.right, height);
				 continue;
			}
			default:
			}
		}	   
	}
	@Override
	public boolean isBorderOpaque() {
		return false;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
	    return this.insets;
	}
	
	
	/*
	 * addition
	 * Set_Get
	 */
	public void set_Insets(int top,int left, int bottom,int right) {
		setInsets(new Insets(top, left, bottom, right));
	}
	
	/*
	 * Default Number
	 */
	public void resetFourDrawingNumber(){
		fourDrawingNumber.put(TOP, 1);
		fourDrawingNumber.put(LEFT, 2);
		fourDrawingNumber.put(BOTTOM, 3);
		fourDrawingNumber.put(RIGHT, 4);
	}
	public void setFourDrawingNumber(int topNumber, int leftNumber, int bottomNumber, int rightNumber){
		fourDrawingNumber.put(TOP, topNumber);
		fourDrawingNumber.put(LEFT, leftNumber);
		fourDrawingNumber.put(BOTTOM, bottomNumber);
		fourDrawingNumber.put(RIGHT, rightNumber);
	}
	public void resetFourColors(){
		fourColors.put(TOP, new Color(0,0,0));
		fourColors.put(LEFT, new Color(0,0,0));
		fourColors.put(BOTTOM, new Color(0,0,0));
		fourColors.put(RIGHT, new Color(0,0,0));
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
	
	
	
	/*
	 * Basic
	 * Set_Get
	 */
	
	public Insets getInsets() {
		return insets;
	}
	public void setInsets(Insets insets) {
		this.insets = insets;
	}
}


