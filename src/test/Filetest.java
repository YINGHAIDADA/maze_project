package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.io.*;

class Student implements Serializable{ // ����Ը������л�����ʵ��Serializable�ӿ�
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
		Student w1 = new Student("����", 20, "�����ϵ");
		Student w2 = new Student("����", 21, "����ϵ");
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
			dout = new ObjectOutputStream(fout); // ����һ��ObjectOutputStream����
			dout.writeObject(w1);                // д�����л�����
			dout.writeObject(w2);
			dout.close();
		}catch(IOException e){
			System.out.println(e);
		}
		try{
			fin = new FileInputStream(f);
			din = new ObjectInputStream(fin); // ����ObjectInputStream����
			Student r1 = (Student)din.readObject(); // ���ļ��ж�ȡ���л�����
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
