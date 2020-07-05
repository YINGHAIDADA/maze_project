package data;
public class Point{
	
    int x;            //点的位置坐标
	int y;
	int i;
	int j;
    int number;         //编号
    boolean isRoad;     //是否是路
    boolean isEnter;    //是否是入口点
    boolean isOut;      //是否是出口点
    boolean haveFlag;   //该点是否被标记有旗
    boolean isMountain; //该点是否是山
    boolean isCharge;   //该点是否收费 
    int chargeMoney;    //该点收取的费用
    public boolean[] state;	// 节点四个方向的行走状态，true表示可以走，false表示不能走
    
    boolean isLuJing;	//是不是最短路径上的点
    
    public Point()
    {
    	state=new boolean[4];
    }
    
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setI(int i){
        this.i=i;
    }
    public void setJ(int j){
        this.j=j;
    }
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public boolean isEnter(){
        return isEnter;
    }
    public void setIsOut(boolean boo){
        isOut = boo;
    }
    public boolean isOut(){
        return isOut;
    }
    public static Point getOut(Point[][] p){
    	for(int i=0;i<p.length;i++)
    	{
    		for(int j=0;j<p[i].length;j++)
    		{
    			if(p[i][j].isOut())
    				return p[i][j];
    		}
    	}
    	return null;
    }
    public static Point getEnter(Point[][] p){
    	for(int i=0;i<p.length;i++)
    	{
    		for(int j=0;j<p[i].length;j++)
    		{
    			if(p[i][j].isEnter())
    				return p[i][j];
    		}
    	}
    	return null;
    }
    public void setIsEnter(boolean boo){
        isEnter = boo;
    }
    public boolean isMountain(){
        return isMountain;
    }
    public void setIsMountain(boolean boo){
        isMountain = boo;
    }
    public boolean isRoad(){
        return isRoad;
    }
    public void setIsRoad(boolean boo){
        isRoad = boo;
    }
    public void setHaveFlag(boolean boo){
        haveFlag = boo;
    }
    public boolean getHaveFlag(){
        return haveFlag;
    }
    public void setisLuJing(boolean boo){
    	isLuJing=boo;
    }
    public boolean getisLuJing(){
    	return isLuJing;
    }
    public void setNumber(int n){
        number = n;
    }
    public int getNumber(){
        return number;
    }
    public void setIsCharge(boolean boo){
        isCharge = boo;
    }
    public boolean getIsCharge(){
        return isCharge;
    }
    public void setChargeMoney(int m){
        chargeMoney = m;
    }
    public int getChargeMoney(){
        return chargeMoney;
    }
    public boolean equals(Point p){
        if(p.getX()==this.getX()&&p.getY()==this.getY())
           return true;
        else
           return false; 
    }
    public int distanceTo(Point p){
         return Math.abs(this.getX()-p.getX())+Math.abs(this.getY()-p.getY());
    }
    
    public static String PointtoString(Point[][] p)
    {
    	String s="";
    	int row,col;
    	row=p.length;
    	col=p[0].length;
    	for(int i=0;i<row;i++)
    	{
    		for(int j=0;j<col;j++)
    		{
    			if(p[i][j].isRoad())
    			{
    				if(p[i][j].isEnter())
    				{
    					s+='*';
    				}
    				else if(p[i][j].isOut())
    				{
    					s+='#';
    				}
    				else
    				{
    					s+='0';
    				}
    			}
    			else
    			{
    				s+='1';
    			}
    		}
    		s+="\n";
    	}
    	
    	return s;
    }
}
