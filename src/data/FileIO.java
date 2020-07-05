package data;

import java.io.*;

public class FileIO 
{
	static public void FileInRear(String ad, String s) throws IOException// д���ļ�
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
	
	static public void FileRenew(String ad, String s) throws IOException
	{
//		BufferedWriter out = new BufferedWriter(new FileWriter(ad)); // �����������ַ����������Ҫ����Write����
//		out.write(s);
//		//out.newLine();
//		out.flush();
//		out.close();
		FileWriter fw = new FileWriter(ad); // �����Զ�����
		String str1 = s;
		fw.write(str1);
		fw.close();
	}
}
