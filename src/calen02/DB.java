package calen02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import textDBLog.TextDBLog;

public class DB {
	private Connection connection = null;
	private PreparedStatement ps = null;
	final private String URL ="jdbc:hsqldb:file:calen01/schedule;shutdown=true";
	final private String USERNAME ="javatest";
	final private String PASSWORD ="";
	final private String DRIVERTEXT ="org.hsqldb.jdbcDriver";
	final private String LOGFILEURL ="calen01/schedule.txt";
	final private String GETSCHEDULESQLTEXT ="SELECT * FROM schedules";
	final private String GETSCHEDULESQLTEXT2 ="SELECT * FROM schedules WHERE startDate <= ? AND ifnull(endDate, startDate) > ? ORDER BY startDate";
	final private String SETSCHEDULESQLTEXT2 = "INSERT INTO schedules (startDate, endDate,title,texts) VALUES ( ?, ?, ?, ?)";

	
	
	/* sql分のログを保存 */
	public void Log(String sql, String[] replace) {
		String sql2 =  sql;
		if(replace != null) {
			for(int i=0;i < replace.length;i++)	{
				sql2 = sql2.replaceFirst("[?]", replace[i]);
			}
		}
		new TextDBLog(LOGFILEURL, new String[] {sql2+"\r\n"});
		
	}
	public void Log(String sql) {
		String sql2 =  sql;
		new TextDBLog(LOGFILEURL, new String[] {sql2+"\r\n"});
		
	}
	
 
	/*　接続 */
	//接続する
	private Boolean connection() {
		connection = null;
		if(!inputDriver()) return false;
		try {
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			return  true;
		} catch (SQLException e) {
			System.out.println("Mysql への接続に失敗しました。");
			System.out.println(e);
			return false;
		}
	}
	//切断する
	private void close() {
		   try {
			   ps.close();
			   connection.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("SELECTに失敗しました。" + e);
		}
	}
	//ドライバー
	private Boolean inputDriver() {
        // JDBC ドライバロード
		try {
			Class.forName(DRIVERTEXT);
		}catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	
	/* SQL */
	//スケジュール参照
	public ResultSet getSchedule(LocalDateTime startDate, LocalDateTime endDate) {
		ResultSet rs = null; 
		try {
			if(!connection()) {return rs;}

			ps = connection.prepareStatement(GETSCHEDULESQLTEXT2);
			Timestamp endTimestamp = Timestamp.valueOf(endDate);
			ps.setTimestamp(1, endTimestamp);
			Timestamp startTimestamp = Timestamp.valueOf(startDate);
			ps.setTimestamp(2, startTimestamp);
			rs = ps.executeQuery();
			
			String[] prepared = new String[] {startTimestamp.toString(), endTimestamp.toString()};
			Log(GETSCHEDULESQLTEXT, prepared);
			close();
		} catch (SQLException e) {
			System.out.println("SELECTに失敗しました。" + e);
		}
		return rs;
	}
	//スケジュール挿入
	public int setSchedule(LocalDateTime startDate, LocalDateTime endDate, String title, String texts) {
		int resltlCount = -1;
		try {
			if(!connection()) {return resltlCount;}

			ps = connection.prepareStatement(SETSCHEDULESQLTEXT2);
			Timestamp startTimestamp = Timestamp.valueOf(startDate);
			ps.setTimestamp(1, startTimestamp);
			Timestamp endTimestamp = Timestamp.valueOf(endDate);
			ps.setTimestamp(2, endTimestamp);
			ps.setString(3, title);
			ps.setString(4, texts);
			resltlCount = ps.executeUpdate();
			
			String[] prepared = new String[] {
					startTimestamp.toString()
					,endTimestamp.toString()
					,title
					,texts
					};
			Log(SETSCHEDULESQLTEXT2, prepared);
			close();
		} catch (SQLException e) {
			System.out.println("SELECTに失敗しました。" + e);
		}
		
		return resltlCount;
	}
	public int setSchedule(LocalDateTime startDate, String title, String texts) {
		return setSchedule(startDate, startDate, title, texts);
	}
	
	/* テスト用SQL */
	/*
	//テスト用データ参照
		public void testexecuteQuery(ResultSet rs) {
			try {
				while(rs.next()) {
					for(int i=0;i < rs.getMetaData().getColumnCount();i++)
					System.out.print(rs.getString(i+1) + ", ");
					System.out.println("");
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		*/
		
		//update テーブル作成　行追加
		/*　テスト用　必要時以外コメント　*/
		 /* public int executeUpdate(String sql)  {
	        // データベースに接続
			int resltlCount = -1;
			if(!connection()) {return resltlCount;}
	        // テーブル作成
	        Statement st;
			try {
				st = connection.createStatement();
				resltlCount = st.executeUpdate(sql);
				Log(sql);
				st.close();
		        connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resltlCount;
	    }
		//データ参照
		public ResultSet executeQuery(String sql) {
			ResultSet rs = null;
			  // データベースに接続
			if(!connection()) {return rs;}
	        // テーブル作成
	        Statement st;
			try {
				st = connection.createStatement();
				rs = st.executeQuery(sql);
				st.close();
		        connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}*/
		/*
		   public static void main(String[] args) {
			   
		    	DB db = new DB();
		    	db.inputDriver();
		    	String sql = "SELECT * FROM schedules";
		    	sql = "UPDATE schedules SET endDate = '2023-06-08 00:00:00'";
		    	//sql = "INSERT INTO a (id,name) VALUES (2,'ki')";
		    	//db.getSchedule();
				//db.testexecuteQuery(db.executeQuery(db.GETSCHEDULESQLTEXT));
				System.out.println("/");
				//System.out.println(db.executeUpdate(sql));

		    	//db.executeUpdate(sql);
		    	System.out.println(sql);

		    }
		    */
		/* テスト用　必要時以外コメント ここまで */
	
}
