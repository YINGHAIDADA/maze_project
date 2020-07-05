package data;

import java.io.*;

public class FileIO 
{
	static public void FileInRear(String ad, String s) throws IOException// 写入文件
	{	
		FileWriter writer = new FileWriter(ad, true);
        writer.write("\n" + s);
        writer.close();
	}	
	
	static public void FileOut(String ad, LinkList L) throws IOException// 读取文件
	{
		String thisLine;
		BufferedReader in = new BufferedReader(new FileReader(ad)); // 创建缓存区字符输入流，需要传如Reader对象
		while((thisLine = in.readLine()) != null)    // 每次读取一行，直到文件结束
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
		File file=new File("自定义迷宫/"+ad+".txt");
		boolean a=file.createNewFile();
		if(a==false)
			return 2;
		FileWriter fw = new FileWriter(file); // 可以自动创建
		fw.write(s);
		fw.close();
		return 0;
	}
}
