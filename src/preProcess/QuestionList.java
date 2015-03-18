package preProcess;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import BasicOps.FileOps;

public class QuestionList {
	private ArrayList<Question> questions;

	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public QuestionList(String path){
		questions = new ArrayList<Question>();
		String content = FileOps.LoadFile(path);
		Document doc = Jsoup.parse(content);
		Elements ques = doc.select("question");
		
		for(Element que : ques){
			String id = que.attr("id");
			String onlydbo = que.attr("onlydbo");
			String answertype = que.attr("answertype");
			String aggregation = que.attr("aggregation");
			String hybrid = que.attr("hybrid");
			Elements textE = que.select("string[lang=en]");
			Elements keywordsE = que.select("keyword[lang=en]");
			Elements queryE = que.select("query");
			Elements answersE = que.select("answers answer");
			
			String text = textE.text();
			String query = queryE.text();
			ArrayList<String> keywords = new ArrayList<String>();
			String [] keywordList = keywordsE.text().split(", ");
			for(String k : keywordList){
				keywords.add(k);
			}
			ArrayList<String> answers = new ArrayList<String>();
			for(Element answer : answersE){
				answers.add(answer.text());
			}
			
			Question currentQ = new Question(id, onlydbo, answertype, aggregation, hybrid, text, query, answers, keywords);
			questions.add(currentQ);
		}
	}
}
