package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Test;

public class DBTool {
	public static final String dbDriver		=		"com.mysql.jdbc.Driver";

	
	public Connection conn;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public DBTool() {
		try {
			conn = DriverManager.getConnection(DataBaseConst.dbUrl, DataBaseConst.dbUser, DataBaseConst.dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Execute query with parameters
	 * rs = dbtool.executeQuery("SELECT * FROM users where Type= ? order by UpdateTime limit 100", params); 
	 * @param query query string
	 * @param params WHERE parameters
	 * @return result set
	 */
	public ResultSet executeQuery(String query, LinkedList<String> params) {
		try {
			ps = conn.prepareStatement(query);
			if(params != null) {
				for(int i=0; i<params.size(); i++) {
					ps.setObject(i+1, params.get(i));
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * execute query without any parameter
	 * @param query query string
	 * @return result set
	 */
	public ResultSet executeQuery(String query) {
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * update/insert into db with parameters,
	 * which can help to insert text content with quotation marks '"'.
	 * @param update
	 * @param indexList
	 * @param strList
	 * @return
	 */
	public int executeUpdate(String update, int[] indexList, String[] strList) {
		try {
			ps = conn.prepareStatement(update);
			if(indexList.length != strList.length) {
				return 0;
			}
			for(int i=0; i<indexList.length; i++) {
				ps.setString(indexList[i], strList[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * update data table
	 * @param update the sql statement of update
	 * @return the number of data line influenced
	 */
	public int executeUpdate(String update) {
		try {
			ps = conn.prepareStatement(update);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * to make db connection closed
	 * @return if connection is not null, close it and return close status
	 */
	public boolean closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				if(conn.isClosed()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * to make db statement closed 
	 * @return if connection is not null, close it and return close status
	 */
	public boolean closeStatement() {
		if(ps != null) {
			try {
				ps.close();
				if(ps.isClosed()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * print result set details
	 * @param rs ResultSet
	 */
	public void printResult(ResultSet rs, boolean metaVisible) {
		if(rs == null) {
			return;
		}
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnCount = rsMetaData.getColumnCount();
			if(metaVisible) {
				for(int k=1; k<columnCount; k++) {
					System.out.print(rsMetaData.getColumnLabel(k)+"\t");
				}
				System.out.println(rsMetaData.getColumnLabel(columnCount));
			}
			while(rs != null && rs.next()) {
				for(int k=1; k<columnCount; k++) {
					System.out.print(rs.getString(k)+"\t");
				}
				System.out.println(rs.getString(columnCount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDB() {
		// initialize
		DBTool dbtool = new DBTool();
		ResultSet rs;
		
		// update & select query
		String query = "SELECT * FROM `qa_qapair` LIMIT 10";
//		LinkedList<Object> params = new LinkedList<Object>();  
//        params.add("HOME");  
//		rs = dbtool.executeQuery("SELECT * FROM users where Type= ? order by UpdateTime limit 100", params); 
//		String update = "INSERT INTO `qa_qapair` "
//				+ "VALUES ("
//				+ "NULL, 1, ?, ?, 1"
//				+ ")";
//		String delete = "DELETE FROM `qa_qapair` "
//				+ "WHERE `pid`=1";
//		
//		String[] strList = {"question", "answer"};
//		int[] indexList = {1, 2};
//		int updateNum = dbtool.executeUpdate(update, indexList, strList);
//		dbtool.closeStatement();
//		int deleteNum = dbtool.executeUpdate(delete);
//		dbtool.closeStatement();
//		if(updateNum > 0) {
//			System.out.println("update finished.");
//		}
//		if(deleteNum > 0) {
//			System.out.println("delete finished.");
//		}
		
		rs = dbtool.executeQuery(query);
		printResult(rs,true);
		
		dbtool.closeStatement();
		
		// close statement and connection
		if(dbtool.closeConnection()) {
			System.out.println("Connectin closed.");
		}
		
	}
	
	public static void main(String[] args) {
	}
	
}
