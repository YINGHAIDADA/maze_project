package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Filetest {
	public static void main(String[] args) throws FileNotFoundException {
		File a=new File("�Թ��ļ�/test.txt");
		String b;
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(a, "r");
			b=in.readLine();
			System.out.println(b);
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

}
