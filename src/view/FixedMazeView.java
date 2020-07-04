package view;
import javax.swing.JButton;
import data.*;

import java.awt.AWTException;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
public class FixedMazeView extends MazeView implements ItemListener,ActionListener {
	
	
    JButton again;                 //再一次
    JButton top;// 英雄榜
    Label label;
    Choice changeskin;
    public FixedMazeView(Point[][] p) throws AWTException{
       super(p);
       handleMove.mazetag=1;
       again = new JButton("重走");
       add(again);
       again.setSize(80,30);
       again.setLocation(1,leftY);
       again.addActionListener(this);
       
       top = new JButton("英雄榜");
       top.setSize(80,30);
       top.setLocation(1,180);
       top.addActionListener(this);
       add(top);
       
       label=new Label("皮肤选择");
		add(label);
		label.setSize(80, 30);
		label.setLocation(1, 90);
		
		changeskin=new Choice();
		changeskin.add("默认");
		changeskin.add("史蒂夫");
		changeskin.add("马里奥");
		changeskin.add("初音未来");
		add(changeskin);
		changeskin.setSize(80, 30);
		changeskin.setLocation(1, 130);
		changeskin.addItemListener(this);
    }
    public void actionPerformed(ActionEvent e){
       if(e.getSource() == again)
       {
    	   int m =point.length;
           int n =point[0].length;
           MazeMaker mazeMaker = new MazeByFile(new File("迷宫文件/蜀道迷宫.txt"));
           point= mazeMaker.initMaze();
           initPointXY();   
           initView();
           SetChargeOnRoad police = new  ChargeOnRoad();
           police.setMAXMoney(10);
           point = police.setChargeOnRoad(point,6);    //设置6个收费站
           handleMove.recordTime.stop();
           handleMove.spendTime = 0;
           handleMove.showTime.setText("0");
           handleMove.isLeave = false;
           peopleWalker.cleanMoney();
           repaint();
           peopleWalker.requestFocusInWindow();
       }
       else if(e.getSource() == top)
       {
    	   //TopDialog td = new TopDialog();
    	   new TopDialog().setVisible(true);
       }
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
