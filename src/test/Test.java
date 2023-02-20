package test;

public class Test {
	public static void main(String[] args){
		try {
			System.out.println(Integer.parseInt("a"));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
