package view;
import javax.swing.*;
import data.Point;
import java.awt.*;
import java.awt.geom.*;
public class MazeView extends JPanel {
    public Point [][] point;        //需要视图的迷宫
    Rectangle2D [][] block;        //迷宫中路或墙的视图
    int width = 22;                //路或墙的宽度
    int height =22;
    int leftX = 80;                //起点偏移坐标
    int leftY = 50;
    PersonInMaze peopleWalker;     //走迷宫者
    public HandleMove  handleMove;        //负责处理行走
    public MazeView(Point[][] p){
        point = p; 
        peopleWalker = new PersonInMaze();
        handleMove = new HandleMove();
        initPointXY();  //依据视图重新设置点的坐标
        handleMove.setMazePoint(point);
        block = new Rectangle2D[point.length][point[0].length];
        setLayout(null);
        JPanel pNorth = new JPanel();
        add(handleMove);
        add(peopleWalker);
        handleMove.setSize(120,30);
        handleMove.setLocation(leftX,leftY/3);
        peopleWalker.setSize(width,height);
        peopleWalker.setAtMazePoint(getEnterPoint(point));
        peopleWalker.setLocation(getEnterPoint(point).getX(),getEnterPoint(point).getY()); 
        initView();
        registerListener();
    }
    
    public void nextlevel()
    {
    	
    }
    
   public void initPointXY(){ //依据视图重新设置点的坐标
       for(int i=0;i<point.length;i++) {
         for(int j=0;j<point[i].length;j++){
           point[i][j].setX(j*width+leftX);   //组件坐标系原点是左上角，向右是x-轴，向下是y-轴
           point[i][j].setY(i*height+leftY);    
         }
       }
       peopleWalker.setAtMazePoint(getEnterPoint(point));
       peopleWalker.setLocation(getEnterPoint(point).getX(),getEnterPoint(point).getY()); 
       handleMove.setMazePoint(point); 
   }
   public void initView() {
      for(int i = 0;i<point.length;i++){
           for(int j = 0;j<point[i].length;j++) {
             int x = point[i][j].getX();
             int y = point[i][j].getY();
             //绘制的矩形的左上角坐标是（x*width+leftX,y*height+leftY）
             block[i][j]=new Rectangle2D.Double(x,y,width,height);
           }
      }
      repaint();
      handleMove.showTime.setText("0");
      peopleWalker.requestFocusInWindow();
      validate();
   }
   public void registerListener(){
      peopleWalker.addKeyListener(handleMove);
      handleMove.setMazePoint(point);
   }
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g_2d = (Graphics2D)g;
       BasicStroke bs=
                   new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
       for(int i = 0;i<point.length;i++){
           for(int j = 0;j<point[i].length;j++) {
               if(!point[i][j].isRoad()){  
                   Color c = new Color(233,143,22);
                   g_2d.setColor(c);
                   g_2d.setStroke(bs); 
                   g_2d.draw(block[i][j]);
               }
               else {
                   g_2d.setColor(Color.green);
                   g_2d.fill(block[i][j]);
                   g_2d.setColor(Color.blue);
                   g_2d.setStroke(bs);
                   g_2d.draw(block[i][j]); 
                   if(point[i][j].getIsCharge()){
                      g_2d.setColor(Color.blue);
                      g_2d.fill(block[i][j]); 
                      g_2d.setColor(Color.white);
                      int x =point[i][j].getX();//为了把字写到point[i][j]的区域内
                      int y =point[i][j].getY();
                      g_2d.setFont(new Font("",Font.BOLD,15)); 
                      g_2d.drawString(""+point[i][j].getChargeMoney(),x+width/8,y+4*height/5);
                   }
                   if(point[i][j].isOut()){
                      g_2d.setColor(Color.red);
                      g_2d.fill(block[i][j]);
                      g_2d.setColor(Color.white);
                      g_2d.setFont(new Font("",Font.BOLD,10));
                      g_2d.drawString("出口",point[i][j].getX(),point[i][j].getY()+4*height/5);
                   }
               }  
           }
       }
       g_2d.setColor(Color.red);
       int x = point[0][0].getX();
       int y = point[0][0].getY();
       x= x*width+leftX;
       y= y*height+leftY;
       Rectangle2D rect = 
       new Rectangle2D.Double(x,y,width*point[0].length,height*point.length);
       g_2d.draw(rect);
       g_2d.setColor(Color.black);
       String mess1 ="绿色是路,红色是出口，蓝色是收费站（只要经过就收费，包括反复经过）"+
                     "务必记住整个路费，否则无法离开出口";
       String mess2 ="用鼠标单击走迷宫者，然后按方向键行走";
       int toLeftDis =handleMove.getBounds().width+leftX;
       g_2d.setFont(new Font("",Font.PLAIN,14));
       g_2d.drawString(mess1,toLeftDis+2,2*leftY/3);
       g_2d.drawString(mess2,leftX,(point.length+1)*height+leftY);
   }
   public Point getEnterPoint(Point [][] point){ //得到入口点
       Point p =null;
       for(int i = 0;i<point.length;i++){
           for(int j = 0;j<point[i].length;j++) {
               if(point[i][j].isEnter()){
                    p = point[i][j];
                    break;
               }
           }
       }
       return p;
   }
}
