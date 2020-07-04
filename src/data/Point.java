package data;
public class Point{
	
    public int x;            //���λ������
	public int y;
    int number;         //���
    boolean isRoad;     //�Ƿ���·
    boolean isEnter;    //�Ƿ�����ڵ�
    boolean isOut;      //�Ƿ��ǳ��ڵ�
    boolean haveFlag;   //�õ��Ƿ񱻱������
    boolean isMountain; //�õ��Ƿ���ɽ
    boolean isCharge;   //�õ��Ƿ��շ� 
    int chargeMoney;    //�õ���ȡ�ķ���
    public boolean[] state;	// �ڵ��ĸ����������״̬��true��ʾ�����ߣ�false��ʾ������
    
    public static char[][] zhuanint(Point[][] p)
    {
    	char[][] zhuan;
    	int m=p.length;
    	int n=p[0].length;
    	zhuan=new char[m][n];
    	for(int i=0;i<m;i++)
    	{
    		for(int j=0;j<n;j++)
    		{
    			if(p[i][j].isRoad())
    			{
    				if(p[i][j].isEnter())
    				{
    					zhuan[i][j]='*';
    				}
    				else if(p[i][j].isOut())
    				{
    					zhuan[i][j]='#';
    				}
    				else
    				{
    					zhuan[i][j]='0';
    				}
    			}
    			else
    			{
    				zhuan[i][j]='1';
    			}
    		}
    	}
    	
    	return zhuan;
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
}
