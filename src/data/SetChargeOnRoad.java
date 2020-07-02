package data;
import data.Point;
public abstract class  SetChargeOnRoad {
    int MAXMoney = 20; //最高的收费金额
    //在点设置amount个收费点:
    public abstract Point [][] setChargeOnRoad(Point [][] point,int amount);
    public void setMAXMoney(int money){
       MAXMoney = money;
    }
}