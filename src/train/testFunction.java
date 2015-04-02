package train;

import java.io.IOException;
import java.io.PrintStream;

import BasicOps.FileOps;
import service.svm_predict;
import service.svm_scale;
import service.svm_train;

public class testFunction {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Function f = new Function();
		
		double rate = 0.8;
		
//		
		String [] features = {"priorscore","priorscore2","contextscore","contextscore2","weight","linkprobability","commonness","finalscore","finalscore2"};
		f.features(features, "./svm/features-score.txt");
		
//		System.setOut(new PrintStream("./svm/features"));
//		String [] sarg = {"-l","0","./svm/features-score.txt"};
//		svm_scale.main(sarg);
		
		f.divide(rate, "./svm/features-score.txt", "./svm/score-train", "./svm/score-test");
//		
		String []arg ={ "-v","10","svm/score-train", 	//���SVMѵ��ģ���õ����ݵ�·��  
		"svm/score-model"};  //���SVMͨ��ѵ������ѵ��������ģ�͵�·��  
		String[] parg = { "svm/score-test", // ����Ǵ�Ų�������
				"svm/score-model", // ���õ���ѵ���Ժ��ģ��
				"svm/score-result.txt" }; // ���ɵĽ�����ļ���·��
		System.out.println("........SVM���п�ʼ.........."); // ����һ��ѵ������


		svm_train.main(arg); // ����
		svm_predict.main(parg); // ����
		
		f.compare("./svm/score-test", "./svm/score-result.txt");
		f.output(rate, "./svm/score-result.txt", "./svm/score-entity.txt");
		f.close();
	}
}
