package train;

import java.io.IOException;

import service.*;

public class testSvm {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String []arg ={ "svm/a1a", 	//���SVMѵ��ģ���õ����ݵ�·��  
				"svm/a1a-model"};  //���SVMͨ��ѵ������ѵ��������ģ�͵�·��  
		String []parg={"svm/a1a.t",   //����Ǵ�Ų�������         
				"svm/a1a-model",  //���õ���ѵ���Ժ��ģ��       
				"svm/a1at.txt"};  //���ɵĽ�����ļ���·��     
		System.out.println("........SVM���п�ʼ..........");        //����һ��ѵ������    
		
		svm_train t = new svm_train();        //����һ��Ԥ����߷���Ķ���      
		svm_predict p= new svm_predict();        
				
		t.main(arg);   //����      
		p.main(parg);  //����
		
	}

}
