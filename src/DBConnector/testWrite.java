package DBConnector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class testWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		entityDB db = new entityDB();
		ArrayList<String[]> marks = db.selectMark();
		
		HashSet<String> set = new HashSet<String>();
		for(String[] strings : marks){
			set.add(strings[0]);
		}
		System.out.println(set.size());
		try {
			BufferedWriter fout=new BufferedWriter(new FileWriter("./train/all-mark-entities.txt"));
			StringBuilder sb = new StringBuilder();
			for (String[] strings : marks) {
				sb.setLength(0);
				for(int i=0; i<3; ++i){
					sb.append(strings[i]);
					sb.append("\t");
				}
				sb.append(strings[3]);
				sb.append("\n");
				fout.write(sb.toString());
			}
			fout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
