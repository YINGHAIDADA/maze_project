package view;
import javax.swing.JButton;
import data.*;
import java.awt.event.*;
import java.io.*;
public class FixedMazeView extends MazeView implements ActionListener {
    JButton again;                 //再一次
    public FixedMazeView(Point[][] p){
       super(p);
       again = new JButton("重走");
       add(again);
       again.setSize(80,30);
       again.setLocation(1,leftY);
       again.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
       int m =point.length;
       int n =point[0].length;
       MazeMaker mazeMaker = new MazeByFile(new File("迷宫文件/蜀道迷宫.txt"));
       point= mazeMaker.initMaze();
       initPointXY();   
       initView();
       SetChargeOnRoad police = new  ChargeOnRoad();
       police.setMAXMoney(10);
       point = police.setChargeOnRoad(point,6);    //设置6个收费站
       handleMove.recordTime.stop();
       handleMove.spendTime = 0;
       handleMove.showTime.setText("0");
       handleMove.isLeave = false;
       peopleWalker.cleanMoney();
       repaint();
       peopleWalker.requestFocusInWindow();
   }
}
