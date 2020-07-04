package view;

import javax.swing.JButton;
import data.*;
import robot.*;

import java.awt.Choice;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;

public class RandomMazeView extends MazeView implements ItemListener,ActionListener {
	JButton renew; // 重新开始
	JButton robotButton;//智能行走
	Choice changeskin;//皮肤选择
	Label label;
	
	RobotMaze robotMaze;

	public RandomMazeView(Point[][] p) {
		super(p);
		handleMove.mazetag=0;
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
		
		robotButton = new JButton("换迷宫");
		add(robotButton);
		robotButton.setSize(80, 30);
		robotButton.setLocation(1, 180); 
		robotButton.addActionListener(this);
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
			remove(handleMove);
			add(robotMove);
			Point in,out;
			robotMaze=new RobotMaze(point);
			robotMaze.initMazeNodePathState();
			in=peopleWalker.getAtMazePoint();
			out=Point.getOut(point);
			
			robotMaze.findMazePath(in.x, in.y, out.x, out.y);
			point=robotMaze.showMazePath(out.x, out.y);
			robotMove.smartMove(point,peopleWalker);			
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
