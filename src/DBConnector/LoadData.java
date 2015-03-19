package DBConnector;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BasicOps.FileOps;

public class LoadData {
	private entityDB db;

	public LoadData() {
		db = new entityDB();
	}

	public void loadQuestion() {
		System.out.println("question loading...");
		String path = "./train/questions";
		LinkedList<String> ques = FileOps.LoadFilebyLine(path);
		for (String que : ques) {
			db.insertQuestion(que);
		}
		System.out.println("finished");
	}

	public void loadMiner() {
		System.out.println("miner result loading...");
		String path = "./entity/miner/";

		for (int i = 1; i <= 300; ++i) {
			System.out.println(i + " start loading...");
			String filename = path + i + ".txt";
			String response = FileOps.LoadFile(filename);

			int questionID = i;
			int startIndex;
			int endIndex;
			int candID;
			String candTitle;
			double weight;

			JSONObject responseJson = null;
			JSONArray spots = null;
			try {
				responseJson = new JSONObject(response);
				if (!responseJson.has("detectedTopics")) {
					continue;
				}
				spots = responseJson.getJSONArray("detectedTopics");
				for (int j = 0; j < spots.length(); ++j) {
					JSONObject spot = spots.getJSONObject(j);
					candID = spot.getInt("id");
					candTitle = spot.getString("title");
					weight = spot.getDouble("weight");

					JSONArray referArray = spot.getJSONArray("references");
					for (int c = 0; c < referArray.length(); ++c) {
						JSONObject refer = referArray.getJSONObject(c);
						startIndex = refer.getInt("start");
						endIndex = refer.getInt("end");

						db.insertMiner(questionID, startIndex, endIndex,
								candID, candTitle, weight);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("finished");
	}

	public void loadSpotlight() {
		System.out.println("spotlight result loading...");
		String path = "./entity/spotlight/";

		for (int i = 1; i <= 300; ++i) {
			System.out.println(i + " start loading...");
			String filename = path + i + ".txt";
			String response = FileOps.LoadFile(filename);

			int questionID = i;
			int startIndex;
			int endIndex;
			String candTitle;
			String candUri;
			int support;
			double finalScore;
			double priorScore;
			double contextualScore;
			double percentageOfSecondRank;
			String types;

			JSONObject responseJson = null;
			JSONArray spots = null;
			try {
				responseJson = new JSONObject(response).getJSONObject("annotation");
				if (!responseJson.has("surfaceForm")) {
					continue;
				}
				JSONObject spotsObject = responseJson.optJSONObject("surfaceForm");
				if(spotsObject != null){
					spots = new JSONArray();
					spots.put(spotsObject);
				}else{
					spots = responseJson.getJSONArray("surfaceForm");
				}
				
				for (int j = 0; j < spots.length(); ++j) {
					JSONObject spot = spots.getJSONObject(j);
					startIndex = Integer.parseInt(spot.getString("@offset"));
					String name = spot.getString("@name");
					endIndex = startIndex + name.length();
					
					if(!spot.has("resource")){
						continue;
					}
					
					JSONArray resources = null;
					JSONObject resourceObject = spot.optJSONObject("resource");
					if(resourceObject != null){
						resources = new JSONArray();
						resources.put(resourceObject);
					}else{
						resources = spot.getJSONArray("resource");
						for (int c = 0; c < resources.length(); ++c) {
							JSONObject resource = resources.getJSONObject(c);
							candTitle = resource.getString("@label");
							candUri = resource.getString("@uri");
							contextualScore = resource.getDouble("@contextualScore");
							support = resource.getInt("@support");
							finalScore = resource.getDouble("@finalScore");
							priorScore = resource.getDouble("@priorScore");
							percentageOfSecondRank = resource.getDouble("@percentageOfSecondRank");
							types = resource.getString("@types");
							db.insertSpotlight(questionID, startIndex, endIndex, candTitle, candUri, support, finalScore, priorScore, contextualScore, percentageOfSecondRank, types);
						}
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("finished");
	}

	public void loadDexter() {
		System.out.println("dexter result loading...");
		System.out.println("finished");
	}
}
