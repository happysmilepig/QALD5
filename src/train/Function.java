package train;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DBConnector.entityDB;

public class Function {
	entityDB db;
	public Function(){
		db = new entityDB();
	}
	
	public void close(){
		db.close();
	}
	
	public int getTrainSetID(double rate){
		int count = 0;
		String query = "select count(id) from train";
		ResultSet resultSet = db.select(query);
		try {
			if(resultSet.next()){
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int id = (int) (count * rate);
		return id;
	}
	
	public void allfeatures(String[] feature,String path){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] attr= {"weight","linkprobability","commonness",
				"linkfrequency","documentfrequency","entityfrequency",
				"finalscore","support","priorscore","contextscore",
				"percentage","finalscore2","support2","priorscore2",
				"contextscore2","percentage2","label"};
		
		for(int i=0; i<attr.length; ++i){
			map.put(attr[i], i+6);
		}
		
		try {
			BufferedWriter fout=new BufferedWriter(new FileWriter(path));
			
			String query = "select * from train";
			ResultSet result = db.select(query);
			
			StringBuilder sb = new StringBuilder();
			
			while(result.next()){
				sb.setLength(0);
				int label = result.getInt(22);
				sb.append(label);
				for(int i=0; i<feature.length; ++i){
					int column = map.get(feature[i]);
					String value = result.getString(column);
					sb.append(" ");
					sb.append(i+1);
					sb.append(":");
					sb.append(value);
				}
				sb.append("\n");
				fout.write(sb.toString());
			}
			fout.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void features(String[] feature,String path){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] attr= {"weight","linkprobability","commonness",
				"linkfrequency","documentfrequency","entityfrequency",
				"finalscore","support","priorscore","contextscore",
				"percentage","finalscore2","support2","priorscore2",
				"contextscore2","percentage2","label"};
		
		for(int i=0; i<attr.length; ++i){
			map.put(attr[i], i+6);
		}
		
		try {
			BufferedWriter fout=new BufferedWriter(new FileWriter(path));
			
			String query = "select * from train where question < 202";
			ResultSet result = db.select(query);
			
			StringBuilder sb = new StringBuilder();
			
			while(result.next()){
				sb.setLength(0);
				int label = result.getInt(22);
				sb.append(label);
				for(int i=0; i<feature.length; ++i){
					int column = map.get(feature[i]);
					String value = result.getString(column);
					sb.append(" ");
					sb.append(i+1);
					sb.append(":");
					sb.append(value);
				}
				sb.append("\n");
				fout.write(sb.toString());
			}
			fout.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void divide(double rate,String in, String train, String test){
		int end = getTrainSetID(rate);
		BufferedReader fin;
		BufferedWriter trainOut;
		BufferedWriter testOut;
		
		int count = 0;
		String line;
		try {
			fin = new BufferedReader(new FileReader(in));
			trainOut = new BufferedWriter(new FileWriter(train));
			testOut = new BufferedWriter(new FileWriter(test));

			while( (line = fin.readLine()) != null){
				if(count < end){
					trainOut.write(line);
					trainOut.write("\n");
				}else{
					testOut.write(line);
					testOut.write("\n");
				}
				++count;
			}
			
			fin.close();
			trainOut.close();
			testOut.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void compare(String in1, String in2){
		int count = 0;
		int pre = 0;
		int size = 0;
		try {
			BufferedReader fin1 = new BufferedReader(new FileReader(in1));
			BufferedReader fin2 = new BufferedReader(new FileReader(in2));
			
			for(;;){
				String line1 = fin1.readLine();
				String line2 = fin2.readLine();
				
//				System.out.println(line1);
//				System.out.println(line2);
//				break;
			
				if(line1 == null){
					break;
				}
				
				String[] list1 = line1.split(" ");
				int d1 = Integer.parseInt(list1[0]);
				double d2 = Double.parseDouble(line2);
				
//				System.out.println(d1+"\t"+d2);
//				System.out.println(d2);
				if(d1 > 0 && d2 > 0){
					++count;
				}
				if(d2 > 0){
					++pre;
				}
				if(d1 > 0){
					++size;
				}
//				break;
			}
			System.out.println(count);
			System.out.println(pre);
			System.out.println(size);
			System.out.println((double)count/pre);
			System.out.println((double)count/size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void output(double rate,String path,String out){
		int offset = getTrainSetID(rate);
		int count = 0;
		int current;
		try {
			BufferedReader fin = new BufferedReader(new FileReader(path));
			BufferedWriter fout = new BufferedWriter(new FileWriter(out));
			String line;
			while( (line = fin.readLine()) != null){
				++count;
				if(line.startsWith("1")){
					current = offset + count;
					String query = "select * from train where id = "+current;
//					System.out.println(query);
					ResultSet re = db.select(query);
					
					while(re.next()){
//						System.out.println(re.getString(2));
						fout.write(re.getString(2)+"\t"+re.getString(3)+"\t");
						fout.write(re.getString(4)+"\t");
						fout.write("http://en.wikipedia.org/wiki/");
						fout.write(re.getString(5).replace(" ", "_")+"\t");
						fout.write(re.getString(22));
						fout.write("\n");
					}
				}
			}
			fin.close();
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
