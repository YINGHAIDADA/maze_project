package view;

import javax.swing.JButton;
import data.*;
import robot.*;

import java.awt.Choice;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;

public class RandomMazeView extends MazeView implements ItemListener,ActionListener {
	JButton renew; // ���¿�ʼ
	JButton robotButton;//��������
	Choice changeskin;//Ƥ��ѡ��
	Label label;
	
	RobotMaze robotMaze;

	public RandomMazeView(Point[][] p) {
		super(p);
		handleMove.mazetag=0;
		renew = new JButton("���Թ�");
		add(renew);
		renew.setSize(80, 30);
		renew.setLocation(1, leftY); 
		renew.addActionListener(this);
		
		label=new Label("Ƥ��ѡ��");
		add(label);
		label.setSize(80, 30);
		label.setLocation(1, 90);
		
		changeskin=new Choice();
		changeskin.add("Ĭ��");
		changeskin.add("ʷ�ٷ�");
		changeskin.add("�����");
		changeskin.add("����δ��");
		add(changeskin);
		changeskin.setSize(80, 30);
		changeskin.setLocation(1, 130);
		changeskin.addItemListener(this);
		
		robotButton = new JButton("���Թ�");
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
			point = police.setChargeOnRoad(point, 20); // ����20���շ�վ
			handleMove.recordTime.stop();
			handleMove.spendTime = 0;
			handleMove.showTime.setText("0");
			handleMove.isLeave = false;
			peopleWalker.cleanMoney();
			repaint();
			peopleWalker.requestFocusInWindow();
		}
		else if(e.getSource()==robotButton)//��������
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
