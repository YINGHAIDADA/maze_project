package data;
import data.Point;
import java.util.Random;
import java.util.ArrayList;
public class  ChargeOnRoad extends SetChargeOnRoad{ //设置amount个收费点
    public Point [][] setChargeOnRoad(Point [][] point,int amount){ 
        ArrayList<Point> list = new ArrayList<Point>();//放置迷宫中的路
        Random random = new Random();  
        for(int i=0;i<point.length;i++) {
          for(int j=0;j<point[i].length;j++){ 
              if(point[i][j].isRoad()&&!point[i][j].isEnter()&&!point[i][j].isOut())
                 list.add(point[i][j]);
                 point[i][j].setIsCharge(false);
          }
        }
        amount = Math.min(list.size(),amount);
        while(amount>0){
           int index =random.nextInt(list.size());//随机得到一个[0,list.size())之间的数
           Point pointSetCharge = list.remove(index);//从链表中删除一个节点并得到该节点
           pointSetCharge.setIsCharge(true);
           int money = random.nextInt(MAXMoney)+1;  //[1,MAXMoney]之间的随机数
           pointSetCharge.setChargeMoney(money);
           amount--;
       }
       return point;
   }
}