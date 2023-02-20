package myButtons.auxiliaryClassList;

import java.util.HashMap;
import java.util.Map;

public class FourDirectionsDrawingNumber {
	Map<String, Integer> fourDirectionsNumber = new HashMap<>();
	
	FourDirectionsDrawingNumber(){
		fourDirectionsNumber.put("top", 1);
		fourDirectionsNumber.put("left", 2);
		fourDirectionsNumber.put("button", 3);
		fourDirectionsNumber.put("right", 4);
	}
}
