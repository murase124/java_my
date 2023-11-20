package calen01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import textDBLog.TextDBLog;

public class DB {
	Connection connection = null;
	public String getScheduleSQLText() {
		return "SELECT * FROM schedule month IN (?)";
	}
	public String setScheduleSQLText() {
		return "INSERT INTO TEST_TABLE VALUES (2, 'FUGA')";
	}
	public String[] getConnectionText() {
		return new String[]{"jdbc:mysql://localhost/schedule","javatest",""};
	}
	public String getDriverText() {
		return "Com.mysql.cj.jdbc.Driver";
	}
	public void Log(String sql, String[] replace) {
		String sql2 =  sql;
		if(replace != null) {
			for(int i=0;i < replace.length;i++)	{
				sql2 = sql.replaceFirst("?", replace[i]);
			}
		}
		new TextDBLog("calen/calen01/db.txt\";", new String[] {sql2+"\r\n"});
		
	}
	
    public static void main(String[] args) {
    	DB db = new DB();
    	db.inputDriver();
    	String sql = ""
    			+ "CREATE TABLE repetition ("
    			+ "repetitionID INT NOT NULL AUTO_INCREMENT"
    			+ ", scheduleID INT NOT NULL"
    			+ ", unitID INT NOT NULL"
    			+ ", createTime TIMESTAMP NOT NULL DEFAULT current_timestamp"
    			+ ", Delet boolean not null DEFAULT 0"
    			+ ", FOREIGN KEY(scheduleID) REFERENCES schedule(id)"
    			+ ", FOREIGN KEY(unitID) REFERENCES repetitionUnit(repetitionUnitID)"
    			+ ", PRIMARY KEY(repetitionID, scheduleID))";
    	db.createTable(sql);
    	
    	System.out.println(sql);
    }
	
	public Boolean connection() {
		connection = null;
		try {
			String[] cotext = getConnectionText();
			connection = DriverManager.getConnection(cotext[0],cotext[1],cotext[2]);
			return  true;
		} catch (SQLException e) {
			System.out.println("Mysql への接続に失敗しました。");
			return false;
		}
	}
	
	public Boolean inputDriver() {
        // JDBC ドライバロード
		try {
			Class.forName(getDriverText());
		}catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	public Boolean createTable(String sql)  {
        // データベースに接続
		if(!connection()) {return false;}
        // テーブル作成
        Statement st;
		try {
			st = connection.createStatement();
			st.executeUpdate(sql);
			Log(sql, null);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }
	
	public void getSchedule(int month, Map<String, String> getText) {
		try {
			String sql = getScheduleSQLText();
			PreparedStatement ps = connection.prepareStatement(sql);
			String[] prepared = new String[1];
			prepared[0] = (month-1) +","+ month + "," + (month+1);
			ps.setString(1, prepared[0]);
			ResultSet rs = ps.executeQuery(sql);
			Log(sql, prepared);
			while(rs.next()) {
				String id = rs.getString("userid");
				String password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			System.out.println("SELECTに失敗しました。" + e);
		}
	}
	public void setSchedule(int month, int time, String title, String text, Boolean loop) {
		try {
			String sql = setScheduleSQLText();
			PreparedStatement ps = connection.prepareStatement(sql);
			String[] prepared = new String[1];
			prepared[0] = (month-1) +","+ month + "," + (month+1);
			ps.setString(1, prepared[0]);
			ResultSet rs = ps.executeQuery(sql);
			Log(sql, prepared);
			while(rs.next()) {
				String id = rs.getString("userid");
				String password = rs.getString("password");
			}
			
		} catch (SQLException e) {
			System.out.println("SELECTに失敗しました。" + e);
		}
	}
}
