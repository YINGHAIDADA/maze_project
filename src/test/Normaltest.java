package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.*;

public class Normaltest {
	public static void main(String[] args) throws IOException {
		String a="������";
		a="�Թ��ļ�/"+a+".txt";
		File as=new File(a);
		as.createNewFile();
		FileWriter witer=new FileWriter(as);
		witer.write("��������\n");
		witer.close();
		System.out.println("�½��ɹ�");
	}

}
