package DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class insertTrain {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		entityDB db = new entityDB();
		
		int question;int start;int end; String title;
		double weight; double linkprobability; double commonness;
		int linkfrequency; int documentfrequency; int entityfrequency;
		double finalscore; int support; double priorscore; double contextscore;
		double percentage; double finalscore2; int support2; double priorscore2;
		double contextscore2; double percentage2;int label;
		
		int pattern;
		String markup;
		
		StringBuilder sb = new StringBuilder();
		
		String markQuery = "select * from mark";
		ResultSet mark = db.select(markQuery);
		
		int count = 0;
		while(mark.next()){
			question = mark.getInt(2);
			start = mark.getInt(3);
			end = mark.getInt(4);
			
			markup = mark.getString(5);
			pattern = mark.getInt(6);
			
			
			sb.setLength(0);
			sb.append(" where questionID=");
			sb.append(mark.getString(2));
			sb.append(" AND startIndex=");
			sb.append(mark.getString(3));
			sb.append(" AND endIndex=");
			sb.append(mark.getString(4));
			
			String minerQuery = "select * from miner"+sb.toString();
			String dexterQuery = "select * from dexter"+sb.toString();
			String spotQuery = "select * from spotlight"+sb.toString();
			String spot2Query = "select * from spotlight2"+sb.toString();
			
			HashSet<String> set = new HashSet<String>();
			
			ResultSet miner = db.select(minerQuery);				
			while(miner.next()){
				set.add(miner.getString(6));
			}
			
			ResultSet dexter = db.select(dexterQuery);
			while(dexter.next()){
				set.add(dexter.getString(11));
			}
			
			ResultSet spot = db.select(spotQuery);
			while(spot.next()){
				set.add(spot.getString(5));
			}
			
			ResultSet spot2 = db.select(spot2Query);
			while(spot.next()){
				set.add(spot.getString(5));
			}
			
			for (String tmptitle : set) {
				if(tmptitle.length()==0){
					continue;
				}
				
				title = tmptitle;
				
				String query;
				//miner
				if(tmptitle.contains("\"")){
					query = minerQuery + " AND candTitle=" + "'" + tmptitle + "'";
				}else {
					query = minerQuery + " AND candTitle=" + "\"" + tmptitle + "\"";
				}
				
				ResultSet mResultSet = db.select(query);
				if(mResultSet.next()){
					weight = mResultSet.getDouble(7);
				}else{
					weight = 0;
				}
//				if(mResultSet.next()){
//					System.out.println("***");
//				}else{
//					System.out.println("no");
//				}

				//dexter
				if(tmptitle.contains("\"")){
					query = dexterQuery + " AND candTitle=" + "'" + tmptitle + "'";
				}else {
					query = dexterQuery + " AND candTitle=" + "\"" + tmptitle + "\"";
				}
				
				ResultSet dResultSet = db.select(query);
				if(dResultSet.next()){
					linkprobability=dResultSet.getDouble(5);
					commonness=dResultSet.getDouble(9);
					linkfrequency=dResultSet.getInt(6); 
					documentfrequency=dResultSet.getInt(7);
					entityfrequency=dResultSet.getInt(8);
				}else{
					linkprobability=0;
					commonness=0;
					linkfrequency=0; 
					documentfrequency=0;
					entityfrequency=0;
				}
				
				//spot
				if(tmptitle.contains("\"")){
					query = spotQuery + " AND candTitle=" + "'" + tmptitle + "'";
				}else {
					query = spotQuery + " AND candTitle=" + "\"" + tmptitle + "\"";
				}
				
				ResultSet sResultSet = db.select(query);
				if(sResultSet.next()){
					finalscore = sResultSet.getDouble(8);
					support = sResultSet.getInt(7); 
					priorscore = sResultSet.getDouble(9);
					contextscore = sResultSet.getDouble(10);
					percentage = sResultSet.getDouble(11); 
				}else{
					finalscore = 0;
					support = 0; 
					priorscore = 0;
					contextscore = 0;
					percentage = 0; 
				}
				
				
				//spot2
				if(tmptitle.contains("\"")){
					query = spot2Query + " AND candTitle=" + "'" + tmptitle + "'";
				}else {
					query = spot2Query + " AND candTitle=" + "\"" + tmptitle + "\"";
				}
				ResultSet sResultSet2 = db.select(query);
//				System.out.println(query);
				
				if(sResultSet2.next()){
					finalscore2 = sResultSet2.getDouble(8);
					support2 = sResultSet2.getInt(7); 
					priorscore2 = sResultSet2.getDouble(9);
					contextscore2 = sResultSet2.getDouble(10);
					percentage2 = sResultSet2.getDouble(11); 
//					System.out.println("****");
				}else{
					finalscore2 = 0;
					support2 = 0; 
					priorscore2 = 0;
					contextscore2 = 0;
					percentage2 = 0; 
//					System.out.println("----");
				}
//				System.out.println(sResultSet2.getDouble(8));
				
				if(pattern == 1 && title.equals(markup)){
					label = 1;
				}else{
					label = 0;
				}
				++count;
				if(count%100 == 0){
					System.out.println(count);
				}
				db.insertTrain(question, start, end, tmptitle, weight, linkprobability, commonness, linkfrequency, documentfrequency, entityfrequency, finalscore, support, priorscore, contextscore, percentage, finalscore2, support2, priorscore2, contextscore2, percentage2, label);
			}
//			break;
		}
		db.close();
	}
}
