package train;

import java.io.IOException;

import service.*;

public class testSvm {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String []arg ={ "-v", "10","svm/score-train", 	//���SVMѵ��ģ���õ����ݵ�·��  
				"svm/score-model"};  //���SVMͨ��ѵ������ѵ��������ģ�͵�·��  
		String []parg={"svm/score-test",   //����Ǵ�Ų�������         
				"svm/score-model",  //���õ���ѵ���Ժ��ģ��       
				"svm/score-result.txt"};  //���ɵĽ�����ļ���·��     
		System.out.println("........SVM���п�ʼ..........");        //����һ��ѵ������    
		
		svm_train t = new svm_train();        //����һ��Ԥ����߷���Ķ���      
		svm_predict p= new svm_predict();        
				
		
		t.main(arg);   //����      
		p.main(parg);  //����
		
	}

}
