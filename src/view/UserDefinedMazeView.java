package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import data.*;
import data.Point;

public class UserDefinedMazeView extends MazeView implements ItemListener,ActionListener
{
	JButton redefine; // �����Զ���
	JButton restart;// ���¿�ʼ
	Choice changeskin;//Ƥ��ѡ��
	Choice selectdraw;
	Label skin;	
	Label draw;
	Label coords;
	int drawchoice = -1;
	int out = 0;
	
	public UserDefinedMazeView(Point[][] p)
	{		
		super(p);
		handleMove.mazetag=0;
		redefine = new JButton("�ػ�ͼ");
		add(redefine);
		redefine.setSize(80, 30);
		redefine.setLocation(1, 260); 
		redefine.addActionListener(this);
		
		restart = new JButton("����");
		add(restart);
		restart.setSize(80, 30);
		restart.setLocation(1, leftY); 
		restart.addActionListener(this);
		
		skin = new Label("Ƥ��ѡ��");
		skin.setBackground(Color.YELLOW);
		add(skin);
		skin.setSize(75, 30);
		skin.setLocation(1, 90);
		
		changeskin=new Choice();
		changeskin.add("Ĭ��");
		changeskin.add("ʷ�ٷ�");
		changeskin.add("�����");
		changeskin.add("����δ��");
		add(changeskin);
		changeskin.setSize(80, 30);
		changeskin.setLocation(1, 130);
		changeskin.addItemListener(this);
		
		coords = new Label();
		coords.setBounds(1, 300, 60, 30);
		add(coords);
		
		draw = new Label("ѡ���ͼģ��");
		draw.setBackground(Color.GREEN);
		draw.setBounds(1, 200, 75, 30);
		add(draw);
		
		selectdraw=new Choice();
		selectdraw.add("δѡ��");
		selectdraw.add("����·");
		selectdraw.add("�շ�վ");
		selectdraw.add("����");
		selectdraw.add("ɾ��·");
		add(selectdraw);
		selectdraw.setBounds(1, 230, 80, 30);
		selectdraw.addItemListener(this);	
		this.addMouseListener(new MouseAdpt()); //��갴ť����
        this.addMouseMotionListener(new MouseMotionAdpt()); //����ƶ�����
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
	
	// ���������
	class MouseAdpt extends MouseAdapter
    {              
        public void mouseClicked(MouseEvent e) //�������
        {      
        	int i = e.getX(), j = e.getY();
        	if(i >= 80 && i <= 1155 && j >= 50 && j <= 575)
            {
            	if(drawchoice == 1)
            		point[(j - 50) / 22][(i - 80) / 22].setIsRoad(true); // ���ô˵�Ϊ·   
            	else if(drawchoice == 2 && point[(j - 50) / 22][(i - 80) / 22].isRoad())
            	{
            		if(point[(j - 50) / 22][(i - 80) / 22].isOut())
            			JOptionPane.showMessageDialog(null, "�㲻���ڳ��������շ�վ��");
            		else
            		{
            			point[(j - 50) / 22][(i - 80) / 22].setIsCharge(true);
                		String m = JOptionPane.showInputDialog("�����ô˴��շ�վ·��");
                		point[(j - 50) / 22][(i - 80) / 22].setChargeMoney(Integer.parseInt(m));// ���ô˵��·��
            		}            		
            	}            		
            	else if(drawchoice == 3 && point[(j - 50) / 22][(i - 80) / 22].isRoad())
            	{
            		if(out == 0)
            		{
            			point[(j - 50) / 22][(i - 80) / 22].setIsOut(true);// ����ĵ���Ϊ����
            			out = 1;
            		}
            		else
            			JOptionPane.showMessageDialog(null, "ֻ������һ�����ڣ�");
            	}            		
            	else if(drawchoice == 0)
            	{
            		point[(j - 50) / 22][(i - 80) / 22].setIsRoad(false); // �������·��ɾ��  
            		point[(j - 50) / 22][(i - 80) / 22].setIsCharge(false); // ��������շ�վɾ��  
            		point[(j - 50) / 22][(i - 80) / 22].setIsOut(false); // ������ĳ���ɾ��  
            	}  
            	else;
            	repaint();
            }          
        }                     
    }
    
	// ����ƶ�����
    class MouseMotionAdpt extends MouseMotionAdapter
    {       
        public void mouseMoved(MouseEvent e)
        {
            //coords.setText("X: " + (e.getX()) + ",Y: " + (e.getY())); //�ο��������            
        }
        
        public void mouseDragged(MouseEvent e)
        {
        	int i = e.getX(), j = e.getY();
        	if(i >= 80 && i <= 1155 && j >= 50 && j <= 575)
        	{
        		if(drawchoice == 1)
        			point[(j - 50) / 22][(i - 80) / 22].setIsRoad(true); // ����Ϲ��ĵ㶼���·        		
        		else if(drawchoice == 0)
        		{
        			point[(j - 50) / 22][(i - 80) / 22].setIsRoad(false); // �������·��ɾ��  
            		point[(j - 50) / 22][(i - 80) / 22].setIsCharge(false); // ��������շ�վɾ��  
            		point[(j - 50) / 22][(i - 80) / 22].setIsOut(false); // ������ĳ���ɾ��  
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
		else if(c == selectdraw)
		{
			if(((Choice)e.getItemSelectable()).getSelectedItem()=="����·")
				drawchoice = 1;
			else if(((Choice)e.getItemSelectable()).getSelectedItem()=="�շ�վ")
				drawchoice = 2;
			else if(((Choice)e.getItemSelectable()).getSelectedItem()=="����")
				drawchoice = 3;
			else if(((Choice)e.getItemSelectable()).getSelectedItem()=="ɾ��·")
				drawchoice = 0;
			else
				drawchoice = -1;
		}
	}
}
