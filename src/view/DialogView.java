package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import data.Record;
import data.LinkList;
import data.FileIO;

public class DialogView extends JFrame implements ActionListener
{
	TopDialog d = new TopDialog();
	JButton b = new JButton("测试");
	static Font f1 = new Font("TimesRoman", Font.BOLD, 28);
    static Font f2 = new Font("TimesRoman", Font.BOLD, 20);
    static Font f3 = new Font("TimesRoman", Font.PLAIN, 18);
	DialogView()
	{
		this.setLayout(null);
        this.setSize(800, 600);
        this.setTitle("测试对话框");
        this.setLocation(300, 170);
        b.setBounds(350, 250, 100, 60);
        add(b);
        b.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
        this.setResizable(false);
        this.setVisible(true);
	}
	
	public static void main(String[] agrs)
	{		
		new DialogView();
	}
	
	public void actionPerformed(ActionEvent e)
    {
        d.setVisible(true);      
    }	
}

// 输入名字对话框
class InputDialog extends JDialog implements ActionListener
{
	static int Width = 300, Height = 200;
    JLabel background = new JLabel();
    JLabel tip = new JLabel("请输入你的名字：");
    JButton confirm = new JButton("确认");
    JButton cancel = new JButton("取消");
    JTextField text = new JTextField();    
    String str = new String();
    int time;
    
	InputDialog()
	{
		//super(f);		
        this.setLayout(null);
        this.setSize(Width, Height);       
        ImageIcon menubackgroundp = new ImageIcon("迷宫文件/菜单栏背景.png");
        menubackgroundp.setImage(menubackgroundp.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
        background.setIcon(menubackgroundp);        
        tip.setFont(DialogView.f1);
        tip.setForeground(Color.red);
        tip.setBounds(40, 15, 240, 40);
        this.add(tip);
        text.setBounds(40, 70, 220, 40);
        text.setFont(DialogView.f2);
        this.add(text);
        confirm.setBounds(40, 140, 80, 30);        
        this.add(confirm);
        confirm.addActionListener(this); 
        cancel.setBounds(180, 140, 80, 30);        
        this.add(cancel);
        cancel.addActionListener(this); 
        this.add(background);                       
        background.setSize(Width, Height);
        this.setBounds(600, 240, Width, Height);
        this.setUndecorated(true); //去除关闭按钮
	}
	
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == confirm)
		{
			str = text.getText();			
			try {
				FileIO.FileIn("迷宫文件/英雄榜.txt", str);
				FileIO.FileIn("迷宫文件/英雄榜.txt", Integer.toString(time));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false);
		}
		else if(e.getSource() == cancel)
		{
			this.setVisible(false);
		}    
    }	
}


// 英雄榜对话框
class TopDialog extends JDialog implements ActionListener
{
    static int Width = 500, Height = 400;
    JLabel background = new JLabel();
    JLabel top = new JLabel("名次记录");
    JLabel name = new JLabel("玩  家  姓  名");
    JLabel time = new JLabel("闯关用时");
    ImageIcon bi = new ImageIcon("迷宫文件/返回.jpg");
    JButton back = new JButton("返回");
    LinkList L = new LinkList();// 存记录链表  
    
    TopDialog()
    {       
        this.setLayout(null);
        this.setSize(Width, Height);       
        ImageIcon menubackgroundp = new ImageIcon("迷宫文件/表格1.png");
        menubackgroundp.setImage(menubackgroundp.getImage().getScaledInstance(Width, Height, Image.SCALE_DEFAULT));
        background.setIcon(menubackgroundp);       
        
        top.setFont(DialogView.f2);name.setFont(DialogView.f2);time.setFont(DialogView.f2);
        back.setFont(DialogView.f2);
        top.setForeground(Color.WHITE);name.setForeground(Color.WHITE);time.setForeground(Color.WHITE);
        top.setBounds(10, 7, 100, 40);
        name.setBounds(180, 7, 120, 40);
        time.setBounds(392, 7, 100, 40);
        back.setBounds(200, 340, 80, 30);
        
        this.add(top);
        this.add(name);
        this.add(time);
        this.add(back);  
        
        try {
        	FileIO.FileOut("迷宫文件/英雄榜.txt", L);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        Record p = L.h.next;
        int i = 0;
        while(p != null && i < 10)
        {
        	JLabel nameL = new JLabel(p.name);
        	JLabel timeL = new JLabel(Integer.toString(p.time) + "秒");
        	nameL.setFont(DialogView.f3);
        	timeL.setFont(DialogView.f3);
        	nameL.setBounds(185, 50+i*25, 100, 25);
        	timeL.setBounds(410, 50+i*25, 100, 25);
        	nameL.setForeground(Color.WHITE);
        	timeL.setForeground(Color.WHITE);
        	JLabel t = new JLabel(Integer.toString(i + 1));
        	t.setFont(DialogView.f3);
        	t.setBounds(50, 50+i*25, 40, 25);
        	t.setForeground(Color.WHITE);
        	this.add(t);
        	this.add(nameL);
        	this.add(timeL);
        	p = p.next;
        	i++;
        }
        this.add(background);        
        back.addActionListener(this);        
        background.setSize(Width, Height);
        this.setBounds(400, 200, Width, Height);
        this.setUndecorated(true); //去除关闭按钮
    }
    
    public void actionPerformed(ActionEvent e)
    {
        this.setVisible(false);       
    }
}





