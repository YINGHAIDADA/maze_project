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
    JButton again;                 //��һ��
    Label label,guanqia;
    Choice changeskin;
    Font font;
    public String mazename;
    public static int count,top=5;
    public ChallengeMazeView(Point[][] p){
       super(p);
       handleMove.mazetag=2;
       count=1;
       mazename="�Թ��ļ�/�����Թ�"+count+".txt";
       again = new JButton("����");
       add(again);
       again.setSize(80,30);
       again.setLocation(1,leftY);
       again.addActionListener(this);
       
       label=new Label("Ƥ��ѡ��");
		add(label);
		label.setSize(80, 30);
		label.setLocation(1, 90);
		label.setBackground(Color.yellow);
		
		changeskin=new Choice();
		changeskin.add("Ĭ��");
		changeskin.add("ʷ�ٷ�");
		changeskin.add("�����");
		changeskin.add("����δ��");
		add(changeskin);
		changeskin.setSize(80, 30);
		changeskin.setLocation(1, 130);
		changeskin.addItemListener(this);
		
		font=new Font("΢���ź�",Font.ITALIC|Font.BOLD,15);
		guanqia =new Label("�ؿ�   "+count);
		add(guanqia);
		guanqia.setSize(80, 30);
		guanqia.setLocation(1, 190);
		guanqia.setBackground(Color.red);
		guanqia.setFont(font);
    }
    
    public void nextlevel()
    {
    	guanqia.setText("�ؿ�   "+count);
    	mazename="�Թ��ļ�/�����Թ�"+count+".txt";
    	MazeMaker mazeMaker = new MazeByFile(new File(mazename));
        point= mazeMaker.initMaze();
        initPointXY();   
        initView();
        SetChargeOnRoad police = new  ChargeOnRoad();
        police.setMAXMoney(15);
        point = police.setChargeOnRoad(point,6);    //����6���շ�վ
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
       point = police.setChargeOnRoad(point,6);    //����6���շ�վ
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
		if(((Choice)e.getItemSelectable()).getSelectedItem()=="Ĭ��")
		{
			PersonInMaze.image=tool.getImage("�Թ��ļ�/person.gif");
		}
		else if(((Choice)e.getItemSelectable()).getSelectedItem()=="ʷ�ٷ�")
		{
			PersonInMaze.image=tool.getImage("�Թ��ļ�/steve.gif");
		}
		else if(((Choice)e.getItemSelectable()).getSelectedItem()=="�����")
		{
			PersonInMaze.image=tool.getImage("�Թ��ļ�/mariao.gif");
		}
		else if(((Choice)e.getItemSelectable()).getSelectedItem()=="����δ��")
		{
			PersonInMaze.image=tool.getImage("�Թ��ļ�/chuyin.gif");
		}
	}

}
