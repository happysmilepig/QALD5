package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class entityDB {

	Connection conn;
	private static String lock = "lock";
	
	public entityDB(){
		for(;;){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://172.16.7.85:3306/entity?characterEncoding=utf8",
						"qa","qa");
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
			}
		}
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
