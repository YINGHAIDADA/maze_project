package data;

// 闯关成绩记录
public class Record {
	public String name;// 玩家姓名
	public int time;// 闯关用时
	public Record next;// 后继指针
	
	public Record()
	{
		
	}
	public Record(String name, int time)
	{
		this.name = name;
		this.time = time;
	}	
}

