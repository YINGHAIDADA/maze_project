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
	
	static public int FileRenew(String ad, String s) throws IOException
	{
		//System.out.println(ad);
		if(ad==null)
			return 1;
		File file=new File("�Զ����Թ�/"+ad+".txt");
		boolean a=file.createNewFile();
		if(a==false)
			return 2;
		FileWriter fw = new FileWriter(file); // �����Զ�����
		fw.write(s);
		fw.close();
		return 0;
	}
}
