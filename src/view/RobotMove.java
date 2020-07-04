package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Point;
import robot.*;

public class RobotMove extends JPanel implements ActionListener {
	Point[][] p;
	int spendTime = 0;
	javax.swing.Timer recordTime; // ��ʱ��
	JTextField showTime;
	Toolkit tool; // ��������������
	PersonInMaze person;
	boolean isLeave = false; // �ж��Ƿ��Ѿ��뿪����
	int out_i, out_j; // ��ų��ڵ�λ������
	int mazetag; // 0����Թ�,1����Թ���2�����Թ�
	MazeView view;
	
	public RobotMove() {
		recordTime = new javax.swing.Timer(1000, this);
		showTime = new JTextField("0", 5);
		tool = getToolkit();
		showTime.setEditable(false);
		showTime.setHorizontalAlignment(JTextField.CENTER);
		add(new JLabel("��ʱ��:"));
		add(showTime);
		setBackground(Color.cyan);
	}
	
	public void smartMove(Point[][] point,PersonInMaze person1)
	{
		p=point;
		person=person1;
		recordTime.start();
		int m = -1, n = -1;
		
		while(person.getAtMazePoint()!=Point.getOut(p))
		{
			m=person.getAtMazePoint().x;
			n=person.getAtMazePoint().y;
			
			//������
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
		showTime.setText("��ʱ:" + spendTime + "��");
	}
	
	public void charseMoney(Point p) {
		int money = p.getChargeMoney();
		person.setMoney(money);
	}

}
