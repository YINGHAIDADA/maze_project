package gui;
import data.*;
import view.*;

import java.awt.AWTException;
import java.io.File;
public class AppWindow {
   public static void main(String []args) throws AWTException {
       MazeMaker mazeMaker = new MazeByRandom(25,50);
       Point[][] point= mazeMaker.initMaze();
       //设置收费点（可选）
       SetChargeOnRoad policeOne = new  ChargeOnRoad();
       // 收费站最高收费额默认是20
       point = policeOne.setChargeOnRoad(point,20); //设置20个收费站
       MazeView mazeView  = new RandomMazeView(point);
       IntegrationView integrationView = new IntegrationView();
       integrationView.addMazeView("随机迷宫",mazeView);
       
       // 蜀道迷宫
       mazeMaker = new MazeByFile(new File("迷宫文件/蜀道迷宫.txt"));      
       point= mazeMaker.initMaze();
       SetChargeOnRoad policeTwo = new  ChargeOnRoad();
       policeTwo.setMAXMoney(10); //收费站最高收费额为10
       point = policeTwo.setChargeOnRoad(point,6); //设置6个收费站
       mazeView  = new FixedMazeView(point);
       integrationView.addMazeView("蜀道迷宫",mazeView);
       
       // 闯关迷宫
       mazeMaker = new MazeByFile(new File("迷宫文件/闯关迷宫1.txt")); 
       point= mazeMaker.initMaze();
       SetChargeOnRoad policeThree = new  ChargeOnRoad();
       policeThree.setMAXMoney(15); //收费站最高收费额为15
       point = policeThree.setChargeOnRoad(point,6); //设置6个收费站
       mazeView  = new ChallengeMazeView(point);
       mazeView.handleMove.getview(mazeView);
       integrationView.addMazeView("闯关迷宫",mazeView);
       
       // 自定义迷宫
       mazeMaker = new MazeByDefined(25,50); 
       point= mazeMaker.initMaze();
       mazeView  = new UserDefinedMazeView(point); 
       integrationView.addMazeView("自定义迷宫",mazeView);
   }
}