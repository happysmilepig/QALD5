package preProcess;

import java.util.ArrayList;


public class Question {
	private String id;
	private String onlydbo;
	private String answertype;
	private String aggregation;
	private String hybrid;
	private String text;
	private String query;
	private ArrayList<String> answers;
	private ArrayList<String> keywords;

	public Question(String id, String onlydbo, String answertype,
			String aggregation, String hybrid, String text, String query,
			ArrayList<String> answers, ArrayList<String> keywords) {
		this.id = id;
		this.onlydbo = onlydbo;
		this.answertype = answertype;
		this.aggregation = aggregation;
		this.hybrid = hybrid;
		this.text = text;
		this.query = query;
		this.answers = answers;
		this.keywords = keywords;
	}

	public String getId() {
		return id;
	}

	public String getOnlydbo() {
		return onlydbo;
	}

	public String getAnswertype() {
		return answertype;
	}

	public String getAggregation() {
		return aggregation;
	}

	public String getHybrid() {
		return hybrid;
	}

	public String getText() {
		return text;
	}

	public String getQuery() {
		return query;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

}
