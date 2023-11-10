package test1_static;

public class test2 {
	 private int val1 = 0; // 非static変数(インスタンス変数)
	    private static int val2 = 0; // static変数(クラス変数)
	 
	    // コンストラクタ　クラスがインスタンス化される度に変数に1加算する
	    public test2(int val1, int val2) {
	        this.val1 += val1;
	        test2.val2 += val2; // 「クラス名.変数名」でstatic変数を宣言
	    }
	 
	    // 変数の値を表示
	    public void print(){
	        System.out.println("val1 = " + val1 + ", val2 = " + val2);
	    }
	    public static void print2(){
	        System.out.println("val2 = " + val2+1);
	    }
	}

	 
	

