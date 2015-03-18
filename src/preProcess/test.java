package preProcess;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "./train/qald-5_train.xml";
		QuestionList list = new QuestionList(path);
		ArrayList<Question> question = list.getQuestions();
		System.out.println(question.get(0).getAnswertype());
	}
}
