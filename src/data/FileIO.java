package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO 
{
	static public void FileIn(String ad, String s) throws IOException// д���ļ�
	{	
		FileWriter writer = new FileWriter(ad, true);
        writer.write("\n" + s);
        writer.close();
	}	
	
	static public void FileOut(String ad, LinkList L) throws IOException// ��ȡ�ļ�
	{
		String thisLine;
		BufferedReader in = new BufferedReader(new FileReader(ad)); // �����������ַ�����������Ҫ����Reader����
		while((thisLine = in.readLine()) != null)    // ÿ�ζ�ȡһ�У�ֱ���ļ�����
		{
			String name = thisLine;
			int time = Integer.parseInt(in.readLine());
			Record q = new Record(name, time);
			L.SortIn(q);						
		}
		in.close();
	}
}
