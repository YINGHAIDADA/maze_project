package data;
import java.util.ArrayList;
import java.util.LinkedList;      
import java.util.Collections;
import java.util.Random;
import java.util.Iterator;  
public class MazeByRandom implements MazeMaker{
   public Point [][] point;        //point[i][j]是迷宫中的点
   int row,column;
   LinkedList<Point> isMountainPoint;
   public MazeByRandom(int row,int column){
      this.row = row;
      this.column = column;
      isMountainPoint = new LinkedList<Point>();
      point = new Point[row][column];
      for(int i=0;i<row;i++) {
         for(int j=0;j<column;j++){
            point[i][j] = new Point();//创建迷宫中的点
            point[i][j].setX(j);      
            point[i][j].setY(i);    
         }
      }
   }
   public Point [][] initMaze() {
      initRoad(); 
      initNumber();
      point[0][0].setIsEnter(true);
      point[0][0].setIsRoad(true); 
      point[0][0].setHaveFlag(true);  
      point[0][0].setNumber(-1);                   //入口点上的数字最小(山最矮)
      point[0][0].setIsMountain(true);
      point[row-1][column-1].setIsOut(true);
      point[row-1][column-1].setNumber(row*column);//出口点上的数字最大（山最高）
      point[row-1][column-1].setIsMountain(true);
      isMountainPoint.add(point[0][0]);
      isMountainPoint.add(point[row-1][column-1]);
      randomSetMountain(row*column/5);//再将row*column/5个点设置为山 
      Point pStart = point[0][0];
      while(pStart != point[row-1][column-1]){
          pStart = findMaxHeightMountain(pStart);
      }
      return point;
   }
   private void initRoad(){
      for(int i=0;i<row;i++) {
         for(int j=0;j<column;j++){
            point[i][j].setIsRoad(false);     //设置点都不是路
            point[i][j].setHaveFlag(false);   //设置点都没插旗
            point[i][j].setIsMountain(false); //设置点都不是山
         
         }
      }
   }
   private void initNumber(){
        ArrayList<Integer> list = new ArrayList<Integer>();    
        for(int i = 0;i<row*column;i++){    
           list.add(i);    
        }    
        Collections.shuffle(list); //把list的节点洗牌
        Iterator<Integer> ite = list.iterator();
        int m = 0;    
        for(int i=0;i<row;i++) {
          for(int j=0;j<column;j++){    
             point[i][j].setNumber(ite.next());//每个点上随机设置了一个在[0,row*column-1]中的数
          } 
        }    
   }
   private void randomSetMountain(int n){
       LinkedList<Point> list = new LinkedList<Point>();
       Random random = new Random();    
        for(int i=0;i<row;i++) {
           for(int j=0;j<column;j++){ 
             if(point[i][j]!=point[0][0]&&point[i][j]!=point[row-1][column-1])   
                 list.add(point[i][j]); 
           }   
       }  
       while(n>0){
         int index =random.nextInt(list.size()); //随机得到一个[0,list.size())之间的数
         Point pointBySet =  list.remove(index);//从链表中删除一个节点，并得到该节点
         pointBySet.setIsMountain(true);
         isMountainPoint.add(pointBySet);
         n--;
       }
   }
   Point findMaxHeightMountain(Point p){
       Point targetPoint = null;
       
       int minDistance = Integer.MAX_VALUE;
       LinkedList<Point>  pointFind  = new LinkedList<Point>(); //找到的距离p最近的没插旗的山
       for(int i = 0;i<isMountainPoint.size();i++){ //首先计算最近距离
           Point pt = isMountainPoint.get(i);
           if(p !=pt && pt.getHaveFlag() == false){
               if(p.distanceTo(pt)<minDistance)
                  minDistance = p.distanceTo(pt);
           }
       }
       for(int i = 0;i<isMountainPoint.size();i++){ //再找最近距离的没插旗的山
           Point pt = isMountainPoint.get(i);
           if(p != pt && pt.getHaveFlag() == false){
               if(minDistance ==p.distanceTo(pt)) {
                   pointFind.add(pt); 
               }
           }
       }
       //从pointFind找一个号码最大的Point，即找一座最高的山：
       Point maxPoint =null;
       int maxHeight = pointFind.get(0).getNumber();
       for(int i=0;i<pointFind.size();i++){
          int mNumber = pointFind.get(i).getNumber();
          if(mNumber >= maxHeight){
             maxHeight = mNumber;
          }
       } 
       for(int i=0;i<pointFind.size();i++){
          if(pointFind.get(i).getNumber() == maxHeight){
             maxPoint = pointFind.get(i);
             break;
          }
       } 
       targetPoint = maxPoint;
       targetPoint.setHaveFlag(true);        //将该山插旗作标记
       SetRoad.setRoad(p,targetPoint,point); //设置从p到targetPoint的路
       return targetPoint;
  }
}
