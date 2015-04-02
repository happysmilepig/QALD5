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
		String []arg ={ "-v","10","svm/score-train", 	//存放SVM训练模型用的数据的路径  
		"svm/score-model"};  //存放SVM通过训练数据训练出来的模型的路径  
		String[] parg = { "svm/score-test", // 这个是存放测试数据
				"svm/score-model", // 调用的是训练以后的模型
				"svm/score-result.txt" }; // 生成的结果的文件的路径
		System.out.println("........SVM运行开始.........."); // 创建一个训练对象


		svm_train.main(arg); // 调用
		svm_predict.main(parg); // 调用
		
		f.compare("./svm/score-test", "./svm/score-result.txt");
		f.output(rate, "./svm/score-result.txt", "./svm/score-entity.txt");
		f.close();
	}
}
