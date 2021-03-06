package view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import data.Point;

public class HandleMove extends JPanel implements KeyListener, ActionListener {
	Point[][] p;
	int spendTime = 0;
	javax.swing.Timer recordTime; // 计时器
	JTextField showTime;
	Toolkit tool; // 用来播放嘟嘟声音
	PersonInMaze person;
	boolean isLeave = false; // 判断是否已经离开出口
	int out_i, out_j; // 存放出口点位置索引
	int mazetag; // 0随机迷宫,1蜀道迷宫，2闯关迷宫
	MazeView view;
	Robot r;
	
	InputDialog input = new InputDialog();// 开输入对话框
	
	HandleMove() throws AWTException {
		r=new Robot();
		recordTime = new javax.swing.Timer(1000, this);
		showTime = new JTextField("0", 5);
		tool = getToolkit();
		showTime.setEditable(false);
		showTime.setHorizontalAlignment(JTextField.CENTER);
		add(new JLabel("计时器:"));
		add(showTime);
		setBackground(Color.cyan);
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

	public void keyPressed(KeyEvent e) {
		recordTime.start();
		person = (PersonInMaze) e.getSource();
		int m = -1, n = -1;
		Point startPoint = person.getAtMazePoint();
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				if (startPoint.equals(p[i][j])) {
					m = i;
					n = j;
					break;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			int k = Math.max(m - 1, 0);
			if (p[k][n].isRoad()) {
				tool.beep(); // 发出嘟的一声
				person.setAtMazePoint(p[k][n]);
				person.setLocation(p[k][n].getX(), p[k][n].getY());
				if (p[k][n].getIsCharge()) {
					charseMoney(p[k][n]); // 见后面的收费方法charseMoney
				}
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			int k = Math.min(m + 1, p.length - 1);
			if (p[k][n].isRoad()) {
				tool.beep();
				person.setAtMazePoint(p[k][n]);
				person.setLocation(p[k][n].getX(), p[k][n].getY());
				if (p[k][n].getIsCharge())
					charseMoney(p[k][n]);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			int k = Math.max(n - 1, 0);
			if (p[m][k].isRoad()) {
				tool.beep();
				person.setAtMazePoint(p[m][k]);
				person.setLocation(p[m][k].getX(), p[m][k].getY());
				if (p[m][k].getIsCharge())
					charseMoney(p[m][k]);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			int k = Math.min(n + 1, p[0].length - 1);
			if (p[m][k].isRoad()) {
				tool.beep();
				person.setAtMazePoint(p[m][k]);
				person.setLocation(p[m][k].getX(), p[m][k].getY());
				if (p[m][k].getIsCharge())
					charseMoney(p[m][k]);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		spendTime++;
		showTime.setText("用时:" + spendTime + "秒");
	}

	public void keyReleased(KeyEvent e) {
		if (isLeave == true)
			return;
		PersonInMaze person = (PersonInMaze) e.getSource();
		int m = -1, n = -1;
		Point endPoint = person.getAtMazePoint();
		if (endPoint.isOut()) {
			String str = JOptionPane.showInputDialog(this, "输入您的路费（数字）", "收费站出口", JOptionPane.PLAIN_MESSAGE);
			int number = 0;
			try {
				number = Integer.parseInt(str.trim());
			} catch (Exception exp) {
				JOptionPane.showMessageDialog(this, "您费用不对，请重新进入出口", "消息框", JOptionPane.INFORMATION_MESSAGE);
			}
			if (number == person.getMoney()) {
				recordTime.stop();
				JOptionPane.showMessageDialog(this, "您可以离开出口", "消息框", JOptionPane.INFORMATION_MESSAGE);
				// TODO 添加输入名字的消息框
				int x = p[p.length - 1][p[0].length - 1].getX() + person.getBounds().width;
				int y = p[p.length - 1][p[0].length - 1].getY() + person.getBounds().height;
				person.setLocation(x, y);
				isLeave = true;
				person.cleanMoney();
				
                if(mazetag == 1)
                {
                	input.setVisible(true);// 弹出输入对话框
    				input.time = spendTime;// 传入闯关用时
                }
				else if (mazetag == 2) {
					if(ChallengeMazeView.count==ChallengeMazeView.top)
					{
						JOptionPane.showMessageDialog(this, "恭喜您已经完成全部关卡", "胜利", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
					int next = JOptionPane.showConfirmDialog(null, "是否进入下一关？", "标题", JOptionPane.YES_NO_OPTION);
					if (next == 0) {
						ChallengeMazeView.count++;
						view.nextlevel();
					}
					
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "您费用不对，请重新进入出口", "消息框", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void charseMoney(Point p) {
		int money = p.getChargeMoney();
		person.setMoney(money);
	}

	public void keyTyped(KeyEvent e) {
	}
}