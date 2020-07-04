package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import data.*;
import robot.*;

import java.awt.AWTException;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.*;

public class RandomMazeView extends MazeView implements ItemListener,ActionListener {
	JButton renew; // 重新开始
	JButton robotButton;//智能行走
	Choice changeskin;//皮肤选择
	Label label;
	Robot r;
	
	RobotMaze robotMaze;

	public RandomMazeView(Point[][] p) throws AWTException {
		super(p);
		r=new Robot();
		handleMove.mazetag=0;
		add_IJ();
		renew = new JButton("换迷宫");
		add(renew);
		renew.setSize(80, 30);
		renew.setLocation(1, leftY); 
		renew.addActionListener(this);
		
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
		
		robotButton = new JButton("智能行走");
		add(robotButton);
		robotButton.setSize(80, 30);
		robotButton.setLocation(1, 180); 
		robotButton.addActionListener(this);
	}
	
	public void add_IJ()
	{
		int row=this.point.length;
		int col=this.point[0].length;
		System.out.println(row+" "+col);
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				this.point[i][j].setI(i);
				this.point[i][j].setJ(j);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==renew) {
			int m = point.length;
			int n = point[0].length;
			MazeMaker mazeMaker = new MazeByRandom(m, n);
			point = mazeMaker.initMaze();
			initPointXY();
			initView();
			SetChargeOnRoad police = new ChargeOnRoad();
			point = police.setChargeOnRoad(point, 20); // 设置20个收费站
			handleMove.recordTime.stop();
			handleMove.spendTime = 0;
			handleMove.showTime.setText("0");
			handleMove.isLeave = false;
			peopleWalker.cleanMoney();
			repaint();
			peopleWalker.requestFocusInWindow();
		}
		else if(e.getSource()==robotButton)//智能行走
		{
			Point in,out;
			robotMaze=new RobotMaze(point);
			robotMaze.initMazeNodePathState();
			in=peopleWalker.getAtMazePoint();
			out=Point.getOut(point);
			
			robotMaze.findMazePath(in.getI(), in.getJ(), out.getI(), out.getJ());
			point=robotMaze.showMazePath(out.getI(), out.getJ());
			peopleWalker.requestFocusInWindow();
			smartMove();
		}
	}
	
	public void smartMove()
	{
		
		int m = -1, n = -1;
		int row=point.length;
		int col=point[0].length;
		
		while(peopleWalker.getAtMazePoint()!=Point.getOut(point))
		{
			m=peopleWalker.getAtMazePoint().getI();
			n=peopleWalker.getAtMazePoint().getJ();
			
			//向右走
			if(n<col-1)
			{
				if(point[m][n+1].getisLuJing())
				{
					r.keyPress(KeyEvent.VK_RIGHT);
				}
			}
			
			//向下走
			if(m<row-1)
			{
				if(point[m+1][n].getisLuJing())
				{
					r.keyPress(KeyEvent.VK_DOWN);
				}
			}
			
			//向左走
			if(n>0)
			{
				if(point[m][n-1].getisLuJing())
				{
					r.keyPress(KeyEvent.VK_LEFT);
				}
			}
			
			//向上走
			if(m>0)
			{
				if(point[m-1][n].getisLuJing())
				{
					r.keyPress(KeyEvent.VK_RIGHT);	
				}
			}
		
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
