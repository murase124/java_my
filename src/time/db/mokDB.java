package time.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class mokDB {

	Map<String, String> EXECUTEQUERY = new HashMap<>();
	Map<String, String> EXECUTEUPDATE = new HashMap<>();

	protected abstract String getCONNECTIONURL();
	protected abstract String getCONNECTIONUSER();
	protected abstract String getCONNECTIONPASSWORD();
	protected abstract String getFORNAME();
	
	public String driverLoad() {
		 // JDBC ドライバロード
    	String msg = "";
    	try{
            Class.forName(getFORNAME());
    		msg = "ドライバのロードに成功しました";
    	} catch(ClassNotFoundException e) {
    		msg = "ドライバのロードに失敗しました";
    	}
    	return msg;
	}
	
	public Connection connectino() {
        try {
        	return DriverManager.getConnection(getCONNECTIONURL(), getCONNECTIONUSER(), getCONNECTIONPASSWORD());
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
