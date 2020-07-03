package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.io.*;

class Student implements Serializable{ // 必须对该类序列化，即实现Serializable接口
	String name;
	int age;
	String dept;
	public Student(String newName, int newAge, String newDept){
		name = newName;
		age = newAge;
		dept = newDept;
	}
	public String toString(){
		return name + " " + age + " " + dept;	
	}
}
public class Filetest{
	public static void main(String[] args){
		Student w1 = new Student("张三", 20, "计算机系");
		Student w2 = new Student("李四", 21, "金融系");
		FileOutputStream fout;
		ObjectOutputStream dout;
		FileInputStream fin;
		ObjectInputStream din;
		File f = new File("ReadWriteObject.txt");
		try{
			f.createNewFile();
		}catch(IOException e){
			System.out.println(e);		
		}
		try{
			fout = new FileOutputStream(f); 
			dout = new ObjectOutputStream(fout); // 创建一个ObjectOutputStream对象
			dout.writeObject(w1);                // 写入序列化对象
			dout.writeObject(w2);
			dout.close();
		}catch(IOException e){
			System.out.println(e);
		}
		try{
			fin = new FileInputStream(f);
			din = new ObjectInputStream(fin); // 创建ObjectInputStream对象
			Student r1 = (Student)din.readObject(); // 从文件中读取序列化对象
			Student r2 = (Student)din.readObject();
			System.out.println(r1);
			System.out.println(r2);
			din.close();
		}catch(IOException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
