package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import data.*;
import data.Point;

public class UserDefinedMazeView extends MazeView implements ItemListener,ActionListener
{
	JButton redefine; // 重新自定义
	JButton restart;// 重新开始
	Choice changeskin;//皮肤选择
	Choice selectdraw;
	Label skin;	
	Label draw;
	Label coords;// 测试坐标
	int drawchoice = -1;
	int out = 0;
	
	public UserDefinedMazeView(Point[][] p) throws AWTException
	{		
		super(p);
		handleMove.mazetag=0;
		redefine = new JButton("重绘图");
		add(redefine);
		redefine.setSize(80, 30);
		redefine.setLocation(1, 260); 
		redefine.addActionListener(this);
		
		restart = new JButton("重走");
		add(restart);
		restart.setSize(80, 30);
		restart.setLocation(1, leftY); 
		restart.addActionListener(this);
		
		skin = new Label("皮肤选择");
		skin.setBackground(Color.YELLOW);
		add(skin);
		skin.setSize(75, 30);
		skin.setLocation(1, 90);
		
		changeskin=new Choice();
		changeskin.add("默认");
		changeskin.add("史蒂夫");
		changeskin.add("马里奥");
		changeskin.add("初音未来");
		add(changeskin);
		changeskin.setSize(80, 30);
		changeskin.setLocation(1, 130);
		changeskin.addItemListener(this);
		
		coords = new Label();
		coords.setBounds(1, 300, 60, 30);
		add(coords);
		
		draw = new Label("选择绘图模块");
		draw.setBackground(Color.GREEN);
		draw.setBounds(1, 200, 75, 30);
		add(draw);
		
		selectdraw=new Choice();
		selectdraw.add("未选择");
		selectdraw.add("绘制路");
		selectdraw.add("收费站");
		selectdraw.add("出口");
		selectdraw.add("删除路");
		add(selectdraw);
		selectdraw.setBounds(1, 230, 80, 30);
		selectdraw.addItemListener(this);	
		this.addMouseListener(new MouseAdpt()); //鼠标按钮监听
        this.addMouseMotionListener(new MouseMotionAdpt()); //鼠标移动监听
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == redefine)
		{
			int m = point.length;
			int n = point[0].length;
			MazeMaker mazeMaker = new MazeByDefined(m, n);
			point = mazeMaker.initMaze();
			initPointXY();
			initView();
			handleMove.recordTime.stop();
			handleMove.spendTime = 0;
			handleMove.showTime.setText("0");
			handleMove.isLeave = false;
			peopleWalker.cleanMoney();
			repaint();
			peopleWalker.requestFocusInWindow();
		}
		else if(e.getSource() == restart)
		{
			initPointXY();
			initView();
			handleMove.recordTime.stop();
			handleMove.spendTime = 0;
			handleMove.showTime.setText("0");
			handleMove.isLeave = false;
			peopleWalker.cleanMoney();
			repaint();
			peopleWalker.requestFocusInWindow();
		}
	}
	
	// 鼠标点击监听
	class MouseAdpt extends MouseAdapter
    {              
        public void mouseClicked(MouseEvent e) //单击鼠标
        {      
        	int i = e.getX(), j = e.getY();
        	if(i >= 80 && i <= 1155 && j >= 50 && j <= 575)
            {
            	if(drawchoice == 1)
            		point[(j - 50) / 22][(i - 80) / 22].setIsRoad(true); // 设置此点为路   
            	else if(drawchoice == 2 && point[(j - 50) / 22][(i - 80) / 22].isRoad())
            	{
            		if(point[(j - 50) / 22][(i - 80) / 22].isOut())
            			JOptionPane.showMessageDialog(null, "你不能在出口设置收费站！");
            		else
            		{
            			point[(j - 50) / 22][(i - 80) / 22].setIsCharge(true);
                		String m = JOptionPane.showInputDialog("请设置此处收费站路费");
                		point[(j - 50) / 22][(i - 80) / 22].setChargeMoney(Integer.parseInt(m));// 设置此点过路费
            		}            		
            	}            		
            	else if(drawchoice == 3 && point[(j - 50) / 22][(i - 80) / 22].isRoad())
            	{
            		if(out == 0)
            		{
            			point[(j - 50) / 22][(i - 80) / 22].setIsOut(true);// 点击的点设为出口
            			out = 1;
            		}
            		else
            			JOptionPane.showMessageDialog(null, "只能设置一个出口！");
            	}            		
            	else if(drawchoice == 0)
            	{
            		point[(j - 50) / 22][(i - 80) / 22].setIsRoad(false); // 鼠标点击的路都删除  
            		point[(j - 50) / 22][(i - 80) / 22].setIsCharge(false); // 鼠标点击的收费站删除  
            		point[(j - 50) / 22][(i - 80) / 22].setIsOut(false); // 鼠标点击的出口删除  
            	}  
            	else;
            	repaint();
            }          
        }                     
    }
    
	// 鼠标移动监听
    class MouseMotionAdpt extends MouseMotionAdapter
    {       
        public void mouseMoved(MouseEvent e)
        {
            //coords.setText("X: " + (e.getX()) + ",Y: " + (e.getY())); //参考鼠标坐标            
        }
        
        public void mouseDragged(MouseEvent e)
        {
        	int i = e.getX(), j = e.getY();
        	if(i >= 80 && i <= 1155 && j >= 50 && j <= 575)
        	{
        		if(drawchoice == 1)
        			point[(j - 50) / 22][(i - 80) / 22].setIsRoad(true); // 鼠标拖过的点都变成路        		
        		else if(drawchoice == 0)
        		{
        			point[(j - 50) / 22][(i - 80) / 22].setIsRoad(false); // 鼠标点击的路都删除  
            		point[(j - 50) / 22][(i - 80) / 22].setIsCharge(false); // 鼠标点击的收费站删除  
            		point[(j - 50) / 22][(i - 80) / 22].setIsOut(false); // 鼠标点击的出口删除  
        		}
        		repaint();
        	}
        }
    }
	
	public void itemStateChanged(ItemEvent e)
	{
		Choice c;
		c = (Choice)(e.getSource());
		if(c == changeskin)
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
		else if(c == selectdraw)
		{
			if(((Choice)e.getItemSelectable()).getSelectedItem()=="绘制路")
				drawchoice = 1;
			else if(((Choice)e.getItemSelectable()).getSelectedItem()=="收费站")
				drawchoice = 2;
			else if(((Choice)e.getItemSelectable()).getSelectedItem()=="出口")
				drawchoice = 3;
			else if(((Choice)e.getItemSelectable()).getSelectedItem()=="删除路")
				drawchoice = 0;
			else
				drawchoice = -1;
		}
	}
}
