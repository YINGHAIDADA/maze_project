package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import data.*;

public class Normaltest {
	public static void main(String[] args) throws IOException {
		String a="干你娘";
		a="迷宫文件/"+a+".txt";
		File as=new File(a);
		as.createNewFile();
		FileWriter witer=new FileWriter(as);
		witer.write("干尼玛是\n");
		witer.close();
		System.out.println("新建成功");
	}

}
