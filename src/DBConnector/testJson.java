package DBConnector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BasicOps.FileOps;

public class testJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int t=6; t<=6; ++t){
			String path = "./entity/spotlight/" + t +".txt";
			String response = FileOps.LoadFile(path);
			
			try {
				JSONObject res = new JSONObject(response).getJSONObject("annotation");
				if(!res.has("surfaceForm")){
					continue;
				}
				
				JSONObject ob = res.optJSONObject("surfaceForm");
				System.out.println(ob.toString());
				JSONArray array = new JSONArray();
				array.put(ob);
				System.out.println(array.toString());
//				JSONArray array = res.getJSONArray("surfaceForm");
				for(int i=0; i<array.length(); ++i){
//					JSONObject tem = array.getJSONObject(i).optJSONObject("resource");
//					if(tem == null){
//						System.out.println(i+"\tArray");
//					}
//					JSONArray temArray = array.getJSONObject(i).optJSONArray("resource");
//					if(temArray == null){
//						System.out.println(i+"\tObject");
//					}
//					if(!array.getJSONObject(i).has("resource")){
//						System.out.println(t);
//					}
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
