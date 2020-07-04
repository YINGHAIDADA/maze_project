package view;
import javax.swing.*;
import data.Point;
import java.awt.*;
public class PersonInMaze extends JTextField{
   Point point;  //所在的点
   Toolkit tool;
   public static Image image; 
   int money;    //被收取的全部钱
   PersonInMaze(){
      tool=getToolkit();
      setEditable(false);
      setBorder(null);
      setOpaque(false);
      setToolTipText("单击我,然后按键盘方向键吧");
      setFocusable(true);
      requestFocusInWindow();
      image=tool.getImage("迷宫文件/person.gif");
   }
   public void setAtMazePoint(Point p){
      point = p;
   }
   public Point getAtMazePoint(){
      return point;
   }
   public void setMoney(int m){
      money += m;
   }
   public void cleanMoney(){
      money = 0;
   }
   public int getMoney(){
      return money;
   }
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      int w=getBounds().width;
      int h=getBounds().height; 
      g.drawImage(image,0,0,w,h,this);
  } 
}