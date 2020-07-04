package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import data.*;
import robot.*;

import java.awt.AWTException;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;

public class RandomMazeView extends MazeView implements ItemListener,ActionListener {
	JButton renew; // ���¿�ʼ
	JButton robotButton;//��������
	Choice changeskin;//Ƥ��ѡ��
	Label label;
	MoveThread moveThread;
	
	RobotMaze robotMaze;

	public RandomMazeView(Point[][] p) throws AWTException {
		super(p);
		handleMove.mazetag=0;
		add_IJ();
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
		
		robotButton = new JButton("��������");
		add(robotButton);
		robotButton.setSize(80, 30);
		robotButton.setLocation(1, 180); 
		robotButton.addActionListener(this);
	}
	
	public void add_IJ()
	{
		int row=this.point.length;
		int col=this.point[0].length;
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
			moveThread.stop();
			
			int m = point.length;
			int n = point[0].length;
			MazeMaker mazeMaker = new MazeByRandom(m, n);
			point = mazeMaker.initMaze();
			initPointXY();
			initView();
			SetChargeOnRoad police = new ChargeOnRoad();
			point = police.setChargeOnRoad(point, 20); // ����20���շ�վ
			add_IJ();
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
			Point in,out;
			robotMaze=new RobotMaze(point);
			robotMaze.initMazeNodePathState();
			in=peopleWalker.getAtMazePoint();
			out=Point.getOut(point);
			
			//System.out.println("���"+in.getI()+","+in.getJ()+"����"+out.getI()+","+out.getJ());
			
			robotMaze.findMazePath(in.getI(), in.getJ(), out.getI(), out.getJ());
			point=robotMaze.showMazePath(out.getI(), out.getJ());
			peopleWalker.requestFocusInWindow();
			moveThread=new MoveThread();
			moveThread.start();	
		}
	}
	
	public void smartMove()
	{
		handleMove.recordTime.start();
		int m = -1, n = -1;
		int row=point.length;
		int col=point[0].length;
		
		while(!peopleWalker.getAtMazePoint().isOut())
		{
			m=peopleWalker.getAtMazePoint().getI();
			n=peopleWalker.getAtMazePoint().getJ();
			
			//������
			if(n<col-1)
			{
				if(point[m][n+1].getisLuJing())
				{
					handleMove.tool.beep(); // ����ཱུ�һ��
					peopleWalker.setAtMazePoint(point[m][n+1]);
					peopleWalker.setLocation(point[m][n+1].getX(), point[m][n+1].getY());
					point[m][n].setisLuJing(false);
					if (point[m][n+1].getIsCharge()) {
						charseMoney(point[m][n+1]); // ��������շѷ���charseMoney
					}
				}
			}
			
			//������
			if(m<row-1)
			{
				if(point[m+1][n].getisLuJing())
				{
					handleMove.tool.beep(); // ����ཱུ�һ��
					peopleWalker.setAtMazePoint(point[m+1][n]);
					peopleWalker.setLocation(point[m+1][n].getX(), point[m+1][n].getY());
					point[m][n].setisLuJing(false);
					if (point[m+1][n].getIsCharge()) {
						charseMoney(point[m+1][n]); // ��������շѷ���charseMoney
					}
				}
			}
			
			//������
			if(n>0)
			{
				if(point[m][n-1].getisLuJing())
				{
					handleMove.tool.beep(); // ����ཱུ�һ��
					peopleWalker.setAtMazePoint(point[m][n-1]);
					peopleWalker.setLocation(point[m][n-1].getX(), point[m][n-1].getY());
					point[m][n].setisLuJing(false);
					if (point[m][n-1].getIsCharge()) {
						charseMoney(point[m][n-1]); // ��������շѷ���charseMoney
					}
				}
			}
			
			//������
			if(m>0)
			{
				if(point[m-1][n].getisLuJing())
				{
					handleMove.tool.beep(); // ����ཱུ�һ��
					peopleWalker.setAtMazePoint(point[m-1][n]);
					peopleWalker.setLocation(point[m-1][n].getX(), point[m-1][n].getY());
					point[m][n].setisLuJing(false);
					if (point[m-1][n].getIsCharge()) {
						charseMoney(point[m-1][n]); // ��������շѷ���charseMoney
					}
				}
			}
			//repaint();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		if(peopleWalker.getAtMazePoint()==Point.getOut(point))
		{
			handleMove.recordTime.stop();
			JOptionPane.showMessageDialog(this, "�������ѵ����յ㣬��ʱ��"+handleMove.spendTime+"�� ���ѣ�"+peopleWalker.getMoney()+"Ԫ", "��Ϣ", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void charseMoney(Point p) {
		int money = p.getChargeMoney();
		peopleWalker.setMoney(money);
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
	
	private class MoveThread extends Thread{//�����������ߵ��߳���
		public void run() {
			try {
				smartMove();
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
}
