package data;

// Record链表
public class LinkList {
	public Record h = new Record(); // 表头结点
	public LinkList()
	{
		h.next = null;
	}
	
	public void SortIn(Record q)// 按升序输入
	{
		Record p = h.next;
		Record r = h;
		if(h.next == null)
		{
			q.next = h.next;
			h.next = q;
			p = q;
			return;
		}
		while(p != null)
		{
			if(q.time < p.time)
			{
				q.next = p;	
				r.next = q;	
				return;
			}
			r = p;
			p = p.next;	
			if(p == null)
			{
				q.next = p;
				r.next = q;
				return;
			}
		}
	}
	
	public void Put()// 遍历输出
	{
		Record p = h.next;
		while(p != null)
		{
			System.out.println(p.name);
			System.out.println(Integer.toString(p.time));
			p = p.next;
		}
	}
}