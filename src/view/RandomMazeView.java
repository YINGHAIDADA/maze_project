package view;
import javax.swing.JButton;
import data.*;
import java.awt.event.*;
public class RandomMazeView extends MazeView implements ActionListener {
    JButton renew;                 //重新开始
    public RandomMazeView(Point[][] p){
       super(p);
       renew = new JButton("换迷宫");
       add(renew);
       renew.setSize(80,30);
       renew.setLocation(1,leftY);
       renew.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
       int m =point.length;
       int n =point[0].length;
       MazeMaker mazeMaker = new MazeByRandom(m,n);
       point= mazeMaker.initMaze();
       initPointXY();   
       initView();
       SetChargeOnRoad police = new  ChargeOnRoad();
       point = police.setChargeOnRoad(point,20);    //设置20个收费站
       handleMove.recordTime.stop();
       handleMove.spendTime = 0;
       handleMove.showTime.setText("0");
       handleMove.isLeave = false;
       peopleWalker.cleanMoney();
       repaint();
       peopleWalker.requestFocusInWindow();
   }
}
