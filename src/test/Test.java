package test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Test {
	public static void main(String[] args){
		Timestamp a = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(a.getClass());
	}
}
