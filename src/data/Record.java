package data;

// ���سɼ���¼
public class Record {
	public String name;// �������
	public int time;// ������ʱ
	public Record next;// ���ָ��
	
	public Record()
	{
		
	}
	public Record(String name, int time)
	{
		this.name = name;
		this.time = time;
	}	
}

