package test;
import data.*;
public class AppTest {
   public static void main(String []args) {
      MazeMaker mazeMaker = new MazeByRandom(4,8);
      Point [][] point= mazeMaker.initMaze();
      SetChargeOnRoad police = new ChargeOnRoad(); 
      point = police.setChargeOnRoad(point,4);
      for(int i=0;i<point.length;i++) {
         for(int j=0;j<point[i].length;j++){
             if(point[i][j].isRoad()) {
                if(!point[i][j].getIsCharge()){
                    if(point[i][j].isEnter())
                       System.out.printf("%3s","E"); 
                    else if(point[i][j].isOut())
                       System.out.printf("%3s","O"); 
                    else
                       System.out.printf("%3s","R");
                }   
                else{
                   System.out.printf("%3d",point[i][j].getChargeMoney());
                } 
             }
             else {
                System.out.printf("%3s","W");
             }
         }
         System.out.println();
      }
   }
}