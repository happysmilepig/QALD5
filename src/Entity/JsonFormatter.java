package Entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class JsonFormatter{

	public JsonFormatter(){
		
	}
	public String format(String json){
		String result;
		
		JsonParser parser = new JsonParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonElement el = parser.parse(json);
		result= gson.toJson(el);
		
		return result;
	}
}