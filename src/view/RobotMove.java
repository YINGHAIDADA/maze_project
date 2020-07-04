package view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Point;
import robot.*;

public class RobotMove extends JPanel implements ActionListener {
	Point[][] p;
	Robot r;
	int spendTime = 0;
	javax.swing.Timer recordTime; // 计时器
	JTextField showTime;
	Toolkit tool; // 用来播放嘟嘟声音
	PersonInMaze person;
	boolean isLeave = false; // 判断是否已经离开出口
	int out_i, out_j; // 存放出口点位置索引
	int mazetag; // 0随机迷宫,1蜀道迷宫，2闯关迷宫
	MazeView view;
	
	public RobotMove() throws AWTException {
		r = new Robot();
		recordTime = new javax.swing.Timer(1000, this);
		showTime = new JTextField("0", 5);
		tool = getToolkit();
		showTime.setEditable(false);
		showTime.setHorizontalAlignment(JTextField.CENTER);
		add(new JLabel("计时器:"));
		add(showTime);
		setBackground(Color.cyan);
	}
	
	public void smartMove(Point[][] point,PersonInMaze person1)
	{
		
		p=point;
		recordTime.start();
		int m = -1, n = -1;
		int row=point.length;
		int col=point[0].length;
		
		while(person1.getAtMazePoint()!=Point.getOut(p))
		{
			m=person1.getAtMazePoint().getI();
			n=person1.getAtMazePoint().getJ();
			
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
		
		if(person.getAtMazePoint()==Point.getOut(p))
		{
			recordTime.stop();
			JOptionPane.showMessageDialog(this, "机器人已成功到达终点，花费为"+person.getMoney()+"元", "消息框", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	public void getview(MazeView mazeview)
	{
		view=mazeview;
	}
	
	public void setMazePoint(Point[][] point) {
		p = point;
	}
	
	public void initSpendTime() {
		recordTime.stop();
		spendTime = 0;
		showTime.setText(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		spendTime++;
		showTime.setText("用时:" + spendTime + "秒");
	}
	
	public void charseMoney(Point p) {
		int money = p.getChargeMoney();
		person.setMoney(money);
	}

}
