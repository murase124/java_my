package test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hsqldb {
    public static void main(String[] args) throws Exception {
        // JDBC ドライバロード
    	/*String msg = "";
    	try{
           // Class.forName("org.hsqldb.jdbcDriver");
    		msg = "ドライバのロードに成功しました";
        	System.out.println(msg);
    	} catch(ClassNotFoundException e) {
    		msg = "ドライバのロードに失敗しました";
        	System.out.println(msg + ",\n" + e);
        	return;

    	}*/

        // データベースに接続
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb/testdb;shutdown=true", "SA", "");

        // テーブル作成
        Statement st = connection.createStatement();
        //st.executeUpdate("CREATE TABLE TEST_TABLE (ID INTEGER, VALUE VARCHAR(10))");

        // データ挿入
       // st.executeUpdate("INSERT INTO TEST_TABLE VALUES (1, 'HOGE')");
        //st.executeUpdate("INSERT INTO TEST_TABLE VALUES (2, 'FUGA')");

        // データ取得
        ResultSet rs = st.executeQuery("SELECT * FROM TEST_TABLE");

        while (rs.next()) {
            System.out.println("ID=" + rs.getInt("ID") + ", VALUE=" + rs.getString("VALUE"));
        }

        st.close();
        connection.close();
    }
}