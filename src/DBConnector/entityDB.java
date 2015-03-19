package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class entityDB {

	Connection conn;
//	private static String lock = "lock";

	public entityDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
							"jdbc:mysql://172.16.7.85:3306/entity?characterEncoding=utf8",
							"qa", "qa");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertQuestion(String content){
		try {
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO `question`(`content`) VALUES (?)");
			stat.setString(1, content);
			stat.executeUpdate();
		} catch (Exception ex) {
		}
	}
	
	public void insertQuestion(int id, String content) {
		try {
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO `question`(`id`, `content`) VALUES (?,?)");
			stat.setInt(1, id);
			stat.setString(2, content);
			stat.executeUpdate();
		} catch (Exception ex) {
		}
	}
	
	public void insertMiner(
			int questionID, int startIndex, int endIndex, 
			int candID, String candTitle, double weight){
		try {
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO `miner`(`questionID`, `startIndex`,`endIndex`,`candID`,`candTitle`,`weight`) VALUES (?,?,?,?,?,?)");
			stat.setInt(1, questionID);
			stat.setInt(2, startIndex);
			stat.setInt(3, endIndex);
			stat.setInt(4, candID);
			stat.setString(5, candTitle);
			stat.setDouble(6, weight);
			stat.executeUpdate();
		} catch (Exception ex) {
		}
	}
	
	public void insertSpotlight(
			int questionID, int startIndex, int endIndex, 
			String candTitle, String candUri, int support,
			double finalScore, double priorScore, double contextualScore,
			double percentageOfSecondRank, String types){
		try {
			PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO `spotlight`(`questionID`, `startIndex`,`endIndex`,`candTitle`,`candUri`,`support`,`finalScore`,`priorScore`,`contextualScore`,`percentageOfSecondRank`,`types`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			stat.setInt(1, questionID);
			stat.setInt(2, startIndex);
			stat.setInt(3, endIndex);
			stat.setString(4, candTitle);
			stat.setString(5, candUri);
			stat.setInt(6, support);
			stat.setDouble(7, finalScore);
			stat.setDouble(8, priorScore);
			stat.setDouble(9, contextualScore);
			stat.setDouble(10, percentageOfSecondRank);
			stat.setString(11, types);
			stat.executeUpdate();
		} catch (Exception ex) {
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
