package view;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import data.*;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
public class ChallengeMazeView extends MazeView implements ItemListener,ActionListener {
    JButton again;                 //再一次
    Label label,guanqia;
    Choice changeskin;
    Font font;
    public String mazename;
    public static int count,top=5;
    public ChallengeMazeView(Point[][] p){
       super(p);
       handleMove.mazetag=2;
       count=1;
       mazename="迷宫文件/闯关迷宫"+count+".txt";
       again = new JButton("重走");
       add(again);
       again.setSize(80,30);
       again.setLocation(1,leftY);
       again.addActionListener(this);
       
       label=new Label("皮肤选择");
		add(label);
		label.setSize(80, 30);
		label.setLocation(1, 90);
		label.setBackground(Color.yellow);
		
		changeskin=new Choice();
		changeskin.add("默认");
		changeskin.add("史蒂夫");
		changeskin.add("马里奥");
		changeskin.add("初音未来");
		add(changeskin);
		changeskin.setSize(80, 30);
		changeskin.setLocation(1, 130);
		changeskin.addItemListener(this);
		
		font=new Font("微软雅黑",Font.ITALIC|Font.BOLD,15);
		guanqia =new Label("关卡   "+count);
		add(guanqia);
		guanqia.setSize(80, 30);
		guanqia.setLocation(1, 190);
		guanqia.setBackground(Color.red);
		guanqia.setFont(font);
    }
    
    public void nextlevel()
    {
    	guanqia.setText("关卡   "+count);
    	mazename="迷宫文件/闯关迷宫"+count+".txt";
    	MazeMaker mazeMaker = new MazeByFile(new File(mazename));
        point= mazeMaker.initMaze();
        initPointXY();   
        initView();
        SetChargeOnRoad police = new  ChargeOnRoad();
        police.setMAXMoney(15);
        point = police.setChargeOnRoad(point,6);    //设置6个收费站
        handleMove.recordTime.stop();
        handleMove.spendTime = 0;
        handleMove.showTime.setText("0");
        handleMove.isLeave = false;
        peopleWalker.cleanMoney();
        repaint();
        peopleWalker.requestFocusInWindow();
    }
    
    public void actionPerformed(ActionEvent e){
       int m =point.length;
       int n =point[0].length;
       MazeMaker mazeMaker = new MazeByFile(new File(mazename));
       point= mazeMaker.initMaze();
       initPointXY();   
       initView();
       SetChargeOnRoad police = new  ChargeOnRoad();
       police.setMAXMoney(15);
       point = police.setChargeOnRoad(point,6);    //设置6个收费站
       handleMove.recordTime.stop();
       handleMove.spendTime = 0;
       handleMove.showTime.setText("0");
       handleMove.isLeave = false;
       peopleWalker.cleanMoney();
       repaint();
       peopleWalker.requestFocusInWindow();
   }
    
    public void itemStateChanged(ItemEvent e)
	{
		Toolkit tool;tool=getToolkit();
		if(((Choice)e.getItemSelectable()).getSelectedItem()=="默认")
		{
			PersonInMaze.image=tool.getImage("迷宫文件/person.gif");
		}
		else if(((Choice)e.getItemSelectable()).getSelectedItem()=="史蒂夫")
		{
			PersonInMaze.image=tool.getImage("迷宫文件/steve.gif");
		}
		else if(((Choice)e.getItemSelectable()).getSelectedItem()=="马里奥")
		{
			PersonInMaze.image=tool.getImage("迷宫文件/mariao.gif");
		}
		else if(((Choice)e.getItemSelectable()).getSelectedItem()=="初音未来")
		{
			PersonInMaze.image=tool.getImage("迷宫文件/chuyin.gif");
		}
	}

}
